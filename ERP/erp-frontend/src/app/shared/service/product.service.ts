import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';

@Injectable()
export class ProductService {
   constructor(private http: HttpClient) {

   }
   // 分页查询产品列表
   pageQueryProductList(currentPage: number, limit: number) {
      return this.http.get('/api/admin/information/products-page?' +
         'currentPage=' + currentPage +
         '&limit=' + limit);
   }
   // 获取产品的详细信息
   queryProduct(productId: number): Observable<any> {
      return this.http.get('api/admin/information/product?' +
      'productId=' + productId);
   }
   // 提交编辑产品
   submitSaveProduct(value: any): Observable<any> {
      return this.http.post('api/admin/information/product', value);
   }
   // 提交删除产品
   submitDelProduct(productId: any): Observable<any> {
      return this.http.delete('api/admin/information/product?' +
      'productId= ' + productId);
   }
   // 查询产品列表
   queryProductList(): Observable<any> {
      return this.http.get('/api/admin/business/products');
   }

   // 查找单个模块详细信息
   queryModule(moduleId: number): Observable<any> {
      return this.http.get('api/admin/information/module?' +
      'moduleId=' + moduleId);
   }

   // 查找父级模块列表
   queryPrentList(productId: string): Observable<any> {
      return this.http.get('api/admin/information/parentModule?' +
      'productId=' + productId);
   }

   // 提交编辑模块
   submitSaveModule(module: any): Observable<any> {
      return this.http.put('api/admin/information/module', module);
   }

   // 提交删除模块
   deleteModule(moduleId: any): Observable<any> {
      return this.http.delete('api/admin/information/modules?' +
      'moduleId=' + moduleId);
   }

   // 分页查询公告列表
   pageQueryNoticeList(productId: string, currentPage: number, limit: number ) {
      return this.http.get('api/admin/information/notice-page?' +
         'productId=' + productId +
         '&currentPage=' + currentPage +
         '&limit=' + limit);
   }
   // 获取公告详细信息
   queryNotice(noticeId: number): Observable<any> {
      return this.http.get('api/admin/information/notice?' +
         'noticeId=' + noticeId);
   }
   // 提交编辑公告信息
   submitEditNoticeForm(notice: any): Observable<any> {
      return this.http.put('api/admin/information/notice', notice);
   }

   // 提交删除公告
   deleteNotice(noticeId: any): Observable<any> {
      return this.http.delete('api/admin/information/notice?' +
         'noticeId=' + noticeId);
   }

   // 树状图
   queryModuleTree(productId: number): Observable<any> {
      return this.http.get('api/admin/information/module-tree?' + 'productId=' + productId);
   }
}
