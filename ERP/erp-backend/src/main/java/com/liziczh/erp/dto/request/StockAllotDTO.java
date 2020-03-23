package com.liziczh.erp.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockAllotDTO implements Serializable {
    private Long warehouseId;
    private Long productId;
    private Integer count;
    private Long allotWarehouseId;
}
