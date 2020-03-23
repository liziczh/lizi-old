import {Component, OnInit, TemplateRef} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {BsModalRef, BsModalService} from 'ngx-bootstrap';
import {PsiService} from '../../../shared/service/psi.service';
import {InfoService} from '../../../shared/service/info.service';
import {ToastrService} from 'ngx-toastr';
import {DatePipe} from '@angular/common';
import {StorageUtil} from '../../../shared/utils/storage.util';

@Component({
    selector: 'app-stock',
    templateUrl: './stock.component.html',
    styleUrls: ['./stock.component.css']
})
export class StockComponent implements OnInit {

    stockList: Array<any>; // 初始化库存商品列表
    warehouseList: Array<any>; // 仓库列表
    productList: Array<any>; // 产品列表

    warehouseListOptions: Array<any>; // 类型选择项
    selectedWarehouseId = '0'; // 选中的类型ID
    selectedWarehouseText = ''; // 选中的类型文本

    totalSize: number;  // 记录总数
    pageNum = 1;  // 当前页
    limit = 10;  // 每页大小

    stockAllotForm: FormGroup;  // 库存调拨表单

    modalRef: BsModalRef;  // 模态框
    loading: boolean;  // 列表加载状态
    valid: boolean; // 校验状态

    user: any; // 用户

    constructor(private _service: PsiService, private infoService: InfoService, private modalService: BsModalService,
                private fb: FormBuilder, private toastr: ToastrService, private datePipe: DatePipe, private storageUtil: StorageUtil) {
    }

    ngOnInit() {
        this.user = this.storageUtil.get('currentUser');
        // 初始化仓库列表
        this.infoService.queryWarehouseList().subscribe(response => {
            this.warehouseListOptions = [{'id': '0', 'name': '全部'}];
            this.warehouseList = response['data'];
            this.warehouseListOptions = this.warehouseListOptions.concat(this.warehouseList);
        });
        // 初始化库存商品列表
        this.initStockList();
        this.productList = new Array();
    }

    /**
     * 初始化库存商品列表
     */
    private initStockList() {
        this.loading = true;
        this._service.pagingQueryStockList(this.selectedWarehouseId, this.pageNum, this.limit).subscribe(response => {
            this.stockList = response['data'];
            console.log(this.stockList);
            this.loading = false;
        });
    }

    /**
     * 仓库改变事件
     * @param event  当前的分类对象
     */
    onWarehouseChange(event) {
        this.selectedWarehouseText = this.warehouseListOptions.find(p => p.id === event.currentValue).value;
        this.pageNum = 1;
        this.initStockList();
    }

    /**
     * 分页事件
     * @param event
     */
    onPageChange(event) {
        this.pageNum = event.pageNumber;
        this.initStockList();
    }

    /**
     * 初始化库存调拨表单
     */
    private initStockAllotForm() {
        this.stockAllotForm = this.fb.group({
            warehouseId: ['', [Validators.required]],
            productId: ['', [Validators.required]],
            count: ['', Validators.compose([Validators.required, Validators.maxLength(10)])],
            allotWarehouseId: ['', [Validators.required]],
        });
    }

    /**
     * 打开库存调拨模态框
     */
    openStockAllot(template: TemplateRef<any>) {
        this.productList = new Array();
        this.initStockAllotForm();
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
     * 仓库下拉选项发生改变
     */
    onFormWarehouseChange(event) {
        // 获取产品列表
        const warehouseId = event.currentValue;
        this._service.queryProductListByWarehouseId(warehouseId).subscribe(response => {
            this.productList = response['data'];
        });
    }

    /**
     * 提交库存商品调拨请求
     */
    submitStockAllot() {
        console.log(this.stockAllotForm.value);
        this._service.stockAllot(this.stockAllotForm.value).subscribe(response => {
            if (response.code === 100200) {
                this.initStockList();
                this.modalRef.hide();
                this.toastr.success('商品调拨成功！', 'Success');
            } else if (response.code === 100400) {
                this.toastr.error(response.msg);
            } else {
                this.toastr.error(response.msg);
            }
        });
    }

}
