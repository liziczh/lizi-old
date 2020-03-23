package com.liziczh.erp.service;

import com.liziczh.erp.dto.request.PurchaseDTO;
import com.liziczh.erp.dto.request.SaleDTO;
import com.liziczh.erp.dto.request.StockAllotDTO;
import com.liziczh.erp.entity.*;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PSIService {

    /**
     * 分页条件查询采购记录
     * @param startTime
     * @param endTime
     * @param currentPage
     * @param limit
     * @return
     */
    Page<PurchaseRecord> pagingQueryPurchaseRecordList(String startTime, String endTime, Integer currentPage, Integer limit);

    /**
     * 查询所有采购记录
     * @return
     */
    List<PurchaseRecord> findAllPurchaseRecord();

    /**
     * 采购入库
     * @return
     */
    PurchaseRecord purchaseInStock(PurchaseDTO purchaseDTO);

    /**
     * 采购退货
     * @return
     */
    PurchaseRecord purchaseRefund(PurchaseDTO purchaseDTO);

    /**
     * 分页条件查询销售订单
     * @param startTime
     * @param endTime
     * @param currentPage
     * @param limit
     * @return
     */
    Page<SaleRecord> pagingQuerySaleRecordList(String startTime, String endTime, Integer currentPage, Integer limit);

    /**
     * 查询所有销售记录
     * @return
     */
    List<SaleRecord> findAllSaleRecord();

    /**
     * 销售出库
     * @return
     */
    SaleRecord saleOutStock(SaleDTO saleDTO);

    /**
     * 销售退货
     * @return
     */
    SaleRecord saleRefund(SaleDTO saleDTO);

    /**
     * 查询所有库存商品
     * @return
     */
    List<StockProduct> findAllStockProduct();

    /**
     * 分页条件查询库存商品
     * @param warehouseId
     * @param currentPage
     * @param limit
     * @return
     */
    Page<StockProduct> pagingQueryStockList(Integer warehouseId, Integer currentPage, Integer limit);

    /**
     * 查询某仓库下所有商品
     * @param warehouseId
     * @return
     */
    List<Product> findProductListByWarehouseId(Long warehouseId);

    /**
     * 查询存在某商品的所有仓库
     * @param productId
     * @return
     */
    List<Warehouse> findWarehouseListByProductId(Long productId);

    /**
     * 商品内部调拨
     * @param stockAllotDTO
     * @return
     */
    StockProduct stockAllot(StockAllotDTO stockAllotDTO);

}
