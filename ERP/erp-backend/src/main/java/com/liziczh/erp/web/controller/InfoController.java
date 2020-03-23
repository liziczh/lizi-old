package com.liziczh.erp.web.controller;

import com.liziczh.erp.common.code.BaseRestCode;
import com.liziczh.erp.common.response.BaseRestResponse;
import com.liziczh.erp.common.response.PageRestResponse;
import com.liziczh.erp.entity.*;
import com.liziczh.erp.service.InfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Jehaw-Chen
 */

@Api(tags = "基础资料")
@RestController
@RequestMapping("api/erp/info")
public class InfoController {

    @Autowired
    private InfoService infoService;

    @ApiOperation(value = "分页查找所有供应商")
    @GetMapping("/suppliers")
    public PageRestResponse pagingQuerySupplierList(@RequestParam Integer typeId, @RequestParam String search, @RequestParam Integer currentPage, @RequestParam Integer limit) {
        Page<Supplier> response = infoService.pagingQuerySupplierList(typeId, search, currentPage, limit);
        return PageRestResponse
                .success()
                .data(response.getContent())
                .currentPage(currentPage)
                .limit(limit)
                .currentPage(response.getTotalPages())
                .totalRecord(response.getTotalElements())
                .build();
    }

    @ApiOperation(value = "查找所有供应商")
    @GetMapping("/suppliers/all")
    public BaseRestResponse getAllSupplier() {
        List<Supplier> supplierList = infoService.findAllSupplier();
        return BaseRestResponse.ok(supplierList);
    }

    @ApiOperation(value = "查找所有供应商分类")
    @GetMapping("/supplier/types")
    public BaseRestResponse getAllSupplierType() {
        List<SupplierType> supplierTypeList = infoService.findAllSupplierType();
        return BaseRestResponse.ok(supplierTypeList);
    }

    @ApiOperation(value = "添加供应商")
    @PostMapping("/supplier")
    public BaseRestResponse addSupplier(@RequestBody Supplier supplier) {
        Supplier addSupplier = infoService.addSupplier(supplier);
        return BaseRestResponse.ok(addSupplier);
    }

    @ApiOperation(value = "修改供应商")
    @PutMapping("/supplier")
    public BaseRestResponse modifySupplier(@RequestBody Supplier supplier) {
        Supplier modifySupplier = infoService.modifySupplier(supplier);
        return BaseRestResponse.ok(modifySupplier);
    }

    @ApiOperation(value = "移除供应商")
    @DeleteMapping("/supplier")
    public BaseRestResponse removeSupplier(@RequestParam long supplierId) {
        infoService.removeSupplier(supplierId);
        return BaseRestResponse.ok();
    }

    @ApiOperation(value = "分页查找所有客户")
    @GetMapping("/customers")
    public PageRestResponse pagingQueryCustomerList(@RequestParam Integer typeId, @RequestParam String search, @RequestParam Integer currentPage, @RequestParam Integer limit) {
        Page<Customer> response = infoService.pagingQueryCustomerList(typeId, search, currentPage, limit);
        return PageRestResponse
                .success()
                .data(response.getContent())
                .currentPage(currentPage)
                .limit(limit)
                .currentPage(response.getTotalPages())
                .totalRecord(response.getTotalElements())
                .build();
    }

    @ApiOperation(value = "查找所有客户")
    @GetMapping("/customers/all")
    public BaseRestResponse getAllCustomer() {
        List<Customer> customerList = infoService.findAllCustomer();
        return BaseRestResponse.ok(customerList);
    }

    @ApiOperation(value = "查找所有客户分类")
    @GetMapping("/customer/types")
    public BaseRestResponse getAllCustomerType() {
        List<CustomerType> customerTypeList = infoService.findAllCustomerType();
        return BaseRestResponse.ok(customerTypeList);
    }

    @ApiOperation(value = "添加客户")
    @PostMapping("/customer")
    public BaseRestResponse addCustomer(@RequestBody Customer customer) {
        Customer addCustomer = infoService.addCustomer(customer);
        return BaseRestResponse.ok(addCustomer);
    }

    @ApiOperation(value = "修改客户")
    @PutMapping("/customer")
    public BaseRestResponse modifyCustomer(@RequestBody Customer customer) {
        Customer modifyCustomer = infoService.modifyCustomer(customer);
        return BaseRestResponse.ok(modifyCustomer);
    }

    @ApiOperation(value = "移除客户")
    @DeleteMapping("/customer")
    public BaseRestResponse removeCustomer(@RequestParam long customerId) {
        infoService.removeCustomer(customerId);
        return BaseRestResponse.ok();
    }

    @ApiOperation(value = "分页查找门店")
    @GetMapping("/stores")
    public PageRestResponse pagingQueryStoreList(@RequestParam String search, @RequestParam Integer currentPage, @RequestParam Integer limit) {
        Page<Store> response = infoService.pagingQueryStoreList(search, currentPage, limit);
        return PageRestResponse
                .success()
                .data(response.getContent())
                .currentPage(currentPage)
                .limit(limit)
                .currentPage(response.getTotalPages())
                .totalRecord(response.getTotalElements())
                .build();
    }

