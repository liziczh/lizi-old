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
@Table(name = "lizi_erp_order_purchase")
public class PurchaseRecord implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private long id; // 采购记录ID
    @Column(name = "type", nullable = false)
    private String type; // 记录类型（采购入库"in"/采购退货"out"）
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product; // 商品
    @Column(name = "purchase_price", nullable = false)
    @JsonSerialize(using = PriceDoubleSerializer.class)
    private double purchasePrice; // 商品采购价
    @Column(name = "count")
    private int count; // 数量
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "supplier_id", referencedColumnName = "id")
    private Supplier supplier; // 供应商
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "warehouse_id", referencedColumnName = "id")
    private Warehouse warehouse; // 仓库
    @Column(name = "date")
    private Date date; // 记录日期
    @Column(name = "memo")
    private String memo; // 备注

}
