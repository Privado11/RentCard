package com.rent.rentcar.controller;

import com.rent.rentcar.dto.salesBranch.SalesBranchDto;
import com.rent.rentcar.dto.salesBranch.SalesBranchToSaveDto;
import com.rent.rentcar.exception.NotFoundExceptionEntity;
import com.rent.rentcar.service.salesBranch.SalesBranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sales-branches")
public class SalesBranchController {

    private final SalesBranchService salesBranchService;

    @Autowired
    public SalesBranchController(SalesBranchService salesBranchService) {
        this.salesBranchService = salesBranchService;
    }

    @GetMapping
    public ResponseEntity<List<SalesBranchDto>> getAllSalesBranches() {
        List<SalesBranchDto> salesBranches = salesBranchService.getAllSalesBranches();
        return ResponseEntity.ok(salesBranches);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SalesBranchDto> getSalesBranchById(@PathVariable Long id) {
        try {
            SalesBranchDto salesBranchDto = salesBranchService.getSalesBranchById(id);
            return ResponseEntity.ok(salesBranchDto);
        } catch (NotFoundExceptionEntity ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<SalesBranchDto> addSalesBranch(@RequestBody SalesBranchToSaveDto salesBranchToSaveDto) {
        SalesBranchDto savedSalesBranch = salesBranchService.addSalesBranch(salesBranchToSaveDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedSalesBranch);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SalesBranchDto> updateSalesBranch(@PathVariable Long id, @RequestBody SalesBranchToSaveDto salesBranchToSaveDto) {
        try {
            SalesBranchDto updatedSalesBranch = salesBranchService.updateSalesBranch(id, salesBranchToSaveDto);
            return ResponseEntity.ok(updatedSalesBranch);
        } catch (NotFoundExceptionEntity ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSalesBranch(@PathVariable Long id) {
        salesBranchService.deleteSalesBranch(id);
        return ResponseEntity.noContent().build();
    }
}
