import {Component, OnInit, TemplateRef} from '@angular/core';
import {InfoService} from '../../../shared/service/info.service';
import {BsModalRef, BsModalService} from 'ngx-bootstrap';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ToastrService} from 'ngx-toastr';
import {PsiService} from '../../../shared/service/psi.service';
import {DatePipe} from '@angular/common';
import {StorageUtil} from '../../../shared/utils/storage.util';

@Component({
    selector: 'app-purchase',
    templateUrl: './purchase.component.html',
    styleUrls: ['./purchase.component.css']
})
export class PurchaseComponent implements OnInit {

    purchaseRecordList: Array<any>; // 初始化采购记录列表
    supplierList: Array<any>; // 供应商列表
    warehouseList: Array<any>; // 仓库列表
    productList: Array<any>; // 产品列表

    startTime = new Date(new Date().getTime() - 7 * 24 * 60 * 60 * 1000); // 开始时间
    endTime = new Date(new Date().getTime() + 1 * 24 * 60 * 60 * 1000); // 结束时间

    totalSize: number;  // 记录总数
    pageNum = 1;  // 当前页
    limit = 10;  // 每页大小

    purchaseForm: FormGroup;  // 采购入库表单
    purchaseDetailForm: FormGroup;  // 采购详情表单
    refundPurchaseRecord: any; // 被退货的记录

    modalRef: BsModalRef;  // 模态框
    loading: boolean;  // 列表加载状态
    valid: boolean; // 校验状态
    ifAdd: boolean; // 新增/修改

    user: any; // 用户

    constructor(private _service: PsiService, private infoService: InfoService, private modalService: BsModalService,
                private fb: FormBuilder, private toastr: ToastrService, private datePipe: DatePipe, private storageUtil: StorageUtil) {
    }

    ngOnInit() {
        this.user = this.storageUtil.get('currentUser');
        // 初始化采购订单列表
        this.initPurchaseRecordList();
        // 初始化产品列表
        this.initProductList();
        // 初始化供应商列表
        this.initSupplierList();
        // 初始化仓库列表
        this.initWarehouseList();
    }

    /**
     * 初始化采购订单列表
     */
    initPurchaseRecordList() {
        this.loading = true;
        if (this.startTime < this.endTime) {
            this._service.pagingQueryPurchaseRecordList(
                this.datePipe.transform(this.startTime, 'yyyy-MM-dd HH:mm:ss'),
                this.datePipe.transform(this.endTime, 'yyyy-MM-dd HH:mm:ss'),
                this.pageNum,
                this.limit
            ).subscribe(response => {
                this.purchaseRecordList = response['data'];
                console.log(this.purchaseRecordList);
                this.loading = false;
            });
        } else {
            this.toastr.error('请将开始时间设置在结束时间之前');
        }
    }

    /**
     * 初始化供应商列表
     */
    private initSupplierList() {
        this.infoService.querySupplierList().subscribe(response => {
            this.supplierList = response['data'];
            console.log(this.supplierList);
        });
    }

    /**
     * 初始化仓库列表
     */
    private initWarehouseList() {
        this.infoService.queryWarehouseList().subscribe(response => {
            this.warehouseList = response['data'];
            console.log(this.warehouseList);
        });
    }

    /**
     * 初始化产品列表
     */
    private initProductList() {
        this.infoService.queryProductList().subscribe(response => {
            this.productList = response['data'];
            console.log(this.productList);
        });
    }

    /**
     * 分页事件
     * @param event
     */
    onPageChange(event) {
        this.pageNum = event.pageNumber;
        this.initPurchaseRecordList();
    }

    /**
     * 初始化采购入库表单
     */
    private initPurchaseInStockForm() {
        this.purchaseForm = this.fb.group({
            productId: ['', [Validators.required]],
            purchasePrice: ['', [Validators.required, Validators.pattern(/^[0-9]+(.[0-9]{2})?$/)]],
            count: ['', [Validators.required, Validators.pattern(/^[0-9]*$/)]],
            supplierId: ['', [Validators.required]],
            warehouseId: ['', [Validators.required]],
            type: ['', [Validators.required]],
            memo: ['', [Validators.maxLength(100)]]
        });
    }

    /**
     * 初始化采购记录详情表单
     */
    private initPurchaseRecordDetailForm(row: any) {
        this.purchaseDetailForm = this.fb.group({
            supplier: [row.supplier.name],
            product: [row.product.name],
            purchasePrice: [row.purchasePrice],
            count: [row.count],
            date: [this.datePipe.transform(row.date, 'yyyy-MM-dd HH:mm:ss')],
            warehouse: [row.warehouse.name],
            type: [row.type],
            memo: [row.memo]
        });
    }

    /**
     * 打开采购入库模态框
     */
    openPurchaseInStock(template: TemplateRef<any>) {
        this.initPurchaseInStockForm();
        this.valid = false;
        this.modalRef = this.modalService.show(template, Object.assign({},
            {
                class: 'gray modal-lg',
                ignoreBackdropClick: true, // 忽略背景点击
                keyboard: false
            })
        );
    }

    /**
     * 提交采购入库请求
     */
    submitPurchaseInStock() {
        console.log(this.purchaseForm.value);
        this._service.purchaseInStock(this.purchaseForm.value).subscribe(response => {
            this.initPurchaseRecordList();
            this.modalRef.hide();
            this.toastr.success('入库成功！', 'Success');
        });
    }

    /**
     * 打开查看采购记录详情模态框
     */
    openPurchaseRecordDetail(template: TemplateRef<any>, row: any) {
        this.initPurchaseRecordDetailForm(row);
        this.valid = false;
        this.modalRef = this.modalService.show(template, Object.assign({},
            {
                class: 'gray modal-lg',
                ignoreBackdropClick: true, // 忽略背景点击
                keyboard: false
            })
        );
    }


    /**
     * 打开采购退货模态框
     */
    openPurchaseRefund(template: TemplateRef<any>, row: any) {
        this.refundPurchaseRecord = row;
        console.log(row);
        // 打开弹窗
        this.modalRef = this.modalService.show(template);
    }

    /**
     * 提交采购退货请求
     */
    submitPurchaseRefund() {
        const purchaseRecord = {
            productId: this.refundPurchaseRecord.product.id,
            purchasePrice: this.refundPurchaseRecord.purchasePrice,
            count: this.refundPurchaseRecord.count,
            supplierId: this.refundPurchaseRecord.supplier.id,
            warehouseId: this.refundPurchaseRecord.warehouse.id,
            memo: this.refundPurchaseRecord.memo
        };
        this._service.purchaseRefund(purchaseRecord).subscribe(response => {
            if (response.code === 100200) {
                this.initPurchaseRecordList();
                this.modalRef.hide();
                this.toastr.success('退货成功！', 'Success');
            } else if (response.code === 100400) {
                this.toastr.error(response.msg);
            } else {
                this.toastr.error(response.msg);
            }
        });
    }


}
