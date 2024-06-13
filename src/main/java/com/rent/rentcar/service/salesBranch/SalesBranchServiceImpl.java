package com.rent.rentcar.service.salesBranch;

import com.rent.rentcar.dto.salesBranch.SalesBranchDto;
import com.rent.rentcar.dto.salesBranch.SalesBranchMapper;
import com.rent.rentcar.dto.salesBranch.SalesBranchToSaveDto;
import com.rent.rentcar.exception.NotFoundExceptionEntity;
import com.rent.rentcar.models.SalesBranch;
import com.rent.rentcar.repository.SalesBranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalesBranchServiceImpl implements SalesBranchService{
    private final SalesBranchRepository salesBranchRepository;
    private final SalesBranchMapper salesBranchMapper;

    @Autowired
    public SalesBranchServiceImpl(SalesBranchRepository salesBranchRepository, SalesBranchMapper salesBranchMapper) {
        this.salesBranchRepository = salesBranchRepository;
        this.salesBranchMapper = salesBranchMapper;
    }

    @Override
    public SalesBranchDto getSalesBranchById(Long id) throws NotFoundExceptionEntity {
        SalesBranch salesBranch = salesBranchRepository.findById(id)
                .orElseThrow(() -> new NotFoundExceptionEntity("Sales branch not found with id: " + id));
        return salesBranchMapper.toDto(salesBranch);
    }

    @Override
    public SalesBranchDto addSalesBranch(SalesBranchToSaveDto salesBranchToSaveDto) {
        SalesBranch salesBranch = salesBranchMapper.saveDtoToEntity(salesBranchToSaveDto);
        return salesBranchMapper.toDto(salesBranchRepository.save(salesBranch));
    }

    @Override
    public SalesBranchDto updateSalesBranch(Long id, SalesBranchToSaveDto salesBranchToSaveDto) throws NotFoundExceptionEntity {
        SalesBranch salesBranch = salesBranchRepository.findById(id)
                .orElseThrow(() -> new NotFoundExceptionEntity("Sales branch not found with id: " + id));

        SalesBranch updatedSalesBranch = salesBranchRepository.save(salesBranch);
        return salesBranchMapper.toDto(updatedSalesBranch);
    }

    @Override
    public void deleteSalesBranch(Long id) {
        salesBranchRepository.deleteById(id);
    }

    @Override
    public List<SalesBranchDto> getAllSalesBranches() {
        List<SalesBranch> salesBranches = salesBranchRepository.findAll();
        return salesBranches.stream()
                .map(salesBranchMapper::toDto)
                .toList();
    }
}
