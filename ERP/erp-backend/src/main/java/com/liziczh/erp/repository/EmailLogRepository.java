package com.liziczh.erp.repository;


import com.liziczh.erp.entity.EmailLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Description 邮件日志
 * @Author czh
 * @Date 2018/10/8
 */

@Repository
public interface EmailLogRepository extends JpaRepository<EmailLog, Long> {

    /**
     * 根据用户ID查询邮件日志信息
     *
     * @param userId
     * @return
     */
    Page<EmailLog> findByUserId(long userId, Pageable pageable);

}
