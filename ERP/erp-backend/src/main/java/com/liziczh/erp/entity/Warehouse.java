package com.liziczh.erp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "lizi_erp_warehouse")
public class Warehouse implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private long id; // 仓库ID
    @Column(name = "name", unique = true, nullable = false)
    private String name; // 仓库名称
    @Column(name = "code", unique = true, nullable = false)
    private String code; // 仓库Code
    @Column(name = "manager", nullable = false)
    private String manager; // 仓库负责人
    @Column(name = "phone", nullable = false)
    private String phone; // 仓库电话
    @Column(name = "address")
    private String address; // 仓库地址
    @Column(name = "memo")
    private String memo; // 仓库备注

    @Column(name = "createTime")
    private Date createTime; // 创建时间
    @Column(name = "updateTime")
    private Date updateTime; // 更新时间

}
