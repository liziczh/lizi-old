package com.liziczh.erp.repository;

import com.liziczh.erp.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Description 客户
 * @Author czh
 * @Date 2019/4/28
 */

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Page<Customer> findByType_IdAndNameContaining(Integer typeId, String search, Pageable pageable);

    Page<Customer> findByNameContaining(String search, Pageable pageable);

}
