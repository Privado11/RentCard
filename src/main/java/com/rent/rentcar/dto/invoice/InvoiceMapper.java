package com.rent.rentcar.dto.invoice;

import com.rent.rentcar.models.Invoice;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface InvoiceMapper {
    InvoiceMapper INSTANCE = Mappers.getMapper(InvoiceMapper.class);

    InvoiceDto toDto(Invoice invoice);
    Invoice toEntity(InvoiceDto invoiceDto);
    Invoice saveDtoToEntity(InvoiceToSaveDto invoiceToSaveDto);

}
