/**
 * @author QM.JM
 * @createTime 2017/11/20
 */

import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {Router} from '@angular/router';
import {BrowserModule} from '@angular/platform-browser';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {HttpClient, HttpClientModule} from '@angular/common/http';
import {TranslateLoader, TranslateModule} from '@ngx-translate/core';
import {TranslateHttpLoader} from '@ngx-translate/http-loader';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {AuthGuard} from './shared';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';

import {AuthenticationService} from './shared/service/authentication.service';
import {StorageUtil} from './shared/utils/storage.util';
import {ButtonsModule, ModalModule} from 'ngx-bootstrap';
import {ToastrModule, ToastrService} from 'ngx-toastr';
import { ErrorComponent } from './error/error.component';

// AoT requires an exported function for factories
export function createTranslateLoader(http: HttpClient) {
   // for development
   // return new TranslateHttpLoader(http, '/start-angular/SB-Admin-BS4-Angular-5/master/dist/assets/i18n/', '.json');
   return new TranslateHttpLoader(http, './assets/i18n/', '.json');
}

@NgModule({
   imports: [
      CommonModule,
      BrowserModule,
      BrowserAnimationsModule,
      HttpClientModule,
      ModalModule.forRoot(),
      ButtonsModule.forRoot(),
      NgbModule.forRoot(),
      ToastrModule.forRoot(), // ToastrModule added
      TranslateModule.forRoot({
         loader: {
            provide: TranslateLoader,
            useFactory: createTranslateLoader,
            deps: [HttpClient]
         }
      }),

      AppRoutingModule,
   ],
   declarations: [AppComponent, ErrorComponent],
   providers: [
      AuthGuard,
      ToastrService,
      AuthenticationService,
      StorageUtil
   ],
   bootstrap: [AppComponent]
})
export class AppModule {

   constructor(private router: Router) {

      // NProgress.configure({ showSpinner: false });
      // router.events.subscribe((event) => {
      //    if (event instanceof NavigationStart) {
      //       NProgress.start();
      //    }
      //    if (event instanceof NavigationEnd) {
      //       setTimeout(function () {
      //          NProgress.done();
      //       }, 200);
      //    }
      // });

   }

}
