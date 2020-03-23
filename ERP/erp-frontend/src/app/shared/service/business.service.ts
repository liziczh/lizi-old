import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {CompanyEditProduct} from '../../admin/business/company/company.component';


@Injectable()
export class BusinessService {
   dataSource = [];

   constructor(private http: HttpClient) {
      this.queryCompanyList().subscribe(result => {
         for (let product of result.data) {
            this.dataSource.push({value: product.id, text: product.text});
         }
      });
   }

   // 获取所有产品
   queryProductList(): Observable<any> {
      return this.http.get('/api/admin/business/products');
   }
   // 获取所有产品
   queryUserProductList(): Observable<any> {
      return this.http.get('/api/admin/business/users/products');
   }

   // 获取所有销售人
   querySaleList(): Observable<any> {
      return this.http.get('/api/admin/business/sales');
   }

   // 分页查询公司
   pagingQueryCompanyList(selectedProductId: string, selectedSale: string,
                          selectedStatus: string, value: any, currentPage: number, limit: number) {
      if (value === null) {
         value = '';
      }
      return this.http.get('/api/admin/business/companies-page?' +
         'productId=' + selectedProductId +
         '&saleId=' + selectedSale +
         '&companyStatus=' + selectedStatus +
         '&keyWords=' + value +
         '&currentPage=' + currentPage +
         '&limit=' + limit);
   }

   // 分页查询用户
   pagingQueryUserList(selectedProductId: string, companyId: string, userStatus: string, value: any, currentPage: number, limit: number) {
      if (value === null) {
         value = '';
      }
      if (companyId === null || companyId === '' || companyId === undefined) {
         companyId = '-1';
      }
      return this.http.get('/api/admin/business/users-page?' +
         'productId=' + selectedProductId +
         '&companyId=' + companyId +
         '&userStatus=' + userStatus +
         '&keywords=' + value +
         '&currentPage=' + currentPage +
         '&limit=' + limit);
   }

   // 查询公司列表
   queryCompanyList(): Observable<any> {
      return this.http.get('/api/admin/business/company-select');
   }

   // 删除公司
   delCompany(companyId: number): Observable<any> {
      return this.http.delete('api/admin/business/companies?' +
         'companyId=' + companyId);
   }

   // 禁用公司
   disCompany(companyId: number, type: number): Observable<any> {
      return this.http.delete('api/admin/business/disable-companies?' +
         'companyId=' + companyId + '&type=' + type);
   }

   // 解除微信绑定
   delWeChat(userId: any): Observable<any> {
      return this.http.delete('api/admin/business/users/wechat?' +
         'userIds=' + userId);
   }

   // 查看用户微信绑定历史
   getUserWeChat(userId: number): Observable<any> {
      return this.http.get('api/admin/business/users/wechat?' +
         'userId=' + userId);
   }

   // 用户获取公司所有邮箱后缀
   getUserCompanyEmails(companyId: string): Observable<any> {
      return this.http.get('api/admin/business/users/company/emails?' +
         'companyId=' + companyId);
   }

   // 获取公司所有邮箱后缀
   getCompanyEmails(companyId: string): Observable<any> {
      return this.http.get('api/admin/business/company/emails?' +
         'companyId=' + companyId);
   }

   // 查询公司可添加用户的产品
   getCompanyProducts(companyId: string): Observable<any> {
      return this.http.get('api/admin/business/company/' + companyId + '/products');
   }

   // 添加公司
   submitCreateCompany(value: any): Observable<any> {
      return this.http.post('api/admin/business/companies', value);
   }

   // 修改公司信息
   submitEditCompany(value: any): Observable<any> {
      return this.http.put('api/admin/business/companies', value);
   }

   // 获取单个公司的信息
   queryCompany(companyID: number): Observable<any> {
      return this.http.get('api/admin/business/companies?companyID=' + companyID);
   }

   // （批量）删除用户
   delUser(userId: any): Observable<any> {
      return this.http.delete('api/admin/business/users?' +
         'userIds=' + userId);
   }

   // 禁用/解禁用户
   disUser(userId: Array<string>, type: number): Observable<any> {
      return this.http.delete('api/admin/business/disable-users?' +
         'userIds=' + userId +
         '&type=' + type);
   }

   // 修改密码
   editPassWord(newPassWord: string, userId: string): Observable<any> {
      return this.http.put('api/admin/business/users/pwd?' +
         'userId=' + userId +
         '&newPwd=' + newPassWord, userId);
   }

   // 重置密码
   resetPassWord(userIds: any): Observable<any> {
      return this.http.put('api/admin/business/users/batch-pwd?userIds=' + userIds, null);
   }

   // 获取单个用户信息
   queryUser(userId: string): Observable<any> {
      return this.http.get('api/admin/business/users?' +
         'userId=' + userId);
   }

