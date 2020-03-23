import {Injectable} from '@angular/core';
import 'rxjs/add/operator/map';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';

@Injectable()
export class UserService {

    constructor(private http: HttpClient) {
    }

    // 条件查询用户
    pagingQueryUserList(roleId: string, search: string, pageNum: number, limit: number): Observable<any> {
        if (search === null) {
            search = '';
        }
        return this.http.get(`/api/erp/users?roleId=${roleId}&search=${search}&currentPage=${pageNum}&limit=${limit}`);
    }

    // 获取所有用户
    queryUserList(): Observable<any> {
        return this.http.get(`/api/erp/user/all`);
    }

    // 获取所有用户角色
    queryUserRoleList(): Observable<any> {
        return this.http.get(`/api/erp/user/roles`);
    }

    // 新增用户
    addUser(user: any): Observable<any> {
        return this.http.post(`/api/erp/user`, user);
    }

    // 修改用户
    modifyUser(user: any): Observable<any> {
        return this.http.put(`/api/erp/user`, user);
    }

    // 修改用户密码
    modifyUserPassword(username: any, password: any): Observable<any> {
        return this.http.put(`/api/erp/user/password`, {
            username: username,
            password: password
        });
    }

    // 删除用户
    deleteUser(userId: any): Observable<any> {
        return this.http.delete(`/api/erp/user?userId=${userId}`);
    }

}
