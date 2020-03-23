package com.liziczh.erp.repository;

import com.liziczh.erp.entity.StockProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StockProductRepository extends JpaRepository<StockProduct, Long> {

    Page<StockProduct> findByWarehouse_Id(long warehouseId, Pageable pageable);

    Optional<StockProduct> findByProduct_IdAndWarehouse_Id(long productId, long warehouseId);

    List<StockProduct> findByProduct_Id(long productId);

    List<StockProduct> findByWarehouse_Id(long warehouseId);

}
