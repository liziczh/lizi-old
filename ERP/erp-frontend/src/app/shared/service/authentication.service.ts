import {Injectable} from '@angular/core';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Response} from '../model/response';
import {StorageUtil} from '../utils/storage.util';
import 'rxjs/add/operator/shareReplay';


@Injectable()
export class AuthenticationService {

    public token: string;
    public username: string;
    public user: any; // 用户对象

    constructor(private http: HttpClient, private storageUtil: StorageUtil) {
        // set token if saved in local storage
        this.user = this.storageUtil.get('currentUser');
    }

    login(username: string, password: string): Observable<Response> {
        return this.http.post('/api/erp/auth/login', JSON.stringify({username: username, password: password})
            , {headers: new HttpHeaders({'content-type': 'application/json'})}).map(
            response => {
                let user = response['data'];
                if (user) {
                    this.user = user;
                    this.storageUtil.set('currentUser', JSON.stringify(user));
                }
                console.log(this.storageUtil.get('currentUser'));
                return new Response(response['code'], response['msg']);
            }
        );
    }

    refresh(): Observable<Response> {
        return this.http.get('/api/auth/jwt/refresh'
            , {
                headers: new HttpHeaders({
                    'Authorization': this.token,
                    'content-type': 'application/x-www-form-urlencoded'
                })
            }).map(response => {
                let token = response['data'];
                if (token) {
                    this.token = token;
                    this.storageUtil.set('currentUser', JSON.stringify({username: this.username, token: token}));
                }
                return new Response(response['code'], response['msg']);
            }
        ).shareReplay();
    }

    logout(): void {
        // clear token remove user from local storage to log user out
        localStorage.removeItem('currentUser');
        localStorage.removeItem('router');
    }


}
