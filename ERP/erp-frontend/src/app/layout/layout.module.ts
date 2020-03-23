/**
 * @author QM.JM
 * @createTime 2017/11/20
 */
import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule} from '@angular/router';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {NgProgressInterceptor, NgProgressModule} from 'ngx-progressbar';
import {NgbPopoverModule} from '@ng-bootstrap/ng-bootstrap';

import {
   AdminComponent, FooterComponent, HeaderComponent, LayoutComponent, layoutRoutes, MenuLeftComponent,
   MenuRightComponent, SidebarComponent
} from './';
import {AuthInterceptor} from '../shared/interceptor/auth.interceptor';
import {TimingInterceptor} from '../shared/interceptor/timing.interceptor';
import {UserService} from '../shared/service/user.service';

@NgModule({
   imports: [
      RouterModule.forChild(layoutRoutes),
      CommonModule,
      HttpClientModule,
      NgProgressModule,
      NgbPopoverModule
   ],
   declarations: [
      HeaderComponent,
      MenuLeftComponent,
      MenuRightComponent,
      SidebarComponent,
      AdminComponent,
      FooterComponent,
      LayoutComponent
   ],
   providers: [
      UserService,
      // {provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true},
      {provide: HTTP_INTERCEPTORS, useClass: TimingInterceptor, multi: true},
      {provide: HTTP_INTERCEPTORS, useClass: NgProgressInterceptor, multi: true}
   ]
})
export class LayoutModule {
}
