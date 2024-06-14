package com.rent.rentcar.service.invoice;

import com.rent.rentcar.Enum.PaymentMethod;
import com.rent.rentcar.dto.invoice.InvoiceDto;
import com.rent.rentcar.dto.invoice.InvoiceToSaveDto;
import com.rent.rentcar.exception.NotFoundExceptionEntity;

import java.util.List;

public interface InvoiceService {
    InvoiceDto getInvoiceById(Long id) throws NotFoundExceptionEntity;
    InvoiceDto addInvoice(InvoiceToSaveDto invoiceToSaveDto);
    InvoiceDto updateInvoice(Long id, InvoiceToSaveDto invoiceToSaveDto) throws NotFoundExceptionEntity;
    void deleteInvoice(Long id);
    List<InvoiceDto> getAllInvoices();
    List<InvoiceDto> findInvoicesByYearAndMonths(Integer year, List<Integer> months);
    List<InvoiceDto> findByPaymentDateYear(Integer year);
    List<InvoiceDto> findByPaymentMethod(PaymentMethod paymentMethod);
    List<InvoiceDto> findByPaymentMethodAndMonth(PaymentMethod paymentMethod, Integer month);
    List<InvoiceDto> findByPaymentMethodAndYear(PaymentMethod paymentMethod, Integer year);
}
