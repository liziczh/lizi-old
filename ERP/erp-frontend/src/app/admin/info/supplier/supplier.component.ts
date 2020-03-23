import {Component, OnInit, TemplateRef} from '@angular/core';
import {InfoService} from '../../../shared/service/info.service';
import {BsModalRef, BsModalService} from 'ngx-bootstrap';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {ToastrService} from 'ngx-toastr';
import {StorageUtil} from '../../../shared/utils/storage.util';

export class Supplier {
    id: number;  // ID
    name: string;  // 供应商名称
    code: string;  // 供应商CODE
    type: string;  // 供应商类型
    contact: string; // 供应商联系人
    email: string;  // 供应商邮箱
    phone: string;  // 供应商手机
    phone2: string; // 供应商手机2
    account: string; // 供应商账户
    address: string; // 供应商地址
    memo: string; // 备注
    createTime: Date; // 创建时间
    updateTime: Date; // 更新时间

    constructor(
        id: number, name: string, code: string, type: string, contact: string, email: string, phone: string, phone2: string,
        account: string, address: string, memo: string, createTime: Date, updateTime: Date
    ) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.type = type;
        this.contact = contact;
        this.email = email;
        this.phone = phone;
        this.phone2 = phone2;
        this.account = account;
        this.address = address;
        this.memo = memo;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }
}

@Component({
    selector: 'app-supplier',
    templateUrl: './supplier.component.html',
    styleUrls: ['./supplier.component.css']
})
export class SupplierComponent implements OnInit {

    supplierList: Array<any>; // 初始化供应商列表
    supplierTypeList: Array<any>; // 初始化供应商类型列表

    searchInput: FormControl = new FormControl();  // 搜索框中输入的值
    typeListOptions: Array<any>; // 供应商类型选择项
    selectedTypeId = '0'; // 选中的类型ID
    selectedTypeText = ''; // 选中的类型文本

    selectedTypeOnForm: any; // 表单选中的类型

    totalSize: number;  // 供应商总数
    pageNum = 1;  // 当前页
    limit = 10;  // 每页大小

    supplierForm: FormGroup;  // 供应商表单
    deletedSupplier: any; // 被删除的供应商

    modalRef: BsModalRef;  // 模态框
    loading = true;  // 列表加载状态
    valid: boolean; // 校验状态
    ifAdd: boolean; // 新增/修改

    user: any; // 当前用户

    constructor(private _service: InfoService, private modalService: BsModalService,
                private fb: FormBuilder, private toastr: ToastrService, private storageUtil: StorageUtil) {
        // 实时搜索
        this.searchInput.valueChanges
            .debounceTime(500)
            .subscribe(value => {
                this.initSupplierList();
            });
    }

    ngOnInit() {
        this.user = this.storageUtil.get('currentUser');
        // 初始化供应商类型列表
        this._service.querySupplierTypeList().subscribe(response => {
            this.typeListOptions = [{'id': '0', 'name': '全部'}];
            this.supplierTypeList = response.data;
            this.typeListOptions = this.typeListOptions.concat(this.supplierTypeList);
        });
        // 初始化供应商列表
        this.initSupplierList();
    }

    /**
     * 初始化供应商列表
     */
    private initSupplierList() {
        this.loading = true;
        this._service.pagingQuerySupplierList(this.selectedTypeId, this.searchInput.value, this.pageNum, this.limit).subscribe(response => {
            this.supplierList = response.data;
            this.totalSize = response.totalRecord;
            console.log(this.supplierList);
            this.loading = false;
        });
    }

    /**
     * 分类改变事件
     * @param event  当前的分类对象
     */
    onTypeChange(event) {
        this.selectedTypeText = this.typeListOptions.find(p => p.id === event.currentValue).name;
        this.pageNum = 1;
        this.initSupplierList();
    }

    /**
     * 表单分类改变事件
     * @param event  当前的分类对象
     */
    onFormTypeChange(event) {
        this.selectedTypeOnForm = this.supplierTypeList.find( p => p.id === event.currentValue);
        console.log(this.selectedTypeOnForm);
    }

