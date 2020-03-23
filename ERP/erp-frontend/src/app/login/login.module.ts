/**
 * @author QM.JM
 * @createTime 2017/11/20
 */

import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule} from '@angular/router';

import {loginRoutes} from './login.routing';
import {LoginComponent} from './login.component';
import {ReactiveFormsModule} from '@angular/forms';
import {NgProgressInterceptor, NgProgressModule} from 'ngx-progressbar';
import {HTTP_INTERCEPTORS} from '@angular/common/http';
import {CookieService} from 'ngx-cookie-service';

@NgModule({
   imports: [
      ReactiveFormsModule,
      CommonModule,
      NgProgressModule,
      RouterModule.forChild(loginRoutes)
   ],
   declarations: [LoginComponent],
   exports: [RouterModule],
   providers: [{provide: HTTP_INTERCEPTORS, useClass: NgProgressInterceptor, multi: true},
      CookieService
   ]
})
export class LoginModule {
}
