package com.smartinvoice.controller;

import com.smartinvoice.dto.ApproveRequest;
import com.smartinvoice.dto.UploadResponse;
import com.smartinvoice.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/invoices")
@RequiredArgsConstructor
public class InvoiceController {

    private final InvoiceService invoiceService;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public UploadResponse upload(@RequestPart("file") MultipartFile file) throws Exception {
        return invoiceService.uploadAndParse(file);
    }

    @GetMapping
    public List<UploadResponse> list() {
        return invoiceService.list();
    }

    @GetMapping("/{id}")
    public UploadResponse get(@PathVariable Long id) {
        return invoiceService.get(id);
    }

    @PostMapping("/{id}/decision")
    public UploadResponse decide(@PathVariable Long id, @Validated @RequestBody ApproveRequest request) {
        return invoiceService.decide(id, request);
    }
}
