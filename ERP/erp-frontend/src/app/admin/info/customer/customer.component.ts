import {Component, OnInit, TemplateRef} from '@angular/core';
import {BsModalRef, BsModalService} from 'ngx-bootstrap';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {ToastrService} from 'ngx-toastr';
import {InfoService} from '../../../shared/service/info.service';
import {StorageUtil} from '../../../shared/utils/storage.util';

export class Customer {
    id: number;  // ID
    name: string;  // 客户名称
    code: string;  // 客户CODE
    type: string;  // 客户类型
    contact: string; // 客户联系人
    email: string;  // 客户邮箱
    phone: string;  // 客户手机
    phone2: string; // 客户手机2
    address: string; // 客户地址
    memo: string; // 备注
    createTime: Date; // 创建时间
    updateTime: Date; // 更新时间

    constructor(
        id: number, name: string, code: string, type: string, contact: string, email: string, phone: string, phone2: string,
        address: string, memo: string, createTime: Date, updateTime: Date
    ) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.type = type;
        this.contact = contact;
        this.email = email;
        this.phone = phone;
        this.phone2 = phone2;
        this.address = address;
        this.memo = memo;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }
}

@Component({
    selector: 'app-customer',
    templateUrl: './customer.component.html',
    styleUrls: ['./customer.component.css']
})
export class CustomerComponent implements OnInit {

    customerList: Array<any>; // 初始化客户列表
    customerTypeList: Array<any>; // 客户类型列表

    searchInput: FormControl = new FormControl();  // 搜索框中输入的值
    typeListOptions: Array<any>; // 类型选择项
    selectedTypeId = '0'; // 选中的类型ID
    selectedTypeText = ''; // 选中的类型文本

    selectedTypeOnForm: any; // 表单选中的类型

    totalSize: number;  // 客户总数
    pageNum = 1;  // 当前页
    limit = 10;  // 每页大小

    customerForm: FormGroup;  // 客户表单
    deletedCustomer: any; // 被删除的客户

    modalRef: BsModalRef;  // 模态框
    loading: boolean;  // 列表加载状态
    valid: boolean; // 校验状态
    ifAdd: boolean; // 新增/修改

    user: any; // 当前用户


    constructor(private _service: InfoService, private modalService: BsModalService,
                private fb: FormBuilder, private toastr: ToastrService, private storageUtil: StorageUtil) {
        // 实时搜索
        this.searchInput.valueChanges
            .debounceTime(500)
            .subscribe(value => {
                this.initCustomerList();
            });
    }

    ngOnInit() {
        this.user = this.storageUtil.get('currentUser');
        // 初始化客户类型列表
        this._service.queryCustomerTypeList().subscribe(response => {
            this.typeListOptions = [{'id': '0', 'name': '全部'}];
            this.customerTypeList = response.data;
            this.typeListOptions = this.typeListOptions.concat(this.customerTypeList);
        });
        // 初始化客户列表
        this.initCustomerList();
    }

    /**
     * 初始化客户列表
     */
    private initCustomerList() {
        this.loading = true;
        this._service.pagingQueryCustomerList(this.selectedTypeId, this.searchInput.value, this.pageNum, this.limit).subscribe(response => {
            this.customerList = response.data;
            console.log(this.customerList);
            this.loading = false;
        });
    }

    /**
     * 分类改变事件
     * @param event  当前的分类对象
     */
    onTypeChange(event) {
        this.selectedTypeText = this.typeListOptions.find(p => p.id === event.currentValue).value;
        this.pageNum = 1;
        this.initCustomerList();
    }

    /**
     * 表单分类改变事件
     * @param event  当前的分类对象
     */
    onFormTypeChange(event) {
        this.selectedTypeOnForm = this.customerTypeList.find( p => p.id === event.currentValue);
        console.log(this.selectedTypeOnForm);
    }