    @ApiOperation(value = "查找所有门店")
    @GetMapping("/stores/all")
    public BaseRestResponse getAllStore() {
        List<Store> storeList = infoService.findAllStore();
        return BaseRestResponse.ok(storeList);
    }

    @ApiOperation(value = "添加门店")
    @PostMapping("/store")
    public BaseRestResponse addStore(@RequestBody Store store) {
        Store addStore = infoService.addStore(store);
        return BaseRestResponse.ok(addStore);
    }

    @ApiOperation(value = "修改门店")
    @PutMapping("/store")
    public BaseRestResponse modifyStore(@RequestBody Store store) {
        Store modifyStore = infoService.modifyStore(store);
        return BaseRestResponse.ok(modifyStore);
    }

    @ApiOperation(value = "移除门店")
    @DeleteMapping("/store")
    public BaseRestResponse removeStore(@RequestParam long storeId) {
        infoService.removeStore(storeId);
        return BaseRestResponse.ok();
    }

    @ApiOperation(value = "分页查找仓库")
    @GetMapping("/warehouses")
    public PageRestResponse pagingQueryWarehouseList(@RequestParam String search, @RequestParam Integer currentPage, @RequestParam Integer limit) {
        Page<Warehouse> response = infoService.pagingQueryWarehouseList(search, currentPage, limit);
        return PageRestResponse
                .success()
                .data(response.getContent())
                .currentPage(currentPage)
                .limit(limit)
                .currentPage(response.getTotalPages())
                .totalRecord(response.getTotalElements())
                .build();
    }

    @ApiOperation(value = "查找所有仓库")
    @GetMapping("/warehouses/all")
    public BaseRestResponse getAllWarehouse() {
        List<Warehouse> warehouseList = infoService.findAllWarehouse();
        return BaseRestResponse.ok(warehouseList);
    }

    @ApiOperation(value = "添加仓库")
    @PostMapping("/warehouse")
    public BaseRestResponse addWarehouse(@RequestBody Warehouse warehouse) {
        Warehouse addWarehouse = infoService.addWarehouse(warehouse);
        return BaseRestResponse.ok(addWarehouse);
    }

    @ApiOperation(value = "修改仓库")
    @PutMapping("/warehouse")
    public BaseRestResponse modifyWarehouse(@RequestBody Warehouse warehouse) {
        Warehouse modifyWarehouse = infoService.modifyWarehouse(warehouse);
        return BaseRestResponse.ok(modifyWarehouse);
    }

    @ApiOperation(value = "移除仓库")
    @DeleteMapping("/warehouse")
    public BaseRestResponse removeWarehouse(@RequestParam long warehouseId) {
        infoService.removeWarehouse(warehouseId);
        return BaseRestResponse.ok();
    }

    @ApiOperation(value = "分页查找商品")
    @GetMapping("/products")
    public PageRestResponse pagingQueryProductList(@RequestParam Integer typeId, @RequestParam String search, @RequestParam Integer currentPage, @RequestParam Integer limit) {
        Page<Product> response = infoService.pagingQueryProductList(typeId, search, currentPage, limit);
        return PageRestResponse
                .success()
                .data(response.getContent())
                .currentPage(currentPage)
                .limit(limit)
                .currentPage(response.getTotalPages())
                .totalRecord(response.getTotalElements())
                .build();
    }

    @ApiOperation(value = "查找所有产品")
    @GetMapping("/products/all")
    public BaseRestResponse getAllProduct() {
        List<Product> productList = infoService.findAllProduct();
        return BaseRestResponse.ok(productList);
    }

    @ApiOperation(value = "查找所有产品分类")
    @GetMapping("/product/types")
    public BaseRestResponse getAllProductType() {
        List<ProductType> productTypeList = infoService.findAllProductType();
        return BaseRestResponse.ok(productTypeList);
    }

    @ApiOperation(value = "添加产品")
    @PostMapping("/product")
    public BaseRestResponse addProduct(@RequestBody Product product) {
        Product addProduct = infoService.addProduct(product);
        return BaseRestResponse.ok(addProduct);
    }

    @ApiOperation(value = "修改产品")
    @PutMapping("/product")
    public BaseRestResponse modifyProduct(@RequestBody Product product) {
        Product modifyProduct = infoService.modifyProduct(product);
        return BaseRestResponse.ok(modifyProduct);
    }

    @ApiOperation(value = "移除产品")
    @DeleteMapping("/product")
    public BaseRestResponse removeProduct(@RequestParam long productId) {
        if (infoService.removeProduct(productId)) {
            return BaseRestResponse.ok();
        } else {
            return BaseRestResponse.of(BaseRestCode.REST_BAD_REQUEST, "库存中仍存在当前商品，无法删除");
        }
    }

}
