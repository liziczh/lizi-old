package com.liziczh.erp.service.impl;

import com.liziczh.erp.entity.*;
import com.liziczh.erp.repository.*;
import com.liziczh.erp.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class InfoServiceImpl implements InfoService {

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SupplierTypeRepository supplierTypeRepository;

    @Autowired
    private CustomerTypeRepository customerTypeRepository;

    @Autowired
    private ProductTypeRepository productTypeRepository;

    @Autowired
    private StockProductRepository stockProductRepository;


    @Override
    public Page<Supplier> pagingQuerySupplierList(Integer typeId, String search, Integer currentPage, Integer limit) {
        // 按创建时间逆序
        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
        Pageable pageable = PageRequest.of(currentPage - 1, limit, sort);
        Page<Supplier> supplierPage = null;
        if (typeId.equals(0)) {
            supplierPage = supplierRepository.findByNameContaining(search, pageable);
        } else {
            supplierPage = supplierRepository.findByType_IdAndNameContaining(typeId, search, pageable);
        }
        return supplierPage;
    }

    @Override
    public List<Supplier> findAllSupplier() {
        List<Supplier> supplierList = supplierRepository.findAll();
        return supplierList;
    }

    @Override
    public List<SupplierType> findAllSupplierType() {
        List<SupplierType> supplierTypeList = supplierTypeRepository.findAll();
        return supplierTypeList;
    }

    @Override
    public Supplier findSupplierBySupplierId(long supplierId) {
        Supplier supplier = supplierRepository.findById(supplierId).get();
        return supplier;
    }

    @Override
    public Supplier addSupplier(Supplier supplier) {
        supplier.setCreateTime(new Date());
        supplier.setUpdateTime(new Date());
        return supplierRepository.save(supplier);
    }

    @Override
    public Supplier modifySupplier(Supplier supplier) {
        supplier.setUpdateTime(new Date());
        return supplierRepository.save(supplier);
    }

    @Override
    public void removeSupplier(long supplierId) {
        supplierRepository.deleteById(supplierId);
    }

    @Override
    public Page<Customer> pagingQueryCustomerList(Integer typeId, String search, Integer currentPage, Integer limit) {
        // 按创建时间逆序
        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
        Pageable pageable = PageRequest.of(currentPage - 1, limit, sort);
        Page<Customer> customerPage = null;
        if (typeId.equals(0)) {
            customerPage = customerRepository.findByNameContaining(search, pageable);
        } else {
            customerPage = customerRepository.findByType_IdAndNameContaining(typeId, search, pageable);
        }
        return customerPage;
    }

    @Override
    public List<Customer> findAllCustomer() {
        List<Customer> customerList = customerRepository.findAll();
        return customerList;
    }

    @Override
    public List<CustomerType> findAllCustomerType() {
        List<CustomerType> customerTypeList = customerTypeRepository.findAll();
        return customerTypeList;
    }

    @Override
    public Customer findCustomerByCustomerId(long customerId) {
        Customer customer = customerRepository.findById(customerId).get();
        return customer;
    }

    @Override
    public Customer addCustomer(Customer customer) {
        customer.setCreateTime(new Date());
        customer.setUpdateTime(new Date());
        return customerRepository.save(customer);
    }

    @Override
    public Customer modifyCustomer(Customer customer) {
        customer.setUpdateTime(new Date());
        return customerRepository.save(customer);
    }

    @Override
    public void removeCustomer(long customerId) {
        customerRepository.deleteById(customerId);
    }

    @Override
    public Page<Store> pagingQueryStoreList(String search, Integer currentPage, Integer limit) {
        // 按创建时间逆序
        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
        Pageable pageable = PageRequest.of(currentPage - 1, limit, sort);
        Page<Store> storePage = storeRepository.findByNameContaining(search, pageable);
        return storePage;
    }

    @Override
    public List<Store> findAllStore() {
        List<Store> storeList = storeRepository.findAll();
        return storeList;
    }

    @Override
    public Store findStoreByStoreId(long storeId) {
        Store store = storeRepository.findById(storeId).get();
        return store;
    }

    @Override
    public Store addStore(Store store) {
        store.setCreateTime(new Date());
        store.setUpdateTime(new Date());
        return storeRepository.save(store);
    }

    @Override
    public Store modifyStore(Store store) {
        store.setUpdateTime(new Date());
        return storeRepository.save(store);
    }

    @Override
    public void removeStore(long storeId) {
        storeRepository.deleteById(storeId);
    }

    @Override
    public Page<Warehouse> pagingQueryWarehouseList(String search, Integer currentPage, Integer limit) {
        // 按创建时间逆序
        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
        Pageable pageable = PageRequest.of(currentPage - 1, limit, sort);
        Page<Warehouse> warehousePage = warehouseRepository.findByNameContaining(search, pageable);
        return warehousePage;
    }

    @Override
    public List<Warehouse> findAllWarehouse() {
        List<Warehouse> warehouseList = warehouseRepository.findAll();
        return warehouseList;
    }

    @Override
    public Warehouse findWarehouseByWarehouseId(long warehouseId) {
        Warehouse warehouse = warehouseRepository.findById(warehouseId).get();
        return warehouse;
    }

    @Override
    public Warehouse addWarehouse(Warehouse warehouse) {
        warehouse.setCreateTime(new Date());
        warehouse.setUpdateTime(new Date());
        return warehouseRepository.save(warehouse);
    }

    @Override
    public Warehouse modifyWarehouse(Warehouse warehouse) {
        warehouse.setUpdateTime(new Date());
        return warehouseRepository.save(warehouse);
    }

    @Override
    public void removeWarehouse(long warehouseId) {
        warehouseRepository.deleteById(warehouseId);
    }

    @Override
    public Page<Product> pagingQueryProductList(Integer typeId, String search, Integer currentPage, Integer limit) {
        // 按创建时间逆序
        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
        Pageable pageable = PageRequest.of(currentPage - 1, limit, sort);
        Page<Product> productPage = null;
        if (typeId.equals(0)) {
            productPage = productRepository.findByNameContaining(search, pageable);
        } else {
            productPage = productRepository.findByType_IdAndNameContaining(typeId, search, pageable);
        }
        return productPage;
    }

    @Override
    public List<Product> findAllProduct() {
        List<Product> productList = productRepository.findAll();
        return productList;
    }

    @Override
    public List<ProductType> findAllProductType() {
        List<ProductType> productTypeList = productTypeRepository.findAll();
        return productTypeList;
    }

    @Override
    public Product findProductByProductId(long productId) {
        Product product = productRepository.findById(productId).get();
        return product;
    }

    @Override
    public Product addProduct(Product product) {
        product.setCreateTime(new Date());
        product.setUpdateTime(new Date());
        return productRepository.save(product);
    }

    @Override
    public Product modifyProduct(Product product) {
        product.setUpdateTime(new Date());
        return productRepository.save(product);
    }

    @Override
    public boolean removeProduct(long productId) {
        List<StockProduct> stockProductList = stockProductRepository.findByProduct_Id(productId);
        int count = 0;
        for (StockProduct stockProduct : stockProductList) {
            count += stockProduct.getCount();
        }
        if(count == 0){
            productRepository.deleteById(productId);
            return true;
        }else{
            return false;
        }
    }
}
