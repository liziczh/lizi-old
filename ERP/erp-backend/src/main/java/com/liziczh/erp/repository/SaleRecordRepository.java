package com.liziczh.erp.repository;

import com.liziczh.erp.entity.SaleRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface SaleRecordRepository extends JpaRepository<SaleRecord, Long> {

    Page<SaleRecord> findByDateBetween(Date startTime, Date endTime, Pageable pageable);

    List<SaleRecord> findAllByOrderByDateDesc();
}
