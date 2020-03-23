package com.liziczh.erp.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.liziczh.erp.dto.PriceDoubleSerializer;
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
@Table(name = "lizi_erp_product")
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private long id; // 商品ID
    @Column(name = "name", unique = true, nullable = false)
    private String name; // 商品名称
    @Column(name = "code", unique = true, nullable = false)
    private String code; // 商品代码
    @ManyToOne
    @JoinColumn(name = "type", referencedColumnName = "id")
    private ProductType type; // 商品分类
    @Column(name = "spec", nullable = false)
    private String spec; // 商品规格
    @Column(name = "brand", nullable = false)
    private String brand; // 商品品牌
    @Column(name = "memo")
    private String memo; // 商品备注

    @Column(name = "createTime")
    private Date createTime; // 创建时间
    @Column(name = "updateTime")
    private Date updateTime; // 更新时间

}
