package com.example.demo.Controllers;

import com.example.demo.Dto.RejectInvoiceRequest;
import com.example.demo.Dto.ReviewerInvoiceResponse;
import com.example.demo.Models.Invoice;
import com.example.demo.Services.InvoiceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviewer")
public class ReviewerController {

    @Autowired
    private InvoiceService invoiceService;

    @GetMapping("/dashbord")
    @PreAuthorize("hasRole('REVIEWER')")
    public String dashboard() {
        return "reviewer'dashboard";
    }

    @GetMapping("/invoices")
    @PreAuthorize("hasRole('REVIEWER')")
    public List<ReviewerInvoiceResponse> getSubmittedInvoices() {

        return invoiceService.getSubmittedInvoices();
    }

    @PutMapping("/invoices/{id}/approve")
    @PreAuthorize("hasRole('REVIEWER')")
    public Invoice approveInvoice(
            @PathVariable Long id,
            Authentication authentication
    ) {

        return invoiceService.approveInvoice(
                id,
                authentication.getName()
        );
    }

    @PutMapping("/invoices/{id}/reject")
    @PreAuthorize("hasRole('REVIEWER')")
    public Invoice rejectInvoice(
            @PathVariable Long id,
            @Valid @RequestBody RejectInvoiceRequest request,
            Authentication authentication
    ) {

        return invoiceService.rejectInvoice(
                id,
                authentication.getName(),
                request.getReason()
        );
    }
}
