import {Injectable} from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {AuthenticationService} from '../service/authentication.service';
import {Router} from '@angular/router';
import {StorageUtil} from '../utils/storage.util';
import {ToastrService} from 'ngx-toastr';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

   constructor(private auth: AuthenticationService, private router: Router,
               private storageUtil: StorageUtil, private toastr: ToastrService) {

   }

   intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
      /* Clone the request to add the new header.*/

      let expireTime = this.storageUtil.getExpireTime('currentUser');
      if (!expireTime) {
         if (this.router.url !== '/login') {
            localStorage.setItem('router', this.router.url);
         }
         this.router.navigate(['/login']);
      } else if (expireTime < StorageUtil.expireTime / 2) {
         this.auth.refresh().subscribe();
      }
      // 添加headers的Authorization 信息
      // 'Authorization': 'Bearer ' + this.auth.token
      let headers = req.headers.append('Authorization', this.auth.token);
      const authReq = req.clone({
         headers: headers
      });
      // Pass on the cloned request instead of the original request.
      return next.handle(authReq).map(event => {
         if (event instanceof HttpResponse && event.body) {
            // blob下载文件 || 样本回收监控系统
            if (authReq.responseType === 'blob' || authReq.url.startsWith('http://www.qchannel04.cn/')) {
               return event;
            }

            switch (event.body.code) {
               case 100200:
                  // console.log('200 成功:' + req.url + '  msg：' + event.body.msg);
                  break;
               case 201400: {  // JWT Token expired
                  if (this.router.url !== '/login') {
                     localStorage.setItem('router', this.router.url);
                  }
                  // console.log(this.router.url);
                  this.toastr.error('身份信息过期，请重新登陆！');
                  this.router.navigate(['/login']);
                  return null;
               }
               default:
                  this.toastr.error(event.body.msg, event.body.code);
                  return null;
            }
         }
         return event;
      });
   }
}
