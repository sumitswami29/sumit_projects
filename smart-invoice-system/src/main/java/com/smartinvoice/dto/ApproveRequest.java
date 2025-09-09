package com.smartinvoice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ApproveRequest(
        @Email String approverEmail,
        @NotBlank String decision, // APPROVE or REJECT
        String comments
) {}
