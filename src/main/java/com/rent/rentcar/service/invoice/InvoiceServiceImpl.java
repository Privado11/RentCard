package com.rent.rentcar.service.invoice;

import com.rent.rentcar.Enum.PaymentMethod;
import com.rent.rentcar.dto.invoice.InvoiceDto;
import com.rent.rentcar.dto.invoice.InvoiceMapper;
import com.rent.rentcar.dto.invoice.InvoiceToSaveDto;
import com.rent.rentcar.exception.NotFoundExceptionEntity;
import com.rent.rentcar.models.Invoice;
import com.rent.rentcar.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService{

    private final InvoiceRepository invoiceRepository;
    private final InvoiceMapper invoiceMapper;

    @Autowired
    public InvoiceServiceImpl(InvoiceRepository invoiceRepository, InvoiceMapper invoiceMapper) {
        this.invoiceRepository = invoiceRepository;
        this.invoiceMapper = invoiceMapper;
    }

    @Override
    public InvoiceDto getInvoiceById(Long id) throws NotFoundExceptionEntity {
        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new NotFoundExceptionEntity("Invoice not found with id: " + id));
        return invoiceMapper.toDto(invoice);
    }

    @Override
    public InvoiceDto addInvoice(InvoiceToSaveDto invoiceToSaveDto) {
        Invoice invoice = invoiceMapper.saveDtoToEntity(invoiceToSaveDto);
        return invoiceMapper.toDto(invoiceRepository.save(invoice));
    }

    @Override
    public InvoiceDto updateInvoice(Long id, InvoiceToSaveDto invoiceToSaveDto) throws NotFoundExceptionEntity {
        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new NotFoundExceptionEntity("Invoice not found with id: " + id));

        // Update invoice fields here

        Invoice updatedInvoice = invoiceRepository.save(invoice);
        return invoiceMapper.toDto(updatedInvoice);
    }

    @Override
    public void deleteInvoice(Long id) {
        invoiceRepository.deleteById(id);
    }

    @Override
    public List<InvoiceDto> getAllInvoices() {
        List<Invoice> invoices = invoiceRepository.findAll();
        return invoices.stream()
                .map(invoiceMapper::toDto)
                .toList();
    }

    @Override
    public List<InvoiceDto> findInvoicesByYearAndMonths(Integer year, List<Integer> months) {
        List<Invoice> invoices = invoiceRepository.findInvoicesByYearAndMonths(year, months);
        return invoices.stream()
                .map(invoiceMapper::toDto)
                .toList();
    }

    @Override
    public List<InvoiceDto> findByPaymentDateYear(Integer year) {
        List<Invoice> invoices = invoiceRepository.findByPaymentDateYear(year);
        return invoices.stream()
                .map(invoiceMapper::toDto)
                .toList();
    }

    @Override
    public List<InvoiceDto> findByPaymentMethod(PaymentMethod paymentMethod) {
        List<Invoice> invoices = invoiceRepository.findByPaymentMethod(paymentMethod);
        return invoices.stream()
                .map(invoiceMapper::toDto)
                .toList();
    }

    @Override
    public List<InvoiceDto> findByPaymentMethodAndMonth(PaymentMethod paymentMethod, Integer month) {
        List<Invoice> invoices = invoiceRepository.findByPaymentMethodAndMonth(paymentMethod, month);
        return invoices.stream()
                .map(invoiceMapper::toDto)
                .toList();
    }

    @Override
    public List<InvoiceDto> findByPaymentMethodAndYear(PaymentMethod paymentMethod, Integer year) {
        List<Invoice> invoices = invoiceRepository.findByPaymentMethodAndYear(paymentMethod, year);
        return invoices.stream()
                .map(invoiceMapper::toDto)
                .toList();
    }
}
