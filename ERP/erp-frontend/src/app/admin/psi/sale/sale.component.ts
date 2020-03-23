import {Component, OnInit, TemplateRef} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {BsModalRef, BsModalService} from 'ngx-bootstrap';
import {PsiService} from '../../../shared/service/psi.service';
import {InfoService} from '../../../shared/service/info.service';
import {ToastrService} from 'ngx-toastr';
import {DatePipe} from '@angular/common';
import {StorageUtil} from '../../../shared/utils/storage.util';

@Component({
    selector: 'app-sale',
    templateUrl: './sale.component.html',
    styleUrls: ['./sale.component.css']
})
export class SaleComponent implements OnInit {

    saleRecordList: Array<any>; // 初始化销售记录列表
    customerList: Array<any>; // 客户列表
    storeList: Array<any>; // 门店列表
    warehouseList: Array<any>; // 仓库列表
    productList: Array<any>; // 产品列表

    startTime = new Date(new Date().getTime() - 7 * 24 * 60 * 60 * 1000); // 开始时间
    endTime = new Date(new Date().getTime() + 1 * 24 * 60 * 60 * 1000); // 结束时间

    totalSize: number;  // 记录总数
    pageNum = 1;  // 当前页
    limit = 10;  // 每页大小

    saleForm: FormGroup;  // 销售入库表单
    saleDetailForm: FormGroup;  // 销售详情表单
    refundSaleRecord: any; // 被退货的记录

    modalRef: BsModalRef;  // 模态框
    loading: boolean;  // 列表加载状态
    valid: boolean; // 校验状态

    user: any; // 用户

    constructor(private _service: PsiService, private infoService: InfoService, private modalService: BsModalService,
                private fb: FormBuilder, private toastr: ToastrService, private datePipe: DatePipe, private storageUtil: StorageUtil) {
    }

    ngOnInit() {
        this.user = this.storageUtil.get('currentUser');
        // 初始化销售订单列表
        this.initSaleRecordList();
        // 初始化产品列表
        this.initProductList();
        // 初始化客户列表
        this.initCustomerList();
        // 初始化门店列表
        this.initStoreList();
        // 初始化仓库列表
        this.warehouseList = new Array();
    }

    /**
     * 初始化销售订单列表
     */
    initSaleRecordList() {
        this.loading = true;
        if (this.startTime < this.endTime) {
            this._service.pagingQuerySaleRecordList(
                this.datePipe.transform(this.startTime, 'yyyy-MM-dd HH:mm:ss'),
                this.datePipe.transform(this.endTime, 'yyyy-MM-dd HH:mm:ss'),
                this.pageNum,
                this.limit
            ).subscribe(response => {
                this.saleRecordList = response['data'];
                console.log(this.saleRecordList);
                this.loading = false;
            });
        } else {
            this.toastr.error('请将开始时间设置在结束时间之前');
        }
    }

    /**
     * 初始化客户列表
     */
    private initCustomerList() {
        this.infoService.queryCustomerList().subscribe(response => {
            this.customerList = response['data'];
        });
    }

    /**
     * 初始化门店列表
     */
    private initStoreList() {
        this.infoService.queryStoreList().subscribe(response => {
            this.storeList = response['data'];
        });
    }

    /**
     * 初始化产品列表
     */
    private initProductList() {
        this.infoService.queryProductList().subscribe(response => {
            this.productList = response['data'];
        });
    }

    /**
     * 分页事件
     * @param event
     */
    onPageChange(event) {
        this.pageNum = event.pageNumber;
        this.initSaleRecordList();
    }

    /**
     * 初始化销售入库表单
     */
    private initSaleOutStockForm() {
        this.saleForm = this.fb.group({
            productId: ['', [Validators.required]],
            salePrice: ['', [Validators.required, Validators.pattern(/^[0-9]+(.[0-9]{2})?$/)]],
            count: ['', [Validators.required, Validators.pattern(/^[0-9]*$/)]],
            customerId: ['', [Validators.required]],
            warehouseId: ['', [Validators.required]],
            storeId: ['', [Validators.required]],
            type: ['', [Validators.required]],
            memo: ['', [Validators.maxLength(100)]]
        });
    }

    /**
     * 初始化销售记录详情表单
     */
    private initSaleRecordDetailForm(row: any) {
        this.saleDetailForm = this.fb.group({
            customer: [row.customer.name],
            product: [row.product.name],
            salePrice: [row.salePrice],
            count: [row.count],
            date: [this.datePipe.transform(row.date, 'yyyy-MM-dd HH:mm:ss')],
            warehouse: [row.warehouse.name],
            store: [row.store.name],
            type: [row.type],
            memo: [row.memo]
        });
    }

    /**
     * 打开销售入库模态框
     */
    openSaleOutStock(template: TemplateRef<any>) {
        this.warehouseList = new Array<any>();
        this.initSaleOutStockForm();
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
     * 商品下拉选项发生改变
     */
    onProductChange(event) {
        // 获取仓库列表
        const productId = event.currentValue;
        this._service.queryWarehouseListByProductId(productId).subscribe(response => {
            console.log(response['data']);
            this.warehouseList = response['data'];
        });
    }

    /**
     * 提交销售出库请求
     */
    submitSaleOutStock() {
        console.log(this.saleForm.value);
        this._service.saleOutStock(this.saleForm.value).subscribe(response => {
            console.log(response);
            if (response.code === 100200) {
                this.initSaleRecordList();
                this.modalRef.hide();
                this.toastr.success('销售成功！', 'Success');
            } else if (response.code === 100400) {
                this.toastr.error(response.msg);
            } else {
                this.toastr.error(response.msg);
            }
        });
    }

    /**
     * 打开查看销售记录详情模态框
     */
    openSaleRecordDetail(template: TemplateRef<any>, row: any) {
        this.initSaleRecordDetailForm(row);
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
     * 打开销售退货模态框
     */
    openSaleRefund(template: TemplateRef<any>, row: any) {
        this.refundSaleRecord = row;
        console.log(row);
        // 打开弹窗
        this.modalRef = this.modalService.show(template);
    }

    /**
     * 提交销售退货请求
     */
    submitSaleRefund() {
        const saleRecord = {
            productId: this.refundSaleRecord.product.id,
            salePrice: this.refundSaleRecord.salePrice,
            count: this.refundSaleRecord.count,
            customerId: this.refundSaleRecord.customer.id,
            warehouseId: this.refundSaleRecord.warehouse.id,
            storeId: this.refundSaleRecord.store.id,
            memo: this.refundSaleRecord.memo
        };
        this._service.saleRefund(saleRecord).subscribe(response => {
            this.initSaleRecordList();
            this.modalRef.hide();
            this.toastr.success('退货成功！', 'Success');
        });
    }


}
