package com.liziczh.erp.repository;

import com.liziczh.erp.entity.Warehouse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Description 仓库
 * @Author czh
 * @Date 2019/4/28
 */

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {

    Page<Warehouse> findByNameContaining(String search, Pageable pageable);
}
