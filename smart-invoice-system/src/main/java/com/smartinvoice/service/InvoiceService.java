package com.smartinvoice.service;

import com.smartinvoice.dto.ApproveRequest;
import com.smartinvoice.dto.UploadResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface InvoiceService {

    UploadResponse uploadAndParse(MultipartFile file) throws Exception;

    List<UploadResponse> list();

    UploadResponse get(Long id);

    UploadResponse decide(Long id, ApproveRequest request);

    // keep this since your controller or older code might use it
    default UploadResponse rejectInvoice(Long id) {
        // simple delegation to decide()
        return decide(id, new ApproveRequest("system", "REJECT", "Rejected via rejectInvoice()"));
    }
}
