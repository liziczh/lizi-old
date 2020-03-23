/**
 * @author QM.JM
 * @createTime 2017/11/20
 */

import {Routes} from '@angular/router';

import {DashboardComponent} from './dashboard/dashboard.component';
import {SupplierComponent} from './info/supplier/supplier.component';
import {CustomerComponent} from './info/customer/customer.component';
import {StoreComponent} from './info/store/store.component';
import {WarehouseComponent} from './info/warehouse/warehouse.component';
import {ProductComponent} from './info/product/product.component';
import {PurchaseComponent} from './psi/purchase/purchase.component';
import {SaleComponent} from './psi/sale/sale.component';
import {StockComponent} from './psi/stock/stock.component';
import {UserComponent} from './user/user/user.component';


export const adminRoutes: Routes = [

    {path: '', redirectTo: 'dashboard', pathMatch: 'full'},
    {path: 'dashboard', component: DashboardComponent},

    /* 基础资料 */
    {path: 'info/supplier', component: SupplierComponent},
    {path: 'info/customer', component: CustomerComponent},
    {path: 'info/store', component: StoreComponent},
    {path: 'info/warehouse', component: WarehouseComponent},
    {path: 'info/product', component: ProductComponent},

    /* 进销存 */
    {path: 'psi/purchase', component: PurchaseComponent},
    {path: 'psi/sale', component: SaleComponent},
    {path: 'psi/stock', component: StockComponent},

    /* 用户管理 */
    {path: 'user/user', component: UserComponent},

];
