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
@Table(name = "lizi_erp_store")
public class Store implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private long id; // 门店ID
    @Column(name = "name", unique = true, nullable = false)
    private String name; // 门店名称
    @Column(name = "code", unique = true, nullable = false)
    private String code; // 门店Code
    @Column(name = "manager", nullable = false)
    private String manager; // 门店负责人
    @Column(name = "phone", nullable = false)
    private String phone; // 门店电话
    @Column(name = "address")
    private String address; // 门店地址
    @Column(name = "memo")
    private String memo; // 门店备注

    @Column(name = "createTime")
    private Date createTime; // 创建时间
    @Column(name = "updateTime")
    private Date updateTime; // 更新时间

}