    /**
     * 分页事件
     * @param event
     */
    onPageChange(event) {
        this.pageNum = event.pageNumber;
        this.initCustomerList();
    }

    /**
     * 初始化添加客户表单
     */
    private initAddCustomerForm() {
        this.selectedTypeOnForm = null;
        this.customerForm = this.fb.group({
            id: [''],
            name: ['', [Validators.required, Validators.maxLength(20)]],
            code: ['', [Validators.required, Validators.maxLength(20), Validators.pattern(/^[A-Za-z0-9]+$/)]],
            type: ['', [Validators.required]],
            contact: ['', [Validators.required, Validators.maxLength(10)]],
            email: ['', [Validators.required, Validators.email, Validators.maxLength(30)]],
            phone: ['', [Validators.required, Validators.pattern(/^[1]([3-9])[0-9]{9}$/)]],
            phone2: ['', [Validators.pattern(/^[1]([3-9])[0-9]{9}$/)]],
            address: ['', [Validators.maxLength(30)]],
            memo: ['', [Validators.maxLength(100)]],
            createTime: [''],
            updateTime: ['']
        });
    }

    /**
     * 初始化编辑客户表单
     */
    private initEditCustomerForm(row: any) {
        this.selectedTypeOnForm = this.customerTypeList.find(p => p.name === row.type.name);
        console.log(this.selectedTypeOnForm);
        this.customerForm = this.fb.group({
            id: [row.id],
            name: [row.name, [Validators.required, Validators.maxLength(20)]],
            code: [row.code, [Validators.required, Validators.maxLength(20), Validators.pattern(/^[A-Za-z0-9]+$/)]],
            type: [row.type.name, [Validators.required]],
            contact: [row.contact, [Validators.required, Validators.maxLength(10)]],
            email: [row.email, [Validators.required, Validators.email, Validators.maxLength(30)]],
            phone: [row.phone, [Validators.required, Validators.pattern(/^[1]([3-9])[0-9]{9}$/)]],
            phone2: [row.phone2, [Validators.pattern(/^[1]([3-9])[0-9]{9}$/)]],
            address: [row.address, [Validators.maxLength(30)]],
            memo: [row.memo, [Validators.maxLength(100)]],
            createTime: [row.createTime],
            updateTime: [row.updateTime]
        });
    }

    /**
     * 打开添加客户模态框
     */
    openAddCustomer(template: TemplateRef<any>) {
        this.initAddCustomerForm();
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
     * 提交添加客户表单
     */
    submitAddCustomer() {
        // 获取类型对象
        console.log(this.customerForm.value);
        this.customerForm.value.type = this.selectedTypeOnForm;
        console.log(this.customerForm.value);
        this._service.addCustomer(this.customerForm.value).subscribe(response => {
            this.initCustomerList();
            this.modalRef.hide();
            this.toastr.success('添加成功！', 'Success');
        });
    }

    /**
     * 打开编辑客户模态框
     */
    openEditCustomer(template: TemplateRef<any>, row: any) {
        this.initEditCustomerForm(row);
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
     * 提交编辑客户表单
     * @param customer
     */
    submitEditCustomer() {
        // 获取类型对象
        this.customerForm.value.type = this.selectedTypeOnForm;
        this._service.modifyCustomer(this.customerForm.value).subscribe(response => {
            this.initCustomerList();
            this.modalRef.hide();
            this.toastr.success('修改成功！', 'Success');
        });
    }

    /**
     * 打开删除客户模态框
     */
    openDeleteCustomer(template: TemplateRef<any>, row: any) {
        this.deletedCustomer = row;
        // 打开弹窗
        this.modalRef = this.modalService.show(template);
    }

    /**
     * 删除客户
     */
    deleteCustomer(customerId: number) {
        this._service.deleteCustomer(customerId).subscribe(response => {
            this.initCustomerList();
            this.modalRef.hide();
            this.toastr.success('删除成功！', 'Success');
        });
    }

}
