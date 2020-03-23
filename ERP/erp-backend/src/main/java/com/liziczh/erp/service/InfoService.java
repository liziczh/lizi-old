package com.liziczh.erp.service;

import com.liziczh.erp.entity.*;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @Description 基础资料服务
 * @Author czh
 * @Date 2019/4/28
 */

public interface InfoService {

    /**************************************供应商*********************************************/
    /**
     * 分页查询供应商
     * @param typeId
     * @param search
     * @param currentPage
     * @param limit
     * @return
     */
    Page<Supplier> pagingQuerySupplierList(Integer typeId, String search, Integer currentPage, Integer limit);

    /**
     * 查询所有供应商
     *
     * @return List<Supplier>
     */
    List<Supplier> findAllSupplier();

    /**
     * 查询所有供应商分类
     * @return
     */
    List<SupplierType> findAllSupplierType();

    /**
     * 根据供应商ID查询单个供应商
     *
     * @param supplierId
     * @return Supplier
     */
    Supplier findSupplierBySupplierId(long supplierId);

    /**
     * 新增供应商信息
     *
     * @param supplier
     * @return Supplier
     */
    Supplier addSupplier(Supplier supplier);

    /**
     * 保存供应商信息
     *
     * @param supplier
     * @return Supplier
     */
    Supplier modifySupplier(Supplier supplier);

    /**
     * 移除供应商
     *
     * @param supplierId
     */
    void removeSupplier(long supplierId);


    /**************************************客户*********************************************/

    /**
     * 分页查找客户
     * @param typeId
     * @param search
     * @param currentPage
     * @param limit
     * @return
     */
    Page<Customer> pagingQueryCustomerList(Integer typeId, String search, Integer currentPage, Integer limit);

    /**
     * 查询所有客户
     *
     * @return List<Customer>
     */
    List<Customer> findAllCustomer();

    /**
     * 查询所有客户分类
     * @return
     */
    List<CustomerType> findAllCustomerType();

    /**
     * 根据客户ID查询单个客户
     *
     * @param customerId
     * @return Customer
     */
    Customer findCustomerByCustomerId(long customerId);

    /**
     * 添加客户信息
     *
     * @param customer
     * @return Customer
     */
    Customer addCustomer(Customer customer);

    /**
     * 修改客户信息
     *
     * @param customer
     * @return Customer
     */
    Customer modifyCustomer(Customer customer);

    /**
     * 移除客户
     *
     * @param customerId
     */
    void removeCustomer(long customerId);

    /**************************************门店*********************************************/

    /**
     * 分页查询门店
     * @param search
     * @param currentPage
     * @param limit
     * @return
     */
    Page<Store> pagingQueryStoreList(String search, Integer currentPage, Integer limit);

    /**
     * 查询所有门店
     *
     * @return List<Store>
     */
    List<Store> findAllStore();

    /**
     * 根据门店ID查询单个门店
     *
     * @param storeId
     * @return Store
     */
    Store findStoreByStoreId(long storeId);

    /**
     * 添加门店信息
     *
     * @param store
     * @return Store
     */
    Store addStore(Store store);

    /**
     * 修改门店信息
     *
     * @param store
     * @return Store
     */
    Store modifyStore(Store store);

    /**
     * 移除门店
     *
     * @param storeId
     */
    void removeStore(long storeId);

    /**************************************仓库*********************************************/

    /**
     * 分页查询仓库
     * @param search
     * @param currentPage
     * @param limit
     * @return
     */
    Page<Warehouse> pagingQueryWarehouseList(String search, Integer currentPage, Integer limit);

    /**
     * 查询所有仓库
     *
     * @return List<Warehouse>
     */
    List<Warehouse> findAllWarehouse();

    /**
     * 根据仓库ID查询单个仓库
     *
     * @param warehouseId
     * @return Warehouse
     */
    Warehouse findWarehouseByWarehouseId(long warehouseId);

    /**
     * 添加仓库信息
     *
     * @param warehouse
     * @return Warehouse
     */
    Warehouse addWarehouse(Warehouse warehouse);

    /**
     * 修改仓库信息
     *
     * @param warehouse
     * @return Warehouse
     */
    Warehouse modifyWarehouse(Warehouse warehouse);

    /**
     * 移除仓库
     *
     * @param warehouseId
     */
    void removeWarehouse(long warehouseId);

    /**************************************商品*********************************************/

    /**
     * 分页查询商品
     * @param typeId
     * @param search
     * @param currentPage
     * @param limit
     * @return
     */
    Page<Product> pagingQueryProductList(Integer typeId, String search, Integer currentPage, Integer limit);

    /**
     * 查询所有商品
     *
     * @return List<Product>
     */
    List<Product> findAllProduct();

    /**
     * 查询所有商品分类
     * @return
     */
    List<ProductType> findAllProductType();

    /**
     * 根据商品ID查询单个商品
     *
     * @param productId
     * @return Product
     */
    Product findProductByProductId(long productId);

    /**
     * 保存商品信息
     *
     * @param product
     * @return Product
     */
    Product addProduct(Product product);

    /**
     * 保存商品信息
     *
     * @param product
     * @return Product
     */
    Product modifyProduct(Product product);

    /**
     * 移除商品
     *
     * @param productId
     */
    boolean removeProduct(long productId);

}
