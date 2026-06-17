package com.example.demo.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
//import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class CreateInvoiceRequest {
    @NotBlank
    private String invoiceNumber;

    @NotNull
    private BigDecimal amount;

    @NotNull
    private LocalDate dueDate;

    private String description;
}
