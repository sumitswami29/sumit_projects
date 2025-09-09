package com.smartinvoice.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record UploadResponse(
        Long invoiceId,
        String invoiceNumber,
        String vendorName,
        LocalDate invoiceDate,
        BigDecimal total,
        String status
) {}
