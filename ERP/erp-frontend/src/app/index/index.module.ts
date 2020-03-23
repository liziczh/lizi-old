/**
 * @author QM.JM
 * @createTime 2017/11/20
 */

import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule} from '@angular/router';

import {indexRoutes} from './index.routing';
import {IndexComponent} from './index.component';

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(indexRoutes)
  ],
  exports: [RouterModule],
  declarations: [IndexComponent]
})
export class IndexModule { }
