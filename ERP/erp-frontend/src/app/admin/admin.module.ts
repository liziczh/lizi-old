import {NgModule} from '@angular/core';
import {RouterModule} from '@angular/router';

import {
   adminRoutes,
   DashboardComponent,
} from './';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {CommonModule, DatePipe} from '@angular/common';
import {NgxEchartsModule} from 'ngx-echarts';
import {
   NgbDatepickerModule,
   NgbDropdownModule,
   NgbModule,
   NgbPaginationModule,
   NgbTabsetConfig,
   NgbTabsetModule
} from '@ng-bootstrap/ng-bootstrap';
import {AuthGuard} from '../shared/guard';
import {BusinessService} from '../shared/service/business.service';
import {Select2Module} from 'ng2-select2';
import {PaginationPipe} from '../shared/pipe/common/pagination.pipe';
import {BsDatepickerModule, BsModalService, defineLocale, PopoverModule, TypeaheadModule, zhCnLocale} from 'ngx-bootstrap';
import {CommonService} from '../shared/utils/common.service';
import {UserService} from '../shared/service/user.service';
import {AuthorityService} from '../shared/service/authority.service';
import {SampleService} from '../shared/service/sample.service';
import {SortPipe} from '../shared/pipe/common/sort.pipe';
import {TabularPipe} from '../shared/pipe/common/tabular.pipe';
import {UnpublishedComponent} from './article/unpublished/unpublished.component';
import {PublishedComponent} from './article/published/published.component';
import {ArticleService} from '../shared/service/article.service';
import {JobStatusPipe} from '../shared/pipe/job-status';
import {ArticleFilterPipe} from '../shared/pipe/article-filter.pipe';
import {ArrayToString} from '../shared/pipe/common/array-to-String';
import {HighLightPipe} from '../shared/pipe/common/high-light.pipe';
import {FilesUploadComponent} from '../shared/component/files-upload.component';
import {ConfigurationComponent} from './article/configuration/configuration.component';
import {UsersComponent} from './business/users/users.component';
import {CompanyComponent} from './business/company/company.component';
import {ProductTimeComponent} from './business/product-time/product-time.component';
import {ProductTimeService} from '../shared/service/product-time.service';
import {EasyUIModule} from 'ng-easyui/components/easyui/easyui.module';
import {NodesToStringPipe} from '../shared/pipe/nodes-to-string.pipe';
import {HtmlPipe} from '../shared/pipe/common/style-html.pipe';
import {ClipboardModule} from 'ngx-clipboard';
import {ProductService} from '../shared/service/product.service';
import { DateCommonPipePipe } from '../shared/pipe/common/date-common-pipe.pipe';
import { UEditorModule } from 'ngx-ueditor';
import { SupplierComponent } from './info/supplier/supplier.component';
import { CustomerComponent } from './info/customer/customer.component';
import { StoreComponent } from './info/store/store.component';
import { WarehouseComponent } from './info/warehouse/warehouse.component';
import {ProductComponent} from './info/product/product.component';
import {InfoService} from '../shared/service/info.service';
import { PurchaseComponent } from './psi/purchase/purchase.component';
import { SaleComponent } from './psi/sale/sale.component';
import { StockComponent } from './psi/stock/stock.component';
import {PsiService} from "../shared/service/psi.service";
import { UserComponent } from './user/user/user.component';

defineLocale('zh-cn', zhCnLocale);  // 定义中文日期插件

@NgModule({
   imports: [
      CommonModule,
      FormsModule,
      BsDatepickerModule.forRoot(),
      NgbModule,
      TypeaheadModule.forRoot(),
      PopoverModule.forRoot(),
      NgbDatepickerModule,
      EasyUIModule,
      NgbTabsetModule,
      NgbPaginationModule,
      NgbDropdownModule,
      NgxEchartsModule,
      Select2Module,
      ReactiveFormsModule,
      ClipboardModule,
      RouterModule.forChild(adminRoutes),
      UEditorModule.forRoot(
         {
          js: [
             `assets/vendors/ueditor/ueditor.config.js`,
             `assets/vendors/ueditor/ueditor.all.js`
          ],
         // 默认前端配置项
          options: {
             UEDITOR_HOME_URL: 'assets/vendors/ueditor/'
          }
      }
     )
   ],
   declarations: [
      DashboardComponent,
      PaginationPipe,
      SortPipe,
      TabularPipe,
      JobStatusPipe,
      ArticleFilterPipe,
      HighLightPipe,
      ArrayToString,
      UnpublishedComponent,
      PublishedComponent,
      FilesUploadComponent,
      ConfigurationComponent,
      UsersComponent,
      ProductTimeComponent,
      CompanyComponent,
      NodesToStringPipe,
      DateCommonPipePipe,
      SupplierComponent,
      CustomerComponent,
      StoreComponent,
      WarehouseComponent,
      ProductComponent,
      PurchaseComponent,
      SaleComponent,
      StockComponent,
      UserComponent,
      HtmlPipe
   ],
   providers: [CommonService, BsModalService, AuthGuard, InfoService, PsiService, BusinessService, NgbTabsetConfig, UserService, ProductService,
      AuthorityService, SampleService, ArticleService, ProductTimeService,
      DatePipe, PaginationPipe,
      ArrayToString,
      ArticleFilterPipe,
      SortPipe,
      TabularPipe,
      DateCommonPipePipe,
      NodesToStringPipe],

})
export class AdminModule {
}
