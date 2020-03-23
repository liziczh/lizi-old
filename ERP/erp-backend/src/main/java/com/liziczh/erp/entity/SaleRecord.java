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
@Table(name = "lizi_erp_order_sale")
public class SaleRecord implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private long id; // 销售记录ID
    @Column(name = "type", nullable = false)
    private String type; // 记录类型（销售出库"out"/销售退货"in"）
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product; // 商品
    @Column(name = "sale_price", nullable = false)
    @JsonSerialize(using = PriceDoubleSerializer.class)
    private double salePrice; // 商品销售价
    @Column(name = "count")
    private int count; // 数量
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer; // 客户
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "warehouse_id", referencedColumnName = "id")
    private Warehouse warehouse; // 仓库
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "store_id", referencedColumnName = "id")
    private Store store; // 门店
    @Column(name = "date")
    private Date date; // 记录时间
    @Column(name = "memo")
    private String memo; // 备注

}
