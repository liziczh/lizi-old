import 'rxjs/add/operator/do';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {Injectable} from '@angular/core';

@Injectable()
export class TimingInterceptor implements HttpInterceptor {
   constructor() {}

   // 记录操作行为日志
   intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
      const started = Date.now();
      return next
         .handle(req)
         .do(event => {
            if (event instanceof HttpResponse) {
               const elapsed = Date.now() - started;
               // console.log(`Request for ${req.urlWithParams} took ${elapsed} ms.`);
            }
         });
   }
}
