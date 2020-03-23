import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';

@Injectable()
export class InfoService {

    constructor(private http: HttpClient) {
    }

    // 条件查询供应商
    pagingQuerySupplierList(selectedTypeId: string, search: string, pageNum: number, limit: number): Observable<any> {
        if (search === null) {
            search = '';
        }
        return this.http.get(`/api/erp/info/suppliers?typeId=${selectedTypeId}&search=${search}&currentPage=${pageNum}&limit=${limit}`);
    }

    // 获取所有供应商
    querySupplierList(): Observable<any> {
        return this.http.get(`/api/erp/info/suppliers/all`);
    }

    // 获取所有供应商分类
    querySupplierTypeList(): Observable<any> {
        return this.http.get(`/api/erp/info/supplier/types`);
    }

    // 新增供应商
    addSupplier(supplier: any): Observable<any> {
        return this.http.post(`/api/erp/info/supplier`, supplier);
    }

    // 修改供应商
    modifySupplier(supplier: any): Observable<any> {
        return this.http.put(`/api/erp/info/supplier`, supplier);
    }

    // 删除供应商
    deleteSupplier(supplierId: any): Observable<any> {
        return this.http.delete(`/api/erp/info/supplier?supplierId=${supplierId}`);
    }

    // 条件查询客户
    pagingQueryCustomerList(selectedTypeId: string, search: string, pageNum: number, limit: number): Observable<any> {
        if (search === null) {
            search = '';
        }
        return this.http.get(`/api/erp/info/customers?typeId=${selectedTypeId}&search=${search}&currentPage=${pageNum}&limit=${limit}`);
    }

    // 获取所有客户
    queryCustomerList(): Observable<any> {
        return this.http.get(`/api/erp/info/customers/all`);
    }

    // 获取所有客户分类
    queryCustomerTypeList(): Observable<any> {
        return this.http.get(`/api/erp/info/customer/types`);
    }

    // 新增客户
    addCustomer(customer: any): Observable<any> {
        return this.http.post(`/api/erp/info/customer`, customer);
    }

    // 修改客户
    modifyCustomer(customer: any): Observable<any> {
        return this.http.put(`/api/erp/info/customer`, customer);
    }

    // 删除客户
    deleteCustomer(customerId: any): Observable<any> {
        return this.http.delete(`/api/erp/info/customer?customerId=${customerId}`);
    }

    // 条件查询门店
    pagingQueryStoreList(search: string, pageNum: number, limit: number): Observable<any> {
        if (search === null) {
            search = '';
        }
        return this.http.get(`/api/erp/info/stores?&search=${search}&currentPage=${pageNum}&limit=${limit}`);
    }

    // 获取所有门店
    queryStoreList(): Observable<any> {
        return this.http.get(`/api/erp/info/stores/all`);
    }

    // 新增门店
    addStore(store: any): Observable<any> {
        return this.http.post(`/api/erp/info/store`, store);
    }

    // 修改门店
    modifyStore(store: any): Observable<any> {
        return this.http.put(`/api/erp/info/store`, store);
    }

    // 删除门店
    deleteStore(storeId: any): Observable<any> {
        return this.http.delete(`/api/erp/info/store?storeId=${storeId}`);
    }

    // 条件查询仓库
    pagingQueryWarehouseList(search: string, pageNum: number, limit: number): Observable<any> {
        if (search === null) {
            search = '';
        }
        return this.http.get(`/api/erp/info/warehouses?&search=${search}&currentPage=${pageNum}&limit=${limit}`);
    }

    // 获取所有仓库
    queryWarehouseList(): Observable<any> {
        return this.http.get(`/api/erp/info/warehouses/all`);
    }

    // 新增仓库
    addWarehouse(warehouse: any): Observable<any> {
        return this.http.post(`/api/erp/info/warehouse`, warehouse);
    }

    // 修改仓库
    modifyWarehouse(warehouse: any): Observable<any> {
        return this.http.put(`/api/erp/info/warehouse`, warehouse);
    }

    // 删除仓库
    deleteWarehouse(warehouseId: any): Observable<any> {
        return this.http.delete(`/api/erp/info/warehouse?warehouseId=${warehouseId}`);
    }

    // 条件查询产品
    pagingQueryProductList(selectedTypeId: string, search: string, pageNum: number, limit: number): Observable<any> {
        if (search === null) {
            search = '';
        }
        return this.http.get(`/api/erp/info/products?typeId=${selectedTypeId}&search=${search}&currentPage=${pageNum}&limit=${limit}`);
    }

    // 获取所有产品
    queryProductList(): Observable<any> {
        return this.http.get(`/api/erp/info/products/all`);
    }

    // 获取所有产品
    queryProductTypeList(): Observable<any> {
        return this.http.get(`/api/erp/info/product/types`);
    }

    // 新增产品
    addProduct(product: any): Observable<any> {
        return this.http.post(`/api/erp/info/product`, product);
    }

    // 修改产品
    modifyProduct(product: any): Observable<any> {
        return this.http.put(`/api/erp/info/product`, product);
    }

    // 删除产品
    deleteProduct(productId: any): Observable<any> {
        return this.http.delete(`/api/erp/info/product?productId=${productId}`);
    }

}
