package com.liziczh.erp.service.impl;

import com.liziczh.erp.dto.request.PurchaseDTO;
import com.liziczh.erp.dto.request.SaleDTO;
import com.liziczh.erp.dto.request.StockAllotDTO;
import com.liziczh.erp.entity.*;
import com.liziczh.erp.repository.*;
import com.liziczh.erp.service.PSIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PSIServiceImpl implements PSIService {

    @Autowired
    private PurchaseRecordRepository purchaseRecordRepository;

    @Autowired
    private SaleRecordRepository saleRecordRepository;

    @Autowired
    private StockProductRepository stockProductRepository;

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

    @Override
    public Page<PurchaseRecord> pagingQueryPurchaseRecordList(String startTime, String endTime, Integer currentPage, Integer limit) {
        // 按时间逆序
        Sort sort = new Sort(Sort.Direction.DESC, "date");
        Pageable pageable = PageRequest.of(currentPage - 1, limit, sort);
        Page<PurchaseRecord> purchaseRecordPage = null;
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date start = format.parse(startTime);
            Date end = format.parse(endTime);
            purchaseRecordPage = purchaseRecordRepository.findByDateBetween(start, end, pageable);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return purchaseRecordPage;
    }

    @Override
    public List<PurchaseRecord> findAllPurchaseRecord() {
        List<PurchaseRecord> purchaseRecordList = purchaseRecordRepository.findAllByOrderByDateDesc();
        return purchaseRecordList;
    }

    @Override
    public PurchaseRecord purchaseInStock(PurchaseDTO purchaseDTO) {
        // 库存商品记录添加
        Optional<StockProduct> stockProductOption = stockProductRepository.findByProduct_IdAndWarehouse_Id(purchaseDTO.getProductId(), purchaseDTO.getWarehouseId());
        if (stockProductOption.isPresent()) {
            StockProduct stockProduct = stockProductOption.get();
            stockProduct.setCount(stockProduct.getCount() + purchaseDTO.getCount());
            stockProductRepository.save(stockProduct);
        } else {
            StockProduct stockProduct = new StockProduct();
            stockProduct.setProduct(productRepository.findById(purchaseDTO.getProductId()).get());
            stockProduct.setWarehouse(warehouseRepository.findById(purchaseDTO.getWarehouseId()).get());
            stockProduct.setCount(purchaseDTO.getCount());
            stockProductRepository.save(stockProduct);
        }
        // 生成采购入库记录
        PurchaseRecord purchaseRecord = new PurchaseRecord();
        purchaseRecord.setProduct(productRepository.findById(purchaseDTO.getProductId()).get());
        purchaseRecord.setType("采购入库");
        purchaseRecord.setCount(purchaseDTO.getCount());
        purchaseRecord.setPurchasePrice(purchaseDTO.getPurchasePrice());
        purchaseRecord.setSupplier(supplierRepository.findById(purchaseDTO.getSupplierId()).get());
        purchaseRecord.setWarehouse(warehouseRepository.findById(purchaseDTO.getWarehouseId()).get());
        purchaseRecord.setDate(new Date());
        purchaseRecord.setMemo(purchaseDTO.getMemo());
        purchaseRecordRepository.save(purchaseRecord);
        return purchaseRecord;
    }

    @Override
    public PurchaseRecord purchaseRefund(PurchaseDTO purchaseDTO) {
        // 库存商品记录减少
        Optional<StockProduct> stockProductOption = stockProductRepository.findByProduct_IdAndWarehouse_Id(purchaseDTO.getProductId(), purchaseDTO.getWarehouseId());
        if (stockProductOption.isPresent()) {
            StockProduct stockProduct = stockProductOption.get();
            if (stockProduct.getCount() >= purchaseDTO.getCount()) {
                stockProduct.setCount(stockProduct.getCount() - purchaseDTO.getCount());
                stockProductRepository.save(stockProduct);
            } else {
                return null;
            }
        } else {
            StockProduct stockProduct = new StockProduct();
            stockProduct.setProduct(productRepository.findById(purchaseDTO.getProductId()).get());
            stockProduct.setWarehouse(warehouseRepository.findById(purchaseDTO.getWarehouseId()).get());
            stockProduct.setCount(purchaseDTO.getCount());
            stockProductRepository.save(stockProduct);
        }
        // 生成采购退回记录
        PurchaseRecord purchaseRecord = new PurchaseRecord();
        purchaseRecord.setProduct(productRepository.findById(purchaseDTO.getProductId()).get());
        purchaseRecord.setType("采购退货");
        purchaseRecord.setCount(purchaseDTO.getCount());
        purchaseRecord.setPurchasePrice(purchaseDTO.getPurchasePrice());
        purchaseRecord.setSupplier(supplierRepository.findById(purchaseDTO.getSupplierId()).get());
        purchaseRecord.setWarehouse(warehouseRepository.findById(purchaseDTO.getWarehouseId()).get());
        purchaseRecord.setDate(new Date());
        purchaseRecord.setMemo(purchaseDTO.getMemo());
        purchaseRecordRepository.save(purchaseRecord);
        return purchaseRecord;
    }

    @Override
    public Page<SaleRecord> pagingQuerySaleRecordList(String startTime, String endTime, Integer currentPage, Integer limit) {
        // 按时间逆序
        Sort sort = new Sort(Sort.Direction.DESC, "date");
        Pageable pageable = PageRequest.of(currentPage - 1, limit, sort);
        Page<SaleRecord> saleRecordPage = null;
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date start = format.parse(startTime);
            Date end = format.parse(endTime);
            saleRecordPage = saleRecordRepository.findByDateBetween(start, end, pageable);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return saleRecordPage;
    }

    @Override
    public List<SaleRecord> findAllSaleRecord() {
        List<SaleRecord> saleRecordList = saleRecordRepository.findAllByOrderByDateDesc();
        return saleRecordList;
    }

    @Override
    public SaleRecord saleOutStock(SaleDTO saleDTO) {
        // 库存商品记录减少
        Optional<StockProduct> stockProductOption = stockProductRepository.findByProduct_IdAndWarehouse_Id(saleDTO.getProductId(), saleDTO.getWarehouseId());
        if (stockProductOption.isPresent()) {
            StockProduct stockProduct = stockProductOption.get();
            if (stockProduct.getCount() >= saleDTO.getCount()) {
                stockProduct.setCount(stockProduct.getCount() - saleDTO.getCount());
                stockProductRepository.save(stockProduct);
            } else {
                return null;
            }
        } else {
            StockProduct stockProduct = new StockProduct();
            stockProduct.setProduct(productRepository.findById(saleDTO.getProductId()).get());
            stockProduct.setWarehouse(warehouseRepository.findById(saleDTO.getWarehouseId()).get());
            stockProduct.setCount(saleDTO.getCount());
            stockProductRepository.save(stockProduct);
        }
        // 生成销售出库记录
        SaleRecord saleRecord = new SaleRecord();
        saleRecord.setProduct(productRepository.findById(saleDTO.getProductId()).get());
        saleRecord.setType("销售出库");
        saleRecord.setCount(saleDTO.getCount());
        saleRecord.setSalePrice(saleDTO.getSalePrice());
        saleRecord.setCustomer(customerRepository.findById(saleDTO.getCustomerId()).get());
        saleRecord.setWarehouse(warehouseRepository.findById(saleDTO.getWarehouseId()).get());
        saleRecord.setStore(storeRepository.findById(saleDTO.getStoreId()).get());
        saleRecord.setDate(new Date());
        saleRecord.setMemo(saleDTO.getMemo());
        saleRecordRepository.save(saleRecord);
        return saleRecord;
    }

    @Override
    public SaleRecord saleRefund(SaleDTO saleDTO) {
        // 库存商品记录添加
        Optional<StockProduct> stockProductOption = stockProductRepository.findByProduct_IdAndWarehouse_Id(saleDTO.getProductId(), saleDTO.getWarehouseId());
        if (stockProductOption.isPresent()) {
            StockProduct stockProduct = stockProductOption.get();
            stockProduct.setCount(stockProduct.getCount() + saleDTO.getCount());
            stockProductRepository.save(stockProduct);
        } else {
            StockProduct stockProduct = new StockProduct();
            stockProduct.setProduct(productRepository.findById(saleDTO.getProductId()).get());
            stockProduct.setWarehouse(warehouseRepository.findById(saleDTO.getWarehouseId()).get());
            stockProduct.setCount(saleDTO.getCount());
            stockProductRepository.save(stockProduct);
        }
        // 生成销售退货记录
        SaleRecord saleRecord = new SaleRecord();
        saleRecord.setProduct(productRepository.findById(saleDTO.getProductId()).get());
        saleRecord.setType("销售退货");
        saleRecord.setCount(saleDTO.getCount());
        saleRecord.setSalePrice(saleDTO.getSalePrice());
        saleRecord.setCustomer(customerRepository.findById(saleDTO.getCustomerId()).get());
        saleRecord.setWarehouse(warehouseRepository.findById(saleDTO.getWarehouseId()).get());
        saleRecord.setStore(storeRepository.findById(saleDTO.getStoreId()).get());
        saleRecord.setDate(new Date());
        saleRecord.setMemo(saleDTO.getMemo());
        saleRecordRepository.save(saleRecord);
        return saleRecord;
    }

    @Override
    public List<StockProduct> findAllStockProduct() {
        List<StockProduct> stockProductList = stockProductRepository.findAll();
        return stockProductList;
    }

    @Override
    public Page<StockProduct> pagingQueryStockList(Integer warehouseId, Integer currentPage, Integer limit) {
        Pageable pageable = PageRequest.of(currentPage - 1, limit);
        Page<StockProduct> stockProductPage = null;
        if(warehouseId.equals(0)){
            stockProductPage = stockProductRepository.findAll(pageable);
        }else{
            stockProductPage = stockProductRepository.findByWarehouse_Id(warehouseId, pageable);
        }
        return stockProductPage;
    }

    @Override
    public List<Product> findProductListByWarehouseId(Long warehouseId) {
        List<StockProduct> stockProductList = stockProductRepository.findByWarehouse_Id(warehouseId);
        List<Product> productList = new ArrayList<>();
        if(stockProductList != null && !stockProductList.isEmpty()){
            stockProductList.forEach(
                    stockProduct -> {
                        productList.add(stockProduct.getProduct());
                    }
            );
        }
        return productList;
    }

    @Override
    public List<Warehouse> findWarehouseListByProductId(Long productId) {
        List<StockProduct> stockProductList = stockProductRepository.findByProduct_Id(productId);
        List<Warehouse> warehouseList = new ArrayList<>();
        if(stockProductList != null && !stockProductList.isEmpty()){
            stockProductList.forEach(
                    stockProduct -> {
                        warehouseList.add(stockProduct.getWarehouse());
                    }
            );
        }
        return warehouseList;
    }

    @Override
    public StockProduct stockAllot(StockAllotDTO stockAllotDTO) {
        StockProduct stockProduct = stockProductRepository.findByProduct_IdAndWarehouse_Id(stockAllotDTO.getProductId(), stockAllotDTO.getWarehouseId()).get();
        if(stockProduct.getCount() < stockAllotDTO.getCount()){
            return null;
        }
        Optional<StockProduct> allotStockProductOption = stockProductRepository.findByProduct_IdAndWarehouse_Id(stockAllotDTO.getProductId(), stockAllotDTO.getAllotWarehouseId());
        StockProduct allotStockProduct = null;
        if (allotStockProductOption.isPresent()) {
            allotStockProduct = allotStockProductOption.get();
        }else{
            allotStockProduct = new StockProduct();
            allotStockProduct.setProduct(productRepository.findById(stockAllotDTO.getProductId()).get());
            allotStockProduct.setWarehouse(warehouseRepository.findById(stockAllotDTO.getAllotWarehouseId()).get());
            allotStockProduct.setCount(0);
        }
        stockProduct.setCount(stockProduct.getCount() - stockAllotDTO.getCount());
        stockProductRepository.save(stockProduct);
        allotStockProduct.setCount(allotStockProduct.getCount() + stockAllotDTO.getCount());
        stockProductRepository.save(allotStockProduct);
        return allotStockProduct;
    }

}
