package com.rent.rentcar.controller;

import com.rent.rentcar.Enum.PaymentMethod;
import com.rent.rentcar.dto.invoice.InvoiceDto;
import com.rent.rentcar.dto.invoice.InvoiceToSaveDto;
import com.rent.rentcar.exception.NotFoundExceptionEntity;
import com.rent.rentcar.service.invoice.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/invoices")
public class InvoiceController {

    private final InvoiceService invoiceService;

    @Autowired
    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping
    public ResponseEntity<List<InvoiceDto>> getAllInvoices() {
        List<InvoiceDto> invoices = invoiceService.getAllInvoices();
        return ResponseEntity.ok(invoices);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InvoiceDto> getInvoiceById(@PathVariable Long id) {
        try {
            InvoiceDto invoiceDto = invoiceService.getInvoiceById(id);
            return ResponseEntity.ok(invoiceDto);
        } catch (NotFoundExceptionEntity ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<InvoiceDto> addInvoice(@RequestBody InvoiceToSaveDto invoiceToSaveDto) {
        InvoiceDto savedInvoice = invoiceService.addInvoice(invoiceToSaveDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedInvoice);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InvoiceDto> updateInvoice(@PathVariable Long id, @RequestBody InvoiceToSaveDto invoiceToSaveDto) {
        try {
            InvoiceDto updatedInvoice = invoiceService.updateInvoice(id, invoiceToSaveDto);
            return ResponseEntity.ok(updatedInvoice);
        } catch (NotFoundExceptionEntity ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInvoice(@PathVariable Long id) {
        invoiceService.deleteInvoice(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/year")
    public ResponseEntity<List<InvoiceDto>> findInvoicesByYear(@RequestParam("year")  Integer year) {
        List<InvoiceDto> invoices = invoiceService.findByPaymentDateYear(year);
        return ResponseEntity.ok(invoices);
    }

    @GetMapping("/year/{year}/months")
    public ResponseEntity<List<InvoiceDto>> findInvoicesByYearAndMonths(@PathVariable Integer year, @RequestParam List<Integer> months) {
        List<InvoiceDto> invoices = invoiceService.findInvoicesByYearAndMonths(year, months);
        return ResponseEntity.ok(invoices);
    }

    @GetMapping("/payment-method")
    public ResponseEntity<List<InvoiceDto>> findByPaymentMethod(@RequestParam("paymentMethod") PaymentMethod paymentMethod) {
        List<InvoiceDto> invoices = invoiceService.findByPaymentMethod(paymentMethod);
        return ResponseEntity.ok(invoices);
    }

    @GetMapping("/payment-method/month")
    public ResponseEntity<List<InvoiceDto>> findByPaymentMethodAndMonth(@RequestParam("paymentMethod") PaymentMethod paymentMethod, @RequestParam("month") Integer month) {
        List<InvoiceDto> invoices = invoiceService.findByPaymentMethodAndMonth(paymentMethod, month);
        return ResponseEntity.ok(invoices);
    }


    @GetMapping("/payment-method/year")
    public ResponseEntity<List<InvoiceDto>> findByPaymentMethodAndYear(@RequestParam("paymentMethod") PaymentMethod paymentMethod, @RequestParam("year") Integer year){
        List<InvoiceDto> invoices = invoiceService.findByPaymentMethodAndYear(paymentMethod, year);
        return ResponseEntity.ok(invoices);
    }
}
