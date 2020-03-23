import {Component, OnInit, TemplateRef} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {BsModalRef, BsModalService} from 'ngx-bootstrap';
import {InfoService} from '../../../shared/service/info.service';
import {ToastrService} from 'ngx-toastr';
import {StorageUtil} from '../../../shared/utils/storage.util';

export class Store {
    id: number;  // ID
    name: string;  // 门店名称
    code: string;  // 门店CODE
    manager: string; // 门店负责人
    phone: string;  // 门店手机
    address: string; // 门店地址
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
    selector: 'app-store',
    templateUrl: './store.component.html',
    styleUrls: ['./store.component.css']
})
export class StoreComponent implements OnInit {

    storeList: Array<any>; // 初始化门店列表

    searchInput: FormControl = new FormControl();  // 搜索框中输入的值

    totalSize: number;  // 门店总数
    pageNum = 1;  // 当前页
    limit = 10;  // 每页大小

    storeForm: FormGroup;  // 门店表单
    deletedStore: any; // 被删除的门店

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
                this.initStoreList();
            });
    }

    ngOnInit() {
        this.user = this.storageUtil.get('currentUser');
        // 初始化门店列表
        this.initStoreList();
    }

    /**
     * 初始化门店列表
     */
    private initStoreList() {
        this.loading = true;
        this._service.pagingQueryStoreList(this.searchInput.value, this.pageNum, this.limit).subscribe(response => {
            this.storeList = response.data;
            console.log(this.storeList);
            this.loading = false;
        });
    }

    /**
     * 分页事件
     * @param event
     */
    onPageChange(event) {
        this.pageNum = event.pageNumber;
        this.initStoreList();
    }

    /**
     * 初始化添加门店表单
     */
    private initAddStoreForm() {
        this.storeForm = this.fb.group({
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
     * 初始化编辑门店表单
     */
    private initEditStoreForm(row: any) {
        this.storeForm = this.fb.group({
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
     * 打开添加门店模态框
     */
    openAddStore(template: TemplateRef<any>) {
        this.initAddStoreForm();
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
     * 提交添加门店表单
     */
    submitAddStore() {
        console.log(this.storeForm.value);
        this._service.addStore(this.storeForm.value).subscribe(response => {

            this.initStoreList();
            this.modalRef.hide();
            this.toastr.success('添加成功！', 'Success');
        });
    }

    /**
     * 打开编辑门店模态框
     */
    openEditStore(template: TemplateRef<any>, row: any) {
        this.initEditStoreForm(row);
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
     * 提交编辑门店表单
     * @param store
     */
    submitEditStore() {
        this._service.modifyStore(this.storeForm.value).subscribe(response => {
            this.initStoreList();
            this.modalRef.hide();
            this.toastr.success('修改成功！', 'Success');
        });
    }

    /**
     * 打开删除门店模态框
     */
    openDeleteStore(template: TemplateRef<any>, row: any) {
        this.deletedStore = row;
        // 打开弹窗
        this.modalRef = this.modalService.show(template);
    }

    /**
     * 删除门店
     */
    deleteStore(storeId: number) {
        this._service.deleteStore(storeId).subscribe(response => {
            this.initStoreList();
            this.modalRef.hide();
            this.toastr.success('删除成功！', 'Success');
        });
    }

}
