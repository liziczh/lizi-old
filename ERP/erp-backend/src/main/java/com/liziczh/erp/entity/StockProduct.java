package com.liziczh.erp.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.liziczh.erp.dto.PriceDoubleSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "lizi_erp_stock_product")
public class StockProduct implements Serializable {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private long id; // 库存商品ID

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "warehouse_id", referencedColumnName = "id")
    private Warehouse warehouse; // 仓库

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product; // 商品

    @Column(name = "count")
    private int count; // 数量

}
