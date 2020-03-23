package com.liziczh.erp.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "lizi_erp_user")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private long id;  // 用户ID
    @Column(name = "username", unique = true, nullable = false)
    private String username;  // 用户名
    @JsonIgnore
    @Column(name = "password", nullable = false)
    private String password;  // 用户密码
    @Column(name = "name", nullable = false)
    private String name;  // 用户姓名
    @Column(name = "email", unique = true)
    private String email;  // 用户邮箱
    @Column(name = "phone")
    private String phone; // 用户手机号
    @ManyToOne
    @JoinColumn(name = "role", referencedColumnName = "id")
    private UserRole role;

    @Column(name = "createTime")
    private Date createTime; // 创建时间
    @Column(name = "updateTime")
    private Date updateTime; // 更新时间

}
