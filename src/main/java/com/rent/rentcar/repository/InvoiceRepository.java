package com.rent.rentcar.repository;

import com.rent.rentcar.models.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    @Query("SELECT i FROM Invoice i WHERE YEAR(i.paymentDate) = :year AND MONTH(i.paymentDate) IN :months")
    List<Invoice> findInvoicesByYearAndMonths(Integer year, List<Integer> months);

    @Query("SELECT i FROM Invoice i WHERE YEAR(i.paymentDate) = :year")
    List<Invoice> findByPaymentDateYear(Integer year);
}
