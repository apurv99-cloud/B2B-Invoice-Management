package com.example.demo.Dto;

import com.example.demo.Models.InvoiceStatus;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class InvoiceResponse {
    private Long id;
    private String invoiceNumber;
    private BigDecimal amount;
    private String description;
    private LocalDate dueDate;
    private InvoiceStatus status;
    private String rejectionReason;
}
