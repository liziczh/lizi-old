import {Component, OnInit, TemplateRef} from '@angular/core';
import {BsModalRef, BsModalService} from 'ngx-bootstrap';
import {ToastrService} from 'ngx-toastr';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {BusinessService} from '../../../shared/service/business.service';
import {DatePipe} from '@angular/common';

export class ProductTime {
   id: number;  // ID
   productId: number;  // 产品ID
   productName: string;  // 产品名称
   productCode: string;  // 产品CODE
   startTime: Date;  // 开始时间
   endTime: Date;  // 结束时间
   manager: string;  // 管理员

   constructor(
      id: number, productId: number, productName: string, productCode: string,
      startTime: Date, endTime: Date, manager: string
   ) {
      this.id = id;
      this.productId = productId;
      this.productName = productName;
      this.productCode = productCode;
      this.startTime = startTime;
      this.endTime = endTime;
      this.manager = manager;
   }
}

@Component({
   selector: 'app-product-time',
   templateUrl: './product-time.component.html',
   styleUrls: ['./product-time.component.css']
})
export class ProductTimeComponent implements OnInit {

   // 初始化产品时间
   productTimeList = null;  // 产品时间列表（初始化）

   productTime: FormGroup;  // 产品时间表单
   deletedProductTime: ProductTime; // 被删除的产品时间
   noTimeLimitProductList = [];  // 未添加时间限制的产品

   modalRef: BsModalRef; // 模态框
   loading = true;

   constructor(private _service: BusinessService, private modalService: BsModalService,
               private fb: FormBuilder, private toastr: ToastrService, private datePipe: DatePipe) {
   }

   ngOnInit() {
      // 初始化查询全部产品时间
      this.initProductTimeList();
   }

   /**
    * 初始化查询全部产品时间
    */
   private initProductTimeList() {
      this.loading = true;
      this._service.queryProductTimeList().subscribe(response => {
         this.productTimeList = response['data'];
         this.loading = false;
      });
   }

   /**
    * 打开添加产品时间弹窗
    * @param template
    */
   openAddProductTime(template: TemplateRef<any>) {
      this.noTimeLimitProductList = [];
      this._service.queryProductListNoInProductTime().subscribe(response => {
         this.noTimeLimitProductList = response.data;
         if (this.noTimeLimitProductList.length > 0) {
            this.productTime = this.fb.group({
               productId: [this.noTimeLimitProductList[0].id, [Validators.required]],
               startTime: [null, [Validators.required]],
               endTime: [null, [Validators.required]],
               manager: ['', [Validators.email, Validators.maxLength(30)]]
            });
            this.modalRef = this.modalService.show(template);
         } else {
            this.toastr.warning('暂无可添加时间限制的产品');
         }
      });
   }

   /**
    * 提交添加产品时间表单
    */
   submitAddProductTime(productTime: ProductTime) {
      if (productTime.startTime < productTime.endTime) {
         this._service.addProductTime(
            productTime.productId,
            this.datePipe.transform(productTime.startTime.setHours(8, 0, 0, 0), 'yyyy-MM-dd HH:mm:ss'),
            this.datePipe.transform(productTime.endTime.setHours(8, 0, 0, 0), 'yyyy-MM-dd HH:mm:ss'),
            productTime.manager
         ).subscribe(response => {
            this.initProductTimeList();
            this.modalRef.hide();
         });
      } else {
         this.toastr.error('请将开始时间设置在结束时间之前');
      }
   }

   /**
    * 打开编辑产品时间弹窗
    * @param row
    * @param template
    */
   openEditProductTime(row: any, template: TemplateRef<any>) {
      // 日期：string --> Date
      this.productTime = this.fb.group({
         id: [row.id],
         startTime: [new Date(row.startTime), [Validators.required]],
         endTime: [new Date(row.endTime), [Validators.required]],
         manager: [row.manager, [Validators.pattern(/^[a-z0-9]+(\.[_a-z0-9]+)*@[a-z0-9-]+(\.[a-z0-9-]+)*(\.[a-z]{2,15})$/),
            Validators.maxLength(30)]]
      });
      // 打开弹窗
      this.modalRef = this.modalService.show(template);
   }

   /**
    * 提交修改产品时间表单
    */
   submitEditProductTime(productTime: ProductTime) {
      // 如果开始时间 < 结束时间，则修改；否则错误提示。
      if (productTime.startTime < productTime.endTime) {
         // 日期：Date --> string
         this._service.modifyProductTime(
            productTime.id,
            this.datePipe.transform(productTime.startTime.setHours(8, 0, 0, 0), 'yyyy-MM-dd HH:mm:ss'),
            this.datePipe.transform(productTime.endTime.setHours(8, 0, 0, 0), 'yyyy-MM-dd HH:mm:ss'),
            productTime.manager
         ).subscribe(response => {
            this.initProductTimeList();
            this.modalRef.hide();
         });
      } else {
         this.toastr.error('请将开始时间设置在结束时间之前');
      }

   }

   /**
    * 打开删除产品时间弹窗
    */
   openDeleteProductTime(row: any, template: TemplateRef<any>) {
      this.deletedProductTime = row;
      this.modalRef = this.modalService.show(template);
   }

   /**
    * 删除单条产品时间
    */
   singleDeleteProductTime(deletedProductTime: ProductTime) {
      this._service.removeProductTime(deletedProductTime.id).subscribe(response => {
         this.initProductTimeList();
         this.modalRef.hide();
      });
   }

}
