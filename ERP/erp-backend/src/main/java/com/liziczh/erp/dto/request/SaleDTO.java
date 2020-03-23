package com.liziczh.erp.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleDTO {
    private Long productId; // 产品ID
    private Double salePrice; // 产品价格
    private Integer count; // 产品数量
    private Long customerId; // 客户ID
    private Long warehouseId; // 仓库ID
    private Long storeId; // 门店ID
    private String memo; // 备注
}
