package com.liziczh.erp.repository;

import com.liziczh.erp.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Description 产品
 * @Author czh
 * @Date 2019/4/28
 */

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Page<Product> findByType_IdAndNameContaining(Integer typeId, String search, Pageable pageable);

    Page<Product> findByNameContaining(String search, Pageable pageable);
}
