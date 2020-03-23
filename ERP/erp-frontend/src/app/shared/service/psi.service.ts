import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';

@Injectable()
export class PsiService {

    constructor(private http: HttpClient) {
    }

    // 分页条件查询所有采购记录
    pagingQueryPurchaseRecordList(startTime: string, endTime: string, pageNum: number, limit: number): Observable<any> {
        return this.http.get(`/api/erp/psi/records/purchase?startTime=${startTime}&endTime=${endTime}&currentPage=${pageNum}&limit=${limit}`);
    }

    // 获取所有采购记录
    queryPurchaseRecordList(): Observable<any> {
        return this.http.get(`/api/erp/psi/records/purchase/all`);
    }

    // 采购入库
    purchaseInStock(value: any): Observable<any> {
        return this.http.post(`/api/erp/psi/purchase/in`, value);
    }

    // 采购退货
    purchaseRefund(value: any): Observable<any> {
        return this.http.post(`/api/erp/psi/purchase/refund`, value);
    }

    // 分页条件查询所有销售记录
    pagingQuerySaleRecordList(startTime: string, endTime: string, pageNum: number, limit: number): Observable<any> {
        return this.http.get(`/api/erp/psi/records/sale?startTime=${startTime}&endTime=${endTime}&currentPage=${pageNum}&limit=${limit}`);
    }

    // 获取所有销售记录
    querySaleRecordList(): Observable<any> {
        return this.http.get(`/api/erp/psi/records/sale/all`);
    }

    // 销售出库
    saleOutStock(value: any): Observable<any> {
        return this.http.post(`/api/erp/psi/sale/out`, value);
    }

    // 销售退货
    saleRefund(value: any): Observable<any> {
        return this.http.post(`/api/erp/psi/sale/refund`, value);
    }

    // 分页条件查询所有库存记录
    pagingQueryStockList(warehouseId: string, pageNum: number, limit: number): Observable<any> {
        return this.http.get(`/api/erp/psi/stock?warehouseId=${warehouseId}&currentPage=${pageNum}&limit=${limit}`);
    }

    // 获取所有库存记录
    queryStockList(): Observable<any> {
        return this.http.get(`/api/erp/psi/stock/all`);
    }

    // 获取某仓库下的商品列表
    queryProductListByWarehouseId(warehouseId: number): Observable<any> {
        return this.http.get(`/api/erp/psi/stock/products?warehouseId=${warehouseId}`);
    }

    // 获取存在某商品的仓库列表
    queryWarehouseListByProductId(productId: number): Observable<any> {
        return this.http.get(`/api/erp/psi/stock/warehouses?productId=${productId}`);
    }

    // 商品内部调拨
    stockAllot(value: any): Observable<any> {
        return this.http.put(`/api/erp/psi/stock/allot`, value);
    }

}
