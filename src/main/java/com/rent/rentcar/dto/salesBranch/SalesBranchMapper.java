package com.rent.rentcar.dto.salesBranch;

import com.rent.rentcar.models.SalesBranch;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SalesBranchMapper {
    SalesBranchMapper INSTANCE = Mappers.getMapper(SalesBranchMapper.class);

    SalesBranchDto toDto(SalesBranch salesBranch);
    SalesBranch saveDtoToEntity(SalesBranchToSaveDto salesBranchToSaveDto);
    SalesBranch toEntity(SalesBranchDto salesBranchDto);
}
