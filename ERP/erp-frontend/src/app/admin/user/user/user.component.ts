import {Component, OnInit, TemplateRef} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {BsModalRef, BsModalService} from 'ngx-bootstrap';
import {ToastrService} from 'ngx-toastr';
import {UserService} from '../../../shared/service/user.service';

@Component({
    selector: 'app-user',
    templateUrl: './user.component.html',
    styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

    userList: Array<any>; // 初始化用户列表
    userRoleList: Array<any>; // 初始化用户角色列表

    searchInput: FormControl = new FormControl();  // 搜索框中输入的值
    roleListOptions: Array<any>; // 用户角色选择项
    selectedRoleId = '0'; // 选中的角色ID

    selectedRoleOnForm: any; // 表单上选中的角色

    totalSize: number;  // 总数
    pageNum = 1;  // 当前页
    limit = 10;  // 每页大小

    userForm: FormGroup;  // 用户表单
    deletedUser: any; // 被删除的用户

    resetPasswordUser: any; // 被重置密码的用户
    newPassword: any; // 重置密码

    modalRef: BsModalRef;  // 模态框
    loading = true;  // 列表加载状态
    valid: boolean; // 校验状态
    ifAdd: boolean; // 新增/修改

    constructor(private _service: UserService, private modalService: BsModalService,
                private fb: FormBuilder, private toastr: ToastrService) {
        // 实时搜索
        this.searchInput.valueChanges
            .debounceTime(500)
            .subscribe(value => {
                this.initUserList();
            });
    }

    ngOnInit() {
        // 初始化用户类型列表
        this._service.queryUserRoleList().subscribe(response => {
            this.roleListOptions = [{'id': '0', 'name': '全部', 'code': 'all'}];
            this.userRoleList = response.data;
            this.roleListOptions = this.roleListOptions.concat(this.userRoleList);
            console.log(this.roleListOptions);
        });
        // 初始化用户列表
        this.initUserList();
    }

    /**
     * 初始化用户列表
     */
    private initUserList() {
        this.loading = true;
        this._service.pagingQueryUserList(this.selectedRoleId, this.searchInput.value, this.pageNum, this.limit).subscribe(response => {
            this.userList = response.data;
            this.totalSize = response.totalRecord;
            console.log(this.userList);
            this.loading = false;
        });
    }

    /**
     * 分类改变事件
     * @param event  当前的分类对象
     */
    onRoleChange(event) {
        this.pageNum = 1;
        this.initUserList();
    }

    /**
     * 表单分类改变事件
     * @param event  当前的分类对象
     */
    onFormRoleChange(event) {
        this.selectedRoleOnForm = this.userRoleList.find(p => p.id === event.currentValue);
        console.log(this.selectedRoleOnForm);
    }

    /**
     * 分页事件
     * @param event
     */
    onPageChange(event) {
        this.pageNum = event.pageNumber;
        this.initUserList();
    }

    /**
     * 初始化添加用户表单
     */
    private initAddUserForm() {
        this.selectedRoleOnForm = null;
        this.userForm = this.fb.group({
            id: [''],
            username: ['', [Validators.required, Validators.minLength(4),
                Validators.maxLength(20), Validators.pattern(/^[A-Za-z0-9]+$/)]],
            password: ['', [Validators.required, Validators.minLength(6),
                Validators.maxLength(20), Validators.pattern(/^[A-Za-z0-9]+$/)]],
            name: ['', [Validators.required, Validators.maxLength(20)]],
            email: ['', [Validators.required, Validators.email, Validators.maxLength(30)]],
            phone: ['', [Validators.required, Validators.pattern(/^[1]([3-9])[0-9]{9}$/)]],
            role: ['', [Validators.required]],
            createTime: [''],
            updateTime: ['']
        });
    }

    /**
     * 初始化编辑用户表单
     */
    private initEditUserForm(row: any) {
        console.log(row);
        this.selectedRoleOnForm = this.userRoleList.find(p => p.name === row.role.name);
        console.log(this.selectedRoleOnForm);
        this.userForm = this.fb.group({
            id: [row.id],
            username: [row.username, [Validators.required, Validators.minLength(4),
                Validators.maxLength(20), Validators.pattern(/^[A-Za-z0-9]+$/)]],
            name: [row.name, [Validators.required]],
            email: [row.email, [Validators.required, Validators.email, Validators.maxLength(30)]],
            phone: [row.phone, [Validators.required, Validators.pattern(/^[1]([3-9])[0-9]{9}$/)]],
            role: [row.role.name, [Validators.required]],
            createTime: [row.createTime],
            updateTime: [row.updateTime]
        });
    }

    /**
     * 打开添加用户模态框
     */
    openAddUser(template: TemplateRef<any>) {
        this.initAddUserForm();
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
     * 提交添加用户表单
     */
    submitAddUser() {
        // 获取类型对象
        console.log(this.userForm.value);
        this.userForm.value.role = this.selectedRoleOnForm;
        console.log(this.userForm.value);
        this._service.addUser(this.userForm.value).subscribe(response => {
            this.initUserList();
            this.modalRef.hide();
            this.toastr.success('添加成功！', 'Success');
        });
    }

    /**
     * 打开编辑用户模态框
     */
    openEditUser(template: TemplateRef<any>, row: any) {
        this.initEditUserForm(row);
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
     * 提交编辑用户表单
     */
    submitEditUser() {
        // 获取类型对象
        this.userForm.value.role = this.selectedRoleOnForm;
        this._service.modifyUser(this.userForm.value).subscribe(response => {
            this.initUserList();
            this.modalRef.hide();
            this.toastr.success('修改成功！', 'Success');
        });
    }

    /**
     * 打开重置密码模态框
     */
    openResetPassword(template: TemplateRef<any>, row: any) {
        this.resetPasswordUser = row;
        this.newPassword = ''
        // 打开弹窗
        this.modalRef = this.modalService.show(template);
    }

    /**
     * 重置密码
     */
    submitResetPassword() {
        this._service.modifyUserPassword(this.resetPasswordUser.username, this.newPassword).subscribe(response => {
            this.initUserList();
            this.modalRef.hide();
            this.toastr.success('重置成功！', 'Success');
        });
    }

    /**
     * 生成新密码
     */
    randomPassword() {
        const member = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
            't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
            'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'];
        this.newPassword = '';
        for (let i = 0; i < 6; i++) {
            let type = Math.floor(Math.random() * 2) % 2 === 0 ? 'char' : 'num';
            if (type === 'char') {
                this.newPassword += member[Math.floor(Math.random() * 52)];
            } else if (type === 'num') {
                this.newPassword += Math.floor(Math.random() * 10);
            }
        }
    }

    /**
     * 打开删除用户模态框
     */
    openDeleteUser(template: TemplateRef<any>, row: any) {
        this.deletedUser = row;
        // 打开弹窗
        this.modalRef = this.modalService.show(template);
    }

    /**
     * 删除用户
     */
    deleteUser(userId: number) {
        this._service.deleteUser(userId).subscribe(response => {
            this.initUserList();
            this.modalRef.hide();
            this.toastr.success('删除成功！', 'Success');
        });
    }

}

