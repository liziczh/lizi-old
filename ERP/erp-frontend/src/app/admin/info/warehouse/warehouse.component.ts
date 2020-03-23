import {Component, OnInit, TemplateRef} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {BsModalRef, BsModalService} from 'ngx-bootstrap';
import {InfoService} from '../../../shared/service/info.service';
import {ToastrService} from 'ngx-toastr';
import {StorageUtil} from '../../../shared/utils/storage.util';

export class Warehouse {
    id: number;  // ID
    name: string;  // 仓库名称
    code: string;  // 仓库CODE
    manager: string; // 仓库负责人
    phone: string;  // 仓库手机
    address: string; // 仓库地址
    memo: string; // 备注
    createTime: Date; // 创建时间
    updateTime: Date; // 更新时间

    constructor(
        id: number, name: string, code: string, manager: string, phone: string,
        address: string, memo: string, createTime: Date, updateTime: Date
    ) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.manager = manager;
        this.phone = phone;
        this.address = address;
        this.memo = memo;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }
}

@Component({
  selector: 'app-warehouse',
  templateUrl: './warehouse.component.html',
  styleUrls: ['./warehouse.component.css']
})
export class WarehouseComponent implements OnInit {

    warehouseList: Array<any>; // 初始化仓库列表

    searchInput: FormControl = new FormControl();  // 搜索框中输入的值

    totalSize: number;  // 仓库总数
    pageNum = 1;  // 当前页
    limit = 10;  // 每页大小

    warehouseForm: FormGroup;  // 仓库表单
    deletedWarehouse: any; // 被删除的仓库

    modalRef: BsModalRef;  // 模态框
    loading: boolean;  // 列表加载状态
    valid: boolean; // 校验状态
    ifAdd: boolean; // 新增/修改

    user: any; // 用户

    constructor(private _service: InfoService, private modalService: BsModalService,
                private fb: FormBuilder, private toastr: ToastrService, private storageUtil: StorageUtil) {
        // 实时搜索
        this.searchInput.valueChanges
            .debounceTime(500)
            .subscribe(value => {
                this.initWarehouseList();
            });
    }

    ngOnInit() {
        this.user = this.storageUtil.get('currentUser');
        // 初始化仓库列表
        this.initWarehouseList();
    }

    /**
     * 初始化仓库列表
     */
    private initWarehouseList() {
        this.loading = true;
        this._service.pagingQueryWarehouseList(this.searchInput.value, this.pageNum, this.limit).subscribe(response => {
            this.warehouseList = response.data;
            console.log(this.warehouseList);
            this.loading = false;
        });
    }

    /**
     * 分页事件
     * @param event
     */
    onPageChange(event) {
        this.pageNum = event.pageNumber;
        this.initWarehouseList();
    }

    /**
     * 初始化添加仓库表单
     */
    private initAddWarehouseForm() {
        this.warehouseForm = this.fb.group({
            id: [''],
            name: ['', [Validators.required, Validators.maxLength(20)]],
            code: ['', [Validators.required, Validators.maxLength(20), Validators.pattern(/^[A-Za-z0-9]+$/)]],
            manager: ['', [Validators.required, Validators.maxLength(10)]],
            phone: ['', [Validators.required, Validators.pattern(/^[1]([3-9])[0-9]{9}$/)]],
            address: ['', [Validators.maxLength(30)]],
            memo: ['', [Validators.maxLength(100)]],
            createTime: [''],
            updateTime: ['']
        });
    }

    /**
     * 初始化编辑仓库表单
     */
    private initEditWarehouseForm(row: any) {
        this.warehouseForm = this.fb.group({
            id: [row.id],
            name: [row.name, [Validators.required, Validators.maxLength(20)]],
            code: [row.code, [Validators.required, Validators.maxLength(20), Validators.pattern(/^[A-Za-z0-9]+$/)]],
            manager: [row.manager, [Validators.required, Validators.maxLength(10)]],
            phone: [row.phone, [Validators.required, Validators.pattern(/^[1]([3-9])[0-9]{9}$/)]],
            address: [row.address, [Validators.maxLength(30)]],
            memo: [row.memo, [Validators.maxLength(100)]],
            createTime: [row.createTime],
            updateTime: [row.updateTime]
        });
    }

    /**
     * 打开添加仓库模态框
     */
    openAddWarehouse(template: TemplateRef<any>) {
        this.initAddWarehouseForm();
        this.ifAdd = true;
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
     * 提交添加仓库表单
     */
    submitAddWarehouse() {
        console.log(this.warehouseForm.value);
        this._service.addWarehouse(this.warehouseForm.value).subscribe(response => {
            this.initWarehouseList();
            this.modalRef.hide();
            this.toastr.success('添加成功！', 'Success');
        });
    }

    /**
     * 打开编辑仓库模态框
     */
    openEditWarehouse(template: TemplateRef<any>, row: any) {
        this.initEditWarehouseForm(row);
        this.ifAdd = false;
        this.valid = false;
        this.modalRef = this.modalService.show(template, Object.assign({},
            {
                class: 'gray modal-lg',
                ignoreBackdropClick: true, // 忽略背景点击
                keyboard: false
            }));
    }

    /**
     * 提交编辑仓库表单
     * @param warehouse
     */
    submitEditWarehouse() {
        this._service.modifyWarehouse(this.warehouseForm.value).subscribe(response => {
            this.initWarehouseList();
            this.modalRef.hide();
            this.toastr.success('修改成功！', 'Success');
        });
    }

    /**
     * 打开删除仓库模态框
     */
    openDeleteWarehouse(template: TemplateRef<any>, row: any) {
        this.deletedWarehouse = row;
        // 打开弹窗
        this.modalRef = this.modalService.show(template);
    }

    /**
     * 删除仓库
     */
    deleteWarehouse(warehouseId: number) {
        this._service.deleteWarehouse(warehouseId).subscribe(response => {
            this.initWarehouseList();
            this.modalRef.hide();
            this.toastr.success('删除成功！', 'Success');
        });
    }

}
