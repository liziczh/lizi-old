package com.liziczh.erp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "lizi_erp_customer")
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private long id; // 客户ID
    @Column(name = "name", unique = true, nullable = false)
    private String name; // 客户名称
    @Column(name = "code", unique = true, nullable = false)
    private String code; // 客户代码
    @ManyToOne
    @JoinColumn(name = "type", referencedColumnName = "id")
    private CustomerType type; // 客户类型
    @Column(name = "contact", nullable = false)
    private String contact; // 客户联系人
    @Column(name = "phone", nullable = false)
    private String phone; // 客户联系电话
    @Column(name = "phone2", nullable = false)
    private String phone2; // 客户联系电话2
    @Column(name = "email", nullable = false)
    private String email; // 客户邮箱

    @Column(name = "address")
    private String address; // 客户地址
    @Column(name = "memo")
    private String memo; // 客户备注

    @Column(name = "createTime")
    private Date createTime; // 创建时间
    @Column(name = "updateTime")
    private Date updateTime; // 更新时间


}