    /**
     * 分页事件
     * @param event
     */
    onPageChange(event) {
        this.pageNum = event.pageNumber;
        this.initSupplierList();
    }

    /**
     * 初始化添加供应商表单
     */
    private initAddSupplierForm() {
        this.selectedTypeOnForm = null;
        this.supplierForm = this.fb.group({
            id: [''],
            name: ['', [Validators.required, Validators.maxLength(20)]],
            code: ['', [Validators.required, Validators.maxLength(20), Validators.pattern(/^[A-Za-z0-9]+$/)]],
            type: ['', [Validators.required]],
            contact: ['', [Validators.required, Validators.maxLength(10)]],
            email: ['', [Validators.required, Validators.email, Validators.maxLength(30)]],
            phone: ['', [Validators.required, Validators.pattern(/^[1]([3-9])[0-9]{9}$/)]],
            phone2: ['', [Validators.pattern(/^[1]([3-9])[0-9]{9}$/)]],
            account: ['', Validators.pattern(/^\d{19}$/)],
            address: ['', [Validators.maxLength(30)]],
            memo: ['', [Validators.maxLength(100)]],
            createTime: [''],
            updateTime: ['']
        });
    }

    /**
     * 初始化编辑供应商表单
     */
    private initEditSupplierForm(row: any) {
        this.selectedTypeOnForm = this.supplierTypeList.find(p => p.name === row.type.name);
        console.log(row);
        this.supplierForm = this.fb.group({
            id: [row.id],
            name: [row.name, [Validators.required, Validators.maxLength(20)]],
            code: [row.code, [Validators.required, Validators.maxLength(20), Validators.pattern(/^[A-Za-z0-9]+$/)]],
            type: [row.type.name, [Validators.required]],
            contact: [row.contact, [Validators.required, Validators.maxLength(10)]],
            email: [row.email, [Validators.required, Validators.email, Validators.maxLength(30)]],
            phone: [row.phone, [Validators.required, Validators.pattern(/^[1]([3-9])[0-9]{9}$/)]],
            phone2: [row.phone2, [Validators.pattern(/^[1]([3-9])[0-9]{9}$/)]],
            account: [row.account, Validators.pattern(/^\d{19}$/)],
            address: [row.address, [Validators.maxLength(30)]],
            memo: [row.memo, [Validators.maxLength(100)]],
            createTime: [row.createTime],
            updateTime: [row.updateTime]
        });
    }

    /**
     * 打开添加供应商模态框
     */
    openAddSupplier(template: TemplateRef<any>) {
        this.initAddSupplierForm();
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
     * 提交添加供应商表单
     */
    submitAddSupplier() {
        // 由文本获取类型对象
        console.log(this.supplierForm.value);
        this.supplierForm.value.type = this.selectedTypeOnForm;
        console.log(this.supplierForm.value);
        this._service.addSupplier(this.supplierForm.value).subscribe(response => {
            this.initSupplierList();
            this.modalRef.hide();
            this.toastr.success('添加成功！', 'Success');
        });
    }

    /**
     * 打开编辑供应商模态框
     */
    openEditSupplier(template: TemplateRef<any>, row: any) {
        this.initEditSupplierForm(row);
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
     * 提交编辑供应商表单
     */
    submitEditSupplier() {
        // 由文本获取类型对象
        this.supplierForm.value.type = this.selectedTypeOnForm;
        this._service.modifySupplier(this.supplierForm.value).subscribe(response => {
            this.initSupplierList();
            this.modalRef.hide();
            this.toastr.success('修改成功！', 'Success');
        });
    }

    /**
     * 打开删除供应商模态框
     */
    openDeleteSupplier(template: TemplateRef<any>, row: any) {
        this.deletedSupplier = row;
        // 打开弹窗
        this.modalRef = this.modalService.show(template);
    }

    /**
     * 删除供应商
     */
    deleteSupplier(supplierId: number) {
        this._service.deleteSupplier(supplierId).subscribe(response => {
            this.initSupplierList();
            this.modalRef.hide();
            this.toastr.success('删除成功！', 'Success');
        });
    }


}
