package com.example.demo.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RejectInvoiceRequest {
    @NotBlank
    private String reason;
}
