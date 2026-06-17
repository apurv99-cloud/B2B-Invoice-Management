package com.example.demo.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PaymentRequest {

    @NotBlank
    private String paymentReference;
}
