package com.smartinvoice.service.impl;

import com.smartinvoice.dto.ApproveRequest;
import com.smartinvoice.dto.UploadResponse;
import com.smartinvoice.entity.*;
import com.smartinvoice.repository.*;
import com.smartinvoice.service.InvoiceService;
import com.smartinvoice.util.FileStorage;
import com.smartinvoice.util.SimpleInvoiceParser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.OffsetDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final VendorRepository vendorRepository;
    private final AttachmentRepository attachmentRepository;
    private final ApprovalRepository approvalRepository;
    private final SimpleInvoiceParser parser;
    private final FileStorage storage;

    @Override
    @Transactional
    public UploadResponse uploadAndParse(MultipartFile file) throws Exception {
        var parsed = parser.parse(file);
        var vendor = upsertVendor(parsed.vendor());
        var invoice = parsed.invoice();
        invoice.setVendor(vendor);
        invoice.setStatus(Invoice.Status.UNDER_REVIEW);
        invoice = invoiceRepository.save(invoice);

        String path = storage.save(file);
        var att = Attachment.builder()
                .invoice(invoice)
                .filename(file.getOriginalFilename())
                .contentType(file.getContentType())
                .sizeBytes(file.getSize())
                .storagePath(path)
                .build();
        attachmentRepository.save(att);

        return toResponse(invoice);
    }

    private Vendor upsertVendor(Vendor v) {
        if (v.getGstin() != null) {
            return vendorRepository.findByGstin(v.getGstin())
                    .map(existing -> {
                        existing.setName(v.getName() != null ? v.getName() : existing.getName());
                        return vendorRepository.save(existing);
                    }).orElseGet(() -> vendorRepository.save(v));
        } else if (v.getName() != null) {
            return vendorRepository.findByNameIgnoreCase(v.getName())
                    .orElseGet(() -> vendorRepository.save(v));
        } else {
            return vendorRepository.save(v);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<UploadResponse> list() {
        return invoiceRepository.findAll().stream().map(this::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public UploadResponse get(Long id) {
        var inv = invoiceRepository.findById(id).orElseThrow();
        return toResponse(inv);
    }

    @Override
    @Transactional
    public UploadResponse decide(Long id, ApproveRequest request) {
        var inv = invoiceRepository.findById(id).orElseThrow();
        var approval = Approval.builder()
                .invoice(inv)
                .approverEmail(request.approverEmail())
                .decidedAt(OffsetDateTime.now())
                .decision(request.decision())
                .comments(request.comments())
                .build();
        approvalRepository.save(approval);

        if ("APPROVE".equalsIgnoreCase(request.decision())) {
            inv.setStatus(Invoice.Status.APPROVED);
            if (inv.getVendor() != null && inv.getVendor().getStatus() == Vendor.Status.PENDING) {
                inv.getVendor().setStatus(Vendor.Status.VERIFIED);
                vendorRepository.save(inv.getVendor());
            }
        } else {
            inv.setStatus(Invoice.Status.REJECTED);
        }
        invoiceRepository.save(inv);
        return toResponse(inv);
    }

    private UploadResponse toResponse(Invoice i) {
        return new UploadResponse(
                i.getId(),
                i.getInvoiceNumber(),
                i.getVendor() != null ? i.getVendor().getName() : null,
                i.getInvoiceDate(),
                i.getTotal(),
                i.getStatus().name()
        );
    }
}
