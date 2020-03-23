/**
 * @author QM.JM
 * @createTime 2017/11/20
 */

import {RouterModule, Routes} from '@angular/router';
import {AuthGuard} from './shared/guard';
import {NgModule} from '@angular/core';
import { ErrorComponent } from './error/error.component';

const routes: Routes = [
   {path: 'login', loadChildren: './login/login.module#LoginModule'},
   {
      path: '',
      redirectTo: 'login', pathMatch: 'full'
   },
   {
      path: 'admin',
      loadChildren: './layout/layout.module#LayoutModule',
      canActivate: [AuthGuard]
   },

   {   path: '**', redirectTo: 'not-found' },
   {   path: '403', component: ErrorComponent}
];

@NgModule({
   imports: [RouterModule.forRoot(routes)],
   exports: [RouterModule]
})
export class AppRoutingModule {
}
