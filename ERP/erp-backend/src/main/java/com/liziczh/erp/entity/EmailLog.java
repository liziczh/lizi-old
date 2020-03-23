package com.liziczh.erp.entity;


import com.liziczh.erp.dto.enumeration.EmailType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description 邮件日志
 * @Author czh
 * @Date 2018/10/8
 */

@Entity
@Table(name = "lizi_erp_email_log")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailLog implements Serializable {

    private static final long serialVersionUID = 2901337024026050596L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private long id; //  id
    @Column(name = "user_id")
    private long userId; // 用户ID
    @Column(name = "type")
    private EmailType type; // 邮件类型（枚举）
    @Column(name = "message")
    private String message; // 邮件类型备注
    @Column(name = "email_subject")
    private String emailSubject; // 邮件标题
    @Column(name = "email_text")
    private String emailText; // 邮件内容
    @Column(name = "email_sent_date")
    private Date emailSentDate; // 发件日期
    @Column(name = "email_from")
    private String emailFrom; // 发件人
    @Column(name = "email_to")
    private String emailTo; // 收件人
    @Column(name = "email_cc")
    private String emailCc; // 抄送
    @Column(name = "email_bcc")
    private String emailBcc; // 密送
    @Column(name = "status")
    private boolean status; // 邮件状态（0 发送失败，1 发送成功）
    @Column(name = "createTime")
    private Date createTime; // 数据创建时间
    @Column(name = "log")
    private String log; // 发送失败日志记录
}
