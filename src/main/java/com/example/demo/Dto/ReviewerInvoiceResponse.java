package com.example.demo.Dto;

import com.example.demo.Models.InvoiceStatus;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class ReviewerInvoiceResponse {


    private Long id;
    private String invoiceNumber;
    private String vendorName;
    private BigDecimal amount;
    private LocalDate dueDate;
    private InvoiceStatus status;
}
