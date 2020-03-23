import {Component, OnInit, TemplateRef} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {BsModalRef, BsModalService} from 'ngx-bootstrap';
import {InfoService} from '../../../shared/service/info.service';
import {ToastrService} from 'ngx-toastr';
import {StorageUtil} from '../../../shared/utils/storage.util';

export class Product {
    id: number;  // ID
    name: string;  // 产品名称
    code: string;  // 产品CODE
    type: string; // 产品类型
    spec: string; // 产品规格
    brand: string;  // 产品品牌
    memo: string; // 备注
    createTime: Date; // 创建时间
    updateTime: Date; // 更新时间

    constructor(
        id: number, name: string, code: string, type: string, spec: string, brand: string,
        memo: string, createTime: Date, updateTime: Date
    ) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.type = type;
        this.spec = spec;
        this.brand = brand;
        this.memo = memo;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }
}

@Component({
    selector: 'app-product',
    templateUrl: './product.component.html',
    styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {
    productList: Array<any>; // 初始化产品列表
    productTypeList: Array<any>; // 初始化产品类型列表

    searchInput: FormControl = new FormControl();  // 搜索框中输入的值
    typeListOptions: Array<any>; // 类型选择项
    selectedTypeId = '0'; // 选中的类型ID
    selectedTypeText = ''; // 选中的类型文本

    selectedTypeOnForm: any; // 表单选中的类型

    totalSize: number;  // 产品总数
    pageNum = 1;  // 当前页
    limit = 10;  // 每页大小

    productForm: FormGroup;  // 产品表单
    deletedProduct: any; // 被删除的产品

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
                this.initProductList();
            });
    }

    ngOnInit() {
        this.user = this.storageUtil.get('currentUser');
        // 初始化产品类型列表
        this._service.queryProductTypeList().subscribe(response => {
            this.typeListOptions = [{'id': '0', 'name': '全部'}];
            this.productTypeList = response.data;
            this.typeListOptions = this.typeListOptions.concat(this.productTypeList);
        });
        // 初始化产品列表
        this.initProductList();
    }

    /**
     * 初始化商品列表
     */
    private initProductList() {
        this.loading = true;
        this._service.pagingQueryProductList(this.selectedTypeId, this.searchInput.value, this.pageNum, this.limit).subscribe(response => {
            this.productList = response.data;
            console.log(this.productList);
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
        this.initProductList();
    }

    /**
     * 表单分类改变事件
     * @param event  当前的分类对象
     */
    onFormTypeChange(event) {
        this.selectedTypeOnForm = this.productTypeList.find( p => p.id === event.currentValue);
        console.log(this.selectedTypeOnForm);
    }

    /**
     * 分页事件
     * @param event
     */
    onPageChange(event) {
        this.pageNum = event.pageNumber;
        this.initProductList();
    }

    /**
     * 初始化添加产品表单
     */
    private initAddProductForm() {
        this.selectedTypeOnForm = null;
        this.productForm = this.fb.group({
            id: [''],
            name: ['', [Validators.required, Validators.maxLength(20)]],
            code: ['', [Validators.required, Validators.maxLength(20), Validators.pattern(/^[A-Za-z0-9]+$/)]],
            type: ['', [Validators.required]],
            spec: ['', [Validators.required, Validators.maxLength(20)]],
            brand: ['', [Validators.required, Validators.maxLength(10)]],
            memo: ['', [Validators.maxLength(100)]],
            createTime: [''],
            updateTime: ['']
        });
    }

    /**
     * 初始化编辑产品表单
     */
    private initEditProductForm(row: any) {
        this.selectedTypeOnForm = this.productTypeList.find(p => p.name === row.type.name);
        this.productForm = this.fb.group({
            id: [row.id],
            name: [row.name, [Validators.required, Validators.maxLength(20)]],
            code: [row.code, [Validators.required, Validators.maxLength(20), Validators.pattern(/^[A-Za-z0-9]+$/)]],
            type: [row.type.name, [Validators.required]],
            spec: [row.spec, [Validators.required, Validators.maxLength(20)]],
            brand: [row.brand, [Validators.required, Validators.maxLength(10)]],
            memo: [row.memo, [Validators.maxLength(100)]],
            createTime: [row.createTime],
            updateTime: [row.updateTime]
        });
    }

    /**
     * 打开添加产品模态框
     */
    openAddProduct(template: TemplateRef<any>) {
        this.initAddProductForm();
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
     * 提交添加产品表单
     * @param product
     */
    submitAddProduct() {
        // 获取类型对象
        // 由文本获取类型对象
        console.log(this.productForm.value);
        this.productForm.value.type = this.selectedTypeOnForm;
        console.log(this.productForm.value);
        this._service.addProduct(this.productForm.value).subscribe(response => {
            this.initProductList();
            this.modalRef.hide();
            this.toastr.success('添加成功！', 'Success');
        });
    }

    /**
     * 打开编辑产品模态框
     */
    openEditProduct(template: TemplateRef<any>, row: any) {
        this.initEditProductForm(row);
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
     * 提交编辑产品表单
     */
    submitEditProduct() {
        // 获取类型对象
        this.productForm.value.type = this.selectedTypeOnForm;
        this._service.modifyProduct(this.productForm.value).subscribe(response => {
            this.initProductList();
            this.modalRef.hide();
            this.toastr.success('修改成功！', 'Success');
        });
    }

    /**
     * 打开删除产品模态框
     */
    openDeleteProduct(template: TemplateRef<any>, row: any) {
        this.deletedProduct = row;
        // 打开弹窗
        this.modalRef = this.modalService.show(template);
    }

    /**
     * 删除产品
     */
    deleteProduct(productId: number) {
        this._service.deleteProduct(productId).subscribe(response => {
            this.initProductList();
            this.modalRef.hide();
            if(response.code === 100200){
                this.toastr.success('删除成功！', 'Success');
            }else{
                this.toastr.error(response.msg);
            }
        });
    }

}