   // 获取公司所有产品
   queryCompanyProducts(companyID: number): Observable<any> {
      return this.http.get('api/admin/business/companies/' + companyID + '/products');
   }
   // 用户界面获取公司所有产品
   queryUserCompanyProducts(companyID: string): Observable<any> {
      return this.http.get('api/admin/business/users/company/' + companyID + '/products');
   }

   // 公司产品延期
   commitProductTemplate(companyID: any, ids: string,
                         productTemplateStartDate: string, productTemplateExpireDate: string): Observable<any> {
      return this.http.put(
         'api/admin/business/companies/products/extension'
         , {
            companyID: companyID,
            productIds: ids,
            startDate: productTemplateStartDate,
            expireDate: productTemplateExpireDate
         });
   }

   // 修改所有产品信息
   commitEditProductTemplate(id: any, data: Array<CompanyEditProduct>): Observable<any> {
      return this.http.put(`api/admin/business/companies/${id}/products`, data);
   }

   queryCompanyNoneProducts(companyID: number): Observable<any> {
      return this.http.get('api/admin/business/companies/' + companyID + '/none-products');
   }

   // 获取产品试用时间
   findProductDataTime(productID: number): Observable<any> {
      return this.http.get('api/admin/business/product-time/' + productID);
   }

   // 公司购买产品
   commitBuyProductTemplate(id: any, dataEdit: Array<CompanyEditProduct>): Observable<any> {
      return this.http.post(`api/admin/business/companies/${id}/products`, dataEdit);
   }

   // 批量添加用户
   commitAddUserTemplate(companyID: string, email: string, products: number[], users: any,
                         isTrial: boolean, isEn: boolean, isSend: boolean): Observable<any> {
      return this.http.post(`api/admin/business/companies/${companyID}/users`, {
         email: email,
         products: products,
         users: users,
         isSend: isSend,
         isTrial: isTrial,
         isEn: isEn
      });

   }

   // 修改用户
   submitEditUserForm(value: any): Observable<any> {
      return this.http.put('api/admin/business/users', value);
   }

   // 添加用户
   submitAddUserForm(value: any): Observable<any> {
      return this.http.post('api/admin/business/users', value);
   }

   // 授权产品给用户
   commitAuthorizeTheUserTemplate(id: string, userList: any[], dataEdit: Array<CompanyEditProduct>): Observable<any> {
      return this.http.post(`api/admin/business/users/products`,
         {
            products: dataEdit,
            userIds: userList,
            companyID: id
         });

   }

   queryUserProducts(userId: string): Observable<any> {
      return this.http.get('api/admin/business/users/' + userId + '/products');
   }

   batchDelUserAuthorize(userList: any[], companyID: string, productIds: any[]) {
      return this.http.delete(`api/admin/business/users/products?companyID=${companyID}&userIds=${userList}&productIds=${productIds}`);
   }

   // 懒加载所有企业
   getData(term: string, pageNumber: number, pageSize: number) {
      term = (term || '').trim();
      let data = this.dataSource.filter(row => row.text.toLowerCase().indexOf(term.toLowerCase()) !== -1);
      let start = (pageNumber - 1) * pageSize;
      let rows = data.slice(start, start + pageSize);
      return Observable.of({
         total: data.length,
         pageNumber: pageNumber,
         pageSize: pageSize,
         rows: rows
      });
   }


   /**
    * ############################################产品时间限制################################################
    */


   // 查询单个产品时间
   queryProductTime(productId: number): Observable<any> {
      return this.http.get(`/api/admin/business/user-product-time/${productId}`);
   }

   // 查询所有产品时间
   queryProductTimeList(): Observable<any> {
      return this.http.get(`/api/admin/business/product-time-list`);
   }

   // 查询所有未添加时间限制的产品
   queryProductListNoInProductTime(): Observable<any> {
      return this.http.get(`/api/admin/business/product-time/no-limit`);
   }

   // 新增产品时间
   addProductTime(
      productId: number, startTime: string,
      endTime: string, manager: string
   ): Observable<any> {
      return this.http.post(`/api/admin/business/product-time?productId=${productId}
         &startTime=${startTime}&endTime=${endTime}&manager=${manager}`, null);
   }

   // 修改产品时间
   modifyProductTime(id: number, startTime: string, endTime: string, manager: string): Observable<any> {
      return this.http.put(`/api/admin/business/product-time?id=${id}&startTime=${startTime}&endTime=${endTime}&manager=${manager}`, null);
   }

   // 删除产品时间
   removeProductTime(id: number): Observable<any> {
      return this.http.delete(`/api/admin/business/product-time?id=${id}`);
   }

   queryUsersCompanyProducts(companyID: string): Observable<any> {
      return this.http.get(`api/admin/business/users/company-products?companyId=${companyID}`);
   }
}


