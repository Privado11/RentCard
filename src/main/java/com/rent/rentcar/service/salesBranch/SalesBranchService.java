package com.rent.rentcar.service.salesBranch;

import com.rent.rentcar.dto.salesBranch.SalesBranchDto;
import com.rent.rentcar.dto.salesBranch.SalesBranchToSaveDto;
import com.rent.rentcar.exception.NotFoundExceptionEntity;

import java.util.List;

public interface SalesBranchService {
    SalesBranchDto getSalesBranchById(Long id) throws NotFoundExceptionEntity;
    SalesBranchDto addSalesBranch(SalesBranchToSaveDto salesBranchToSaveDto);
    SalesBranchDto updateSalesBranch(Long id, SalesBranchToSaveDto salesBranchToSaveDto) throws NotFoundExceptionEntity;
    void deleteSalesBranch(Long id);
    List<SalesBranchDto> getAllSalesBranches();
}
