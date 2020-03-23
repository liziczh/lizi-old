package com.liziczh.erp.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PurchaseDTO {
    private Long productId; // 产品ID
    private Double purchasePrice; // 产品价格
    private Integer count; // 产品数量
    private Long supplierId; // 供应商ID
    private Long warehouseId; // 仓库ID
    private String memo; // 备注
}
