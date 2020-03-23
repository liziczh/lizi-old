package com.liziczh.erp.repository;

import com.liziczh.erp.entity.SupplierType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierTypeRepository extends JpaRepository<SupplierType, Integer> {
}
