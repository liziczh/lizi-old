/**
 * @author QM.JM
 * @createTime 2017/11/20
 */

import {Routes} from '@angular/router';

import {LayoutComponent} from './layout.component';

export const layoutRoutes: Routes = [
  {
    path: '',
    component: LayoutComponent,
    children: [
      { path: '', loadChildren: '../admin/admin.module#AdminModule' },
    ]
  }
];
