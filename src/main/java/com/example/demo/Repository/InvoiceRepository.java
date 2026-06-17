package com.example.demo.Repository;


import com.example.demo.Models.Invoice;
import com.example.demo.Models.InvoiceStatus;
import com.example.demo.Models.User;
//import org.hibernate.internal.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InvoiceRepository
        extends JpaRepository<Invoice, Long> {

    Optional<Invoice> findById(Long id);
    List<Invoice> findByVendor(User vendor);

    boolean existsByInvoiceNumber(String invoiceNumber);

    List<Invoice> findByStatus(InvoiceStatus status);
}
