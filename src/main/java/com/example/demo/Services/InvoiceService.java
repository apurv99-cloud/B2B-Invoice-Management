package com.example.demo.Services;

import com.example.demo.Dto.CreateInvoiceRequest;
import com.example.demo.Dto.InvoiceResponse;
import com.example.demo.Dto.ReviewerInvoiceResponse;
import com.example.demo.Models.Invoice;
import com.example.demo.Models.InvoiceRejection;
import com.example.demo.Models.InvoiceStatus;
import com.example.demo.Models.User;
import com.example.demo.Repository.InvoiceRejectionRepository;
import com.example.demo.Repository.InvoiceRepository;
import com.example.demo.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final UserRepository userRepository;
    private final InvoiceRejectionRepository invoiceRejectionRepository;

    public Invoice createInvoice(CreateInvoiceRequest request, String email) {

        User vendor = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Vendor not found"));

        if (invoiceRepository.existsByInvoiceNumber(request.getInvoiceNumber())) {

            throw new RuntimeException("Invoice Number already exists");
        }

        Invoice invoice = Invoice.builder().invoiceNumber(request.getInvoiceNumber()).amount(request.getAmount()).description(request.getDescription()).dueDate(request.getDueDate()).status(InvoiceStatus.SUBMITTED).vendor(vendor).build();

        return invoiceRepository.save(invoice);
    }

    public List<InvoiceResponse> getMyInvoices(String email) {

        User vendor = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Vendor not found"));

        return invoiceRepository.findByVendor(vendor).stream().map(invoice -> InvoiceResponse.builder().id(invoice.getId()).invoiceNumber(invoice.getInvoiceNumber()).amount(invoice.getAmount()).description(invoice.getDescription()).dueDate(invoice.getDueDate()).status(invoice.getStatus()).rejectionReason(invoice.getRejection() != null ? invoice.getRejection().getRejectionReason() : null).build()).toList();
    }

    public List<ReviewerInvoiceResponse> getSubmittedInvoices() {

        return invoiceRepository.findByStatus(InvoiceStatus.SUBMITTED).stream().map(invoice -> ReviewerInvoiceResponse.builder().id(invoice.getId()).invoiceNumber(invoice.getInvoiceNumber()).vendorName(invoice.getVendor().getCompanyName()).amount(invoice.getAmount()).dueDate(invoice.getDueDate()).status(invoice.getStatus()).build()).toList();
    }

    public Invoice approveInvoice(Long invoiceId, String reviewerEmail) {

        Invoice invoice = invoiceRepository.findById(invoiceId).orElseThrow(() -> new RuntimeException("Invoice not found"));

        User reviewer = userRepository.findByEmail(reviewerEmail).orElseThrow(() -> new RuntimeException("Reviewer not found"));

        if (invoice.getStatus() != InvoiceStatus.SUBMITTED) {

            throw new RuntimeException("Only submitted invoices can be approved");
        }

        invoice.setStatus(InvoiceStatus.APPROVED);
        invoice.setApprovedBy(reviewer);
        invoice.setApprovedDate(LocalDateTime.now());

        return invoiceRepository.save(invoice);
    }

    public Invoice rejectInvoice(Long invoiceId, String reviewerEmail, String reason) {

        Invoice invoice = invoiceRepository.findById(invoiceId).orElseThrow(() -> new RuntimeException("Invoice not found"));

        User reviewer = userRepository.findByEmail(reviewerEmail).orElseThrow(() -> new RuntimeException("Reviewer not found"));

        if (invoice.getStatus() != InvoiceStatus.SUBMITTED) {

            throw new RuntimeException("Only submitted invoices can be rejected");
        }

        invoice.setStatus(InvoiceStatus.REJECTED);

        InvoiceRejection rejection = InvoiceRejection.builder().invoice(invoice).reviewer(reviewer).rejectionReason(reason).build();

        invoice.setRejection(rejection);

        invoiceRejectionRepository.save(rejection);

        return invoiceRepository.save(invoice);
    }
}
