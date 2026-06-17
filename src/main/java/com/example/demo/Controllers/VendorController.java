package com.example.demo.Controllers;

import com.example.demo.Dto.CreateInvoiceRequest;
import com.example.demo.Dto.InvoiceResponse;
import com.example.demo.Models.Invoice;
import com.example.demo.Services.InvoiceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vendor")
public class VendorController {
    @Autowired
    private InvoiceService invoiceService;

    @GetMapping("/dashboard")
    @PreAuthorize("hasRole('VENDOR')")
    public String dashboard() {
        return "Vendor's Dashboard";

    }

    @PostMapping("/invoices")
    @PreAuthorize("hasRole('VENDOR')")
    public Invoice createInvoice(
            @Valid @RequestBody CreateInvoiceRequest request,
            Authentication authentication
    ) {

        return invoiceService.createInvoice(
                request,
                authentication.getName()
        );
    }

    @GetMapping("/invoices")
    @PreAuthorize("hasRole('VENDOR')")
    public List<InvoiceResponse> getMyInvoices(
            Authentication authentication
    ) {

        return invoiceService.getMyInvoices(
                authentication.getName()
        );
    }

}
