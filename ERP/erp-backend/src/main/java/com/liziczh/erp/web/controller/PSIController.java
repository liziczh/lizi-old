package com.liziczh.erp.web.controller;

import com.liziczh.erp.common.code.BaseRestCode;
import com.liziczh.erp.common.response.BaseRestResponse;
import com.liziczh.erp.common.response.PageRestResponse;
import com.liziczh.erp.dto.request.PurchaseDTO;
import com.liziczh.erp.dto.request.SaleDTO;
import com.liziczh.erp.dto.request.StockAllotDTO;
import com.liziczh.erp.entity.*;
import com.liziczh.erp.service.PSIService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Jehaw-Chen
 */
@Api(tags = "进销存")
@RestController
@RequestMapping("api/erp/psi")
public class PSIController {

    @Autowired
    private PSIService psiService;

    @ApiOperation(value = "分页查找所有采购记录")
    @GetMapping("/records/purchase")
    public PageRestResponse pagingQueryPurchaseRecordList(
//            @RequestParam Integer customerId, @RequestParam Integer warehouseId, @RequestParam Integer typeId,
            @RequestParam String startTime, @RequestParam String endTime,
            @RequestParam Integer currentPage, @RequestParam Integer limit) {
        Page<PurchaseRecord> response = psiService.pagingQueryPurchaseRecordList(startTime, endTime, currentPage, limit);
        return PageRestResponse
                .success()
                .data(response.getContent())
                .currentPage(currentPage)
                .limit(limit)
                .currentPage(response.getTotalPages())
                .totalRecord(response.getTotalElements())
                .build();
    }

    @ApiOperation(value = "查找所有采购记录")
    @GetMapping("/records/purchase/all")
    public BaseRestResponse getAllPurchaseRecord() {
        List<PurchaseRecord> purchaseRecordList = psiService.findAllPurchaseRecord();
        return BaseRestResponse.ok(purchaseRecordList);
    }

    @ApiOperation(value = "采购入库")
    @PostMapping("/purchase/in")
    public BaseRestResponse purchaseInStock(@RequestBody PurchaseDTO purchaseDTO) {
        PurchaseRecord purchaseRecord = psiService.purchaseInStock(purchaseDTO);
        return BaseRestResponse.ok(purchaseRecord);
    }

    @ApiOperation(value = "采购退货")
    @PostMapping("/purchase/refund")
    public BaseRestResponse purchaseRefund(@RequestBody PurchaseDTO purchaseDTO) {
        PurchaseRecord purchaseRecord = psiService.purchaseRefund(purchaseDTO);
        if(purchaseRecord == null){
            return BaseRestResponse.of(BaseRestCode.REST_BAD_REQUEST, "库存不足");
        }
        return BaseRestResponse.ok(purchaseRecord);
    }

    @ApiOperation(value = "分页查找所有销售记录")
    @GetMapping("/records/sale")
    public PageRestResponse pagingQuerySaleRecordList(
//            @RequestParam Integer customerId, @RequestParam Integer warehouseId,
//            @RequestParam Integer storeId,@RequestParam Integer typeId,
            @RequestParam String startTime, @RequestParam String endTime,
            @RequestParam Integer currentPage, @RequestParam Integer limit) {
        Page<SaleRecord> response = psiService.pagingQuerySaleRecordList(startTime, endTime, currentPage, limit);
        return PageRestResponse
                .success()
                .data(response.getContent())
                .currentPage(currentPage)
                .limit(limit)
                .currentPage(response.getTotalPages())
                .totalRecord(response.getTotalElements())
                .build();
    }

    @ApiOperation(value = "查找所有销售记录")
    @GetMapping("/records/sale/all")
    public BaseRestResponse getAllSaleRecord() {
        List<SaleRecord> saleRecordList = psiService.findAllSaleRecord();
        return BaseRestResponse.ok(saleRecordList);
    }

    @ApiOperation(value = "销售出库")
    @PostMapping("/sale/out")
    public BaseRestResponse saleOutStock(@RequestBody SaleDTO saleDTO) {
        SaleRecord saleRecord = psiService.saleOutStock(saleDTO);
        if(saleRecord == null){
            return BaseRestResponse.of(BaseRestCode.REST_BAD_REQUEST, "库存不足");
        }
        return BaseRestResponse.ok(saleRecord);
    }

    @ApiOperation(value = "销售退货")
    @PostMapping("/sale/refund")
    public BaseRestResponse saleRefund(@RequestBody SaleDTO saleDTO) {
        SaleRecord saleRecord = psiService.saleRefund(saleDTO);
        return BaseRestResponse.ok(saleRecord);
    }

    @ApiOperation(value = "分页条件查找所有库存")
    @GetMapping("/stock")
    public PageRestResponse pagingQueryStockList(
            @RequestParam Integer warehouseId,
            @RequestParam Integer currentPage, @RequestParam Integer limit) {
        Page<StockProduct> response = psiService.pagingQueryStockList(warehouseId, currentPage, limit);
        return PageRestResponse
                .success()
                .data(response.getContent())
                .currentPage(currentPage)
                .limit(limit)
                .currentPage(response.getTotalPages())
                .totalRecord(response.getTotalElements())
                .build();
    }

    @ApiOperation(value = "查找所有库存")
    @GetMapping("/stock/all")
    public BaseRestResponse getAllStock() {
        List<StockProduct> stockList = psiService.findAllStockProduct();
        return BaseRestResponse.ok(stockList);
    }

    @ApiOperation(value = "查找某仓库下的商品列表")
    @GetMapping("/stock/products")
    public BaseRestResponse getProductListByWarehouseId(@RequestParam Long warehouseId) {
        List<Product> productList = psiService.findProductListByWarehouseId(warehouseId);
        return BaseRestResponse.ok(productList);
    }

    @ApiOperation(value = "查找存在某商品的仓库列表")
    @GetMapping("/stock/warehouses")
    public BaseRestResponse getWarehouseListByProductId(@RequestParam Long productId) {
        List<Warehouse> warehouseList = psiService.findWarehouseListByProductId(productId);
        return BaseRestResponse.ok(warehouseList);
    }

    @ApiOperation(value = "商品内部调拨")
    @PutMapping("/stock/allot")
    public BaseRestResponse stockAllot(@RequestBody StockAllotDTO stockAllotDTO) {
        StockProduct stockProduct = psiService.stockAllot(stockAllotDTO);
        if(stockProduct == null){
            return BaseRestResponse.of(BaseRestCode.REST_BAD_REQUEST, "库存不足");
        }
        return BaseRestResponse.ok(stockProduct);
    }

}
