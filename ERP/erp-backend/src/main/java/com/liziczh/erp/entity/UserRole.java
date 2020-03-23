package com.liziczh.erp.entity;

import com.liziczh.erp.dto.constant.Constants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "lizi_erp_user_role")
public class UserRole implements Serializable {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private int id;  // 角色ID
    @Column(name = "name", unique = true, nullable = false)
    private String name;  // 角色
    @Column(name = "code", unique = true, nullable = false)
    private String code;  // 标识码
}
