package com.liziczh.erp.repository;

import com.liziczh.erp.entity.PurchaseRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface PurchaseRecordRepository extends JpaRepository<PurchaseRecord, Long> {

    Page<PurchaseRecord> findByDateBetween(Date startTime, Date endTime, Pageable pageable);

    List<PurchaseRecord> findAllByOrderByDateDesc();
}
