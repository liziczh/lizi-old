package com.liziczh.erp.repository;

import com.liziczh.erp.entity.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Description 门店
 * @Author czh
 * @Date 2019/4/28
 */

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {

    Page<Store> findByNameContaining(String search, Pageable pageable);
}
