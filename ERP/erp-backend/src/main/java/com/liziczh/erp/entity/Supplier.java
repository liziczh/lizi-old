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
@Table(name = "lizi_erp_supplier")
public class Supplier implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private long id; // 供应商ID
    @Column(name = "name", unique = true, nullable = false)
    private String name; // 供应商名称
    @Column(name = "code", unique = true, nullable = false)
    private String code; // 供应商代码
    @ManyToOne
    @JoinColumn(name = "type", referencedColumnName = "id")
    private SupplierType type; // 供应商类型
    @Column(name = "contact", nullable = false)
    private String contact; // 供应商联系人
    @Column(name = "email", nullable = false)
    private String email; // 供应商邮箱
    @Column(name = "phone", nullable = false)
    private String phone; // 供应商联系电话
    @Column(name = "phone2", nullable = false)
    private String phone2; // 供应商联系电话2

    @Column(name = "account", nullable = false)
    private String account; // 供应商账户

    @Column(name = "address")
    private String address; // 供应商地址
    @Column(name = "memo")
    private String memo; // 供应商备注

    @Column(name = "createTime")
    private Date createTime; // 创建时间
    @Column(name = "updateTime")
    private Date updateTime; // 更新时间

}
