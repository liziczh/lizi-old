import {Injectable} from '@angular/core';
import {of} from 'rxjs/observable/of';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {Group} from '../model/auth/group';


@Injectable()
export class AuthorityService {

   constructor(private http: HttpClient) {
   }

   /**
    * 模糊搜索本公司用户  用户名 邮箱
    * @param {string} term
    * @returns {Observable<any>}
    */
   search(term: string): Observable<any> {
      if (term === '') {
         return of([]);
      }
      return this.http.get('/api/admin/authority/company-user-search', {
         params: {param: term}
      }).map(response => response['data']);
   }

   /**
    * 模糊搜索全部用户  用户名 邮箱
    * @param {string} param
    * @returns {Observable<any>}
    */
   searchUser(param: string): Observable<any> {
      if (param === '') {
         return of([]);
      }
      return this.http.get('/api/admin/authority/user-search', {
         params: {param: param}
      }).map(response => response['data']);
   }

   /**
    * 查询本公司所有的部门列表
    * @returns {Observable<any>}
    */
   queryAllDepartment(): Observable<any> {
      return this.http.get('/api/admin/authority/departments');
   }

   /**
    *查询部门的成员
    * @param {string} departmentId
    * @returns {Observable<any>}
    */
   queryDepartMember(departmentId: string): Observable<any> {
      return this.http.get('/api/admin/authority/department/' + departmentId + '/member');
   }

   /**
    * 查询单个部门的配置信息
    * @param {string} departmentId
    * @returns {Observable<any>}
    */
   queryDepartConfig(departmentId: string): Observable<any> {
      return this.http.get('/api/admin/authority/department/' + departmentId + '/config');
   }

   /**
    * 查询当前部门拥有的菜单
    * @param {string} departmentId
    * @returns {Observable<any>}
    */
   queryDepartMenu(departmentId: string): Observable<any> {
      return this.http.get('/api/admin/authority/department/' + departmentId + '/menus');
   }

   /**
    *查询当前部门的某个菜单拥有的资源
    * @param {string} authorityId
    * @param {string} departmentId
    * @returns {Observable<any>}
    */
   queryMenuResource(authorityId: string, departmentId: string): Observable<any> {
      return this.http.get('/api/admin/authority/department/' + departmentId + '/menu/' + authorityId + '/resource');
   }

   /**
    * 查询个人 单个菜单的 请求资源
    * @param {string} resourceId
    * @param {string} userId
    * @returns {Observable<any>}
    */
   queryUserMenuResource(resourceId: string, userId: string): Observable<any> {
      return this.http.get('/api/admin/authority/user/' + userId + '/menu/' + resourceId + '/resource');
   }

   /**
    * 给部门新增 menu uri button 类型的资源
    * resourceId  startsWith resource./menu./product./1 资源类型/菜单类型/产品类型/全部资源
    * @param {string} authorityId
    * @param {string} departmentId
    * @returns {Observable<any>}
    */
   addDepartResource(authorityId: string, departmentId: string): Observable<any> {
      return this.http.post('/api/admin/authority/department/resource', {
         groupId: departmentId,
         resourceId: authorityId
      });
   }

   /**
    * 给用户新增 menu uri button 类型的资源
    * resourceId  startsWith resource./menu./product./1 资源类型/菜单类型/产品类型/全部资源
    * @param {string} resourceId
    * @param {string} userId
    * @returns {Observable<any>}
    */
   addUserResource(resourceId: string, userId: string): Observable<any> {
      return this.http.post('/api/admin/authority/user/resource', {
         groupId: userId,
         resourceId: resourceId
      });
   }

   /**
    * 给部门删除 menu uri button 类型的资源
    * resourceId  startsWith resource./menu./product./1 资源类型/菜单类型/产品类型/全部资源
    * @param {string} authorityId
    * @param {string} departmentId
    * @returns {Observable<any>}
    */
   delDepartResource(authorityId: string, departmentId: string): Observable<any> {
      return this.http.delete('/api/admin/authority/department/resource?groupId='
         + departmentId + '&resourceId=' + authorityId);
   }

   /**
    * 给用户删除 menu uri button 类型的资源
    * resourceId  startsWith resource./menu./product./1 资源类型/菜单类型/产品类型/全部资源
    * @param {string} authorityId
    * @param {string} departmentId
    * @returns {Observable<any>}
    */
   delUserResource(resourceId: string, userId: string): Observable<any> {
      return this.http.delete('/api/admin/authority/user/resource?groupId=' + userId + '&resourceId=' + resourceId);
   }

   /**
    * 部门新增加、删除、修改（设为leader）用户
    * @param {string} departmentId
    * @param {string} userId
    * @returns {Observable<any>}
    */
   updateDepartUser(departmentId: string, userId: number): Observable<any> {
      return this.http.put('/api/admin/authority/department/member?groupId=' + departmentId + '&userId=' + userId, {});
   }

   delDepartUser(departmentId: string, userId: number): Observable<any> {
      return this.http.delete('/api/admin/authority/department/member?groupId=' + departmentId + '&userId=' + userId, {});
   }

   addDepartUser(departmentId: string, userId: string): Observable<any> {
      return this.http.post('/api/admin/authority/department/member', {
         groupId: departmentId,
         resourceId: userId
      });
   }

   /**
    * 新增部门
    * @param {Group} modalDepartment
    * @returns {Observable<any>}
    */
   addDepartment(modalDepartment: Group): Observable<any> {
      return this.http.post('/api/admin/authority/department', modalDepartment);
   }

   /**
    * 更新部门配置信息
    * @param {Group} modalDepartment
    * @returns {Observable<any>}
    */
   updateDepartment(modalDepartment: Group): Observable<any> {
      return this.http.put('/api/admin/authority/department/config', modalDepartment);
   }

   /**
    *删除部门组
    * @param {string} departmentId
    * @returns {Observable<any>}
    */
   delDepartment(departmentId: string): Observable<any> {
      return this.http.delete('/api/admin/authority/department?groupId=' + departmentId);
   }

   /**
    * 查询所有的公司列表
    * @returns {Observable<any>}
    */
   queryAllCompany(): Observable<any> {
      return this.http.get('/api/admin/authority/companies');
   }

   /**
    * 查询公司的用户
    * authLimit 用户权限类型 0 正常 1 单独指定权限 2 全部
    * @param {string} companyId
    * @param {string} userStatus
    * @returns {Observable<any>}
    */
   queryCompanyAuthLimitUsers(companyId: string, userStatus: string): Observable<any> {
      return this.http.get('/api/admin/authority/company/member?companyId=' + companyId + '&authLimit=' + userStatus);
   }

   /**
    * 切换用户的 是否单独指定权限
    * @param {string} userId
    * @param {boolean} authLimit 当前状态是否单独指定权限
    * @returns {Observable<any>}
    */
   updateUserAuthLimit(userId: string, authLimit: boolean): Observable<any> {
      return this.http.put('/api/admin/authority/auth-limit?userId=' + userId + '&limit=' + authLimit, {});
   }

   /**
    * 查询个人的权限菜单
    * @param {string} userId
    * @returns {Observable<any>}
    */
   queryUserMenu(userId: string): Observable<any> {
      return this.http.get('/api/admin/authority/user/' + userId + '/menus');
   }

}
