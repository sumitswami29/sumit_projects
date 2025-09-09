package com.smartinvoice.util;

import com.smartinvoice.entity.Invoice;
import com.smartinvoice.entity.Vendor;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class SimpleInvoiceParser {

    private static final Pattern INVOICE_NO = Pattern.compile("(?:Invoice\\s*No\\.?|Invoice\\s*#)\\s*([A-Za-z0-9-_/]+)", Pattern.CASE_INSENSITIVE);
    private static final Pattern DATE = Pattern.compile("(?:Date|Invoice\\s*Date)\\s*[:\\-]?\\s*(\\d{1,2}[/\\-]\\d{1,2}[/\\-]\\d{2,4})", Pattern.CASE_INSENSITIVE);
    private static final Pattern TOTAL = Pattern.compile("(?:Total\\s*Amount|Grand\\s*Total|Total)\\s*[:\\-]?\\s*([0-9,]+(?:\\.\\d{1,2})?)", Pattern.CASE_INSENSITIVE);
    private static final Pattern VENDOR = Pattern.compile("(?:Vendor|From|Supplier)\\s*[:\\-]?\\s*([A-Za-z0-9 &.,-]+)");

    private static final DateTimeFormatter[] DATES = new DateTimeFormatter[]{
            DateTimeFormatter.ofPattern("dd/MM/uuuu"),
            DateTimeFormatter.ofPattern("MM/dd/uuuu"),
            DateTimeFormatter.ofPattern("dd-MM-uuuu"),
            DateTimeFormatter.ofPattern("MM-dd-uuuu")
    };

    public ParsedResult parse(MultipartFile file) throws IOException {
        String name = file.getOriginalFilename() != null ? file.getOriginalFilename().toLowerCase(Locale.ROOT) : "";
        if (name.endsWith(".pdf")) {
            return parsePdf(file.getInputStream());
        } else if (name.endsWith(".xlsx")) {
            return parseExcel(file.getInputStream());
        } else {
            throw new IOException("Unsupported file type. Upload PDF or XLSX.");
        }
    }

    private ParsedResult parsePdf(InputStream in) throws IOException {
        try (PDDocument doc = PDDocument.load(in)) {
            PDFTextStripper stripper = new PDFTextStripper();
            String text = stripper.getText(doc);

            String invoiceNo = group(text, INVOICE_NO);
            String vendor = group(text, VENDOR);
            String dateStr = group(text, DATE);
            String totalStr = group(text, TOTAL);

            LocalDate date = tryParseDate(dateStr);
            BigDecimal total = tryParseBig(totalStr);

            Invoice invoice = Invoice.builder()
                    .invoiceNumber(invoiceNo)
                    .invoiceDate(date)
                    .currency("INR")
                    .subtotal(total) // naive
                    .tax(BigDecimal.ZERO)
                    .total(total)
                    .build();

            Vendor v = Vendor.builder().name(vendor).status(Vendor.Status.PENDING).build();

            return new ParsedResult(invoice, v);
        }
    }

    private ParsedResult parseExcel(InputStream in) throws IOException {
        try (Workbook wb = new XSSFWorkbook(in)) {
            Sheet sheet = wb.getSheetAt(0);
            String invoiceNo = sheet.getRow(1).getCell(1).getStringCellValue(); // expects invoice no in B2
            String vendor = sheet.getRow(2).getCell(1).getStringCellValue();    // vendor in B3
            LocalDate date = sheet.getRow(3).getCell(1).getLocalDateTimeCellValue().toLocalDate(); // date in B4
            double total = sheet.getRow(4).getCell(1).getNumericCellValue(); // total in B5

            Invoice invoice = Invoice.builder()
                    .invoiceNumber(invoiceNo)
                    .invoiceDate(date)
                    .currency("INR")
                    .subtotal(BigDecimal.valueOf(total))
                    .tax(BigDecimal.ZERO)
                    .total(BigDecimal.valueOf(total))
                    .build();

            Vendor v = Vendor.builder().name(vendor).status(Vendor.Status.PENDING).build();

            return new ParsedResult(invoice, v);
        } catch (Exception e) {
            throw new IOException("Failed to parse Excel: " + e.getMessage(), e);
        }
    }

    private String group(String text, Pattern p) {
        Matcher m = p.matcher(text);
        return m.find() ? m.group(1).trim() : null;
    }

    private LocalDate tryParseDate(String s) {
        if (s == null) return null;
        for (DateTimeFormatter f : DATES) {
            try { return LocalDate.parse(s, f); } catch (Exception ignored) {}
        }
        return null;
    }

    private BigDecimal tryParseBig(String s) {
        if (s == null) return null;
        try { return new BigDecimal(s.replaceAll(",", "")); } catch (Exception e) { return null; }
    }

    public record ParsedResult(Invoice invoice, Vendor vendor) {}
}
