package com.liziczh.erp.repository;

import com.liziczh.erp.entity.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Description 供应商
 * @Author czh
 * @Date 2019/4/28
 */

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    Page<Supplier> findByType_IdAndNameContaining(Integer typeId, String search, Pageable pageable);

    Page<Supplier> findByNameContaining(String search, Pageable pageable);

}
