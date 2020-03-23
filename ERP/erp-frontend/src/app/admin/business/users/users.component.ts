import {Component, OnInit, TemplateRef} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ToastrService} from 'ngx-toastr';
import {BsModalRef, BsModalService} from 'ngx-bootstrap';
import {ActivatedRoute} from '@angular/router';
import {CompanyEditProduct, CompanyProduct} from 'app/admin/business/company/company.component';
import {BusinessService} from '../../../shared/service/business.service';
import {NodesToStringPipe} from '../../../shared/pipe/nodes-to-string.pipe';
import {DateCommonPipePipe} from '../../../shared/pipe/common/date-common-pipe.pipe';

declare var $: any;

export class BatchModal {
   title: string;
   content: string;
   click: string;
   userList: Array<any>;
}

export class SingleModal {
   title: string;
   content: string;
   click: string;
   user: any;
}

/**
 * 批量操作
 * @type {string}
 */
const BATCH_RESET_PWD = 'reset_pwd';
const BATCH_DELETE = 'delete';
const BATCH_DEL_WECHAT = 'del_wechat';
const BATCH_DISABLED = 'disabled';
const BATCH_UNDISABLED = 'un_disabled';

/**
 * 单用户操作
 * @type {string}
 */
const SINGLE_DELETE = 'delete';
const SINGLE_DEL_WECHAT = 'del_wechat';
const SINGLE_DISABLED = 'disabled';
const SINGLE_UNDISABLED = 'un_disabled';


@Component({
   selector: 'app-users',
   templateUrl: './users.component.html',
   styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {

   selectedStatus = '-1';            // 当前选中的状态id
   modalRef: BsModalRef;                              // 模态框
   weChatList = [];           // 微信绑定历史记录
   ifAdd = true;              // true/false = 新增用户/修改用户
   ifSend = false;
   hasUser = false;           // true/false = 已选择用户/未选择用户
   loading = true;            // 加载状态
   validate: boolean;        // 是否验证通过

   homeProductData = [{value: '-1', text: '全部产品'}];            // 请选择产品
   homeProductValue = '-1';           // 选中的产品value
   activateTime: Date;
   expireTime: Date;

   /**
    * 懒加载下拉框
    * */

   total = 2;
   pageNumber = 1;
   pageSize = 20;
   rowHeight = 30;
   homeCompanyValue = this.activeRoute.snapshot.queryParams['companyID'];           // 选中的公司value
   homeCompanyValueName = this.activeRoute.snapshot.queryParams['companyName'];     // 选中的公司value
   info: any[] = [];
   sourceCompanyData: any[] = [];                                                   // 公司源（不变的，供搜索）


   inputSearchValue = null;                                                         // 搜索框中输入的值

   ifAdmin: boolean;                                                               // 是否是本公司用户


   /**
    * 用户列表table
    * @type {any[]}
    */
   userTableData = [];                 // 用户列表
   userTablePageNum = 1;               // 当前页
   userTableDataTotalSize = 1;         // 总页数
   userTableLimit = 10;                // 每页大小
   userTableDataCheckedAll = false;   // 是否全选
   /**
    * 修改用户
    */
   user: FormGroup;                              // 修改用户 用户表单
   companyEmails = [];                           // 公司邮箱后缀列表
   companyEmailsValue: string;                  //  选择的邮箱后缀
   passWord: string;                            // 修改密码 新密码
   /**
    * 批量操作modal
    */
   batchModal: BatchModal;
   /**
    * 单用户操作modal
    */
   singleModal: SingleModal;


   /**
    *  用户 授权产品列表 模态框
    * @type {any[]}
    */
   dataTree: any[] = [];
   productEditTemplateActivateTime: null;
   productEditTemplateExpireTime: null;
   productEditTemplateAccountNum: null;
   productEditTemplateIsTrial: boolean;
   productEditTemplateIfSend: boolean;
   data: Array<CompanyProduct>;
   dataCopy: Array<CompanyProduct>;
   dataEdit: Array<CompanyEditProduct>;
   treeCopy: Array<CompanyProduct>;

   productBuyTemplateValues: number[] = [];
   productBuyTemplateData = [];
   productBuyTemplateSelectedAll = false;
   dateNow = new Date();

   /**
    * 用户编辑授权产品
    * */
   authorizeEditUserId: string;
   authorizeEditcompanyId: string;
   userCompanyId: string;



   constructor(private activeRoute: ActivatedRoute, private _service: BusinessService, private datePipe: DateCommonPipePipe,
               private nodesToStringPipe: NodesToStringPipe, private modalService: BsModalService,
               private fb: FormBuilder, private toastr: ToastrService) {
      window.scroll(0, 0);
      if (this.homeCompanyValue && this.homeCompanyValueName) {
         this.info.push({value: this.homeCompanyValue, text: this.homeCompanyValueName});
         this.sourceCompanyData.push({value: this.homeCompanyValue, text: this.homeCompanyValueName});
      }
   }

   ngOnInit() {
      /**
       * 查询全部产品
       */
      this._service.queryUserProductList().subscribe(result => {
         for (let product of result.data) {
            this.homeProductData.push({value: product.id, text: product.text});
         }
      });

      /**
       * 查询全部公司
       */
      this._service.queryCompanyList().subscribe(result => {
         if ((this.homeCompanyValue && this.homeCompanyValueName) || result.data.length > 1) {
            this.info = [{value: -1, text: '全部公司'}];
            this.sourceCompanyData = [{value: -1, text: '全部公司'}];
         } else {
            if (result.data.length === 1) {
               this.ifAdmin = true;
            } else {
               this.ifAdmin = false;
            }
            this.homeCompanyValue = result.data[0].id;
            this.homeCompanyValueName = result.data[0].text;
         }
         for (let product of result.data) {
            this.sourceCompanyData.push({value: product.id, text: product.text});
            this.info.push({value: product.id, text: product.text});
         }
         this.total = this.info.length;
      });
   }

   /**
    * 初始化首页用户列表
    */
   private initHomeUserList() {
      this.userTableDataCheckedAll = false;
      this.loading = true;
      this._service.pagingQueryUserList(this.homeProductValue, this.homeCompanyValue, this.selectedStatus, this.inputSearchValue,
         this.userTablePageNum, this.userTableLimit).subscribe(result => {
         this.userTableData = result['data'];
         this.userTableDataTotalSize = result['totalRecord'];
         this.loading = false;
      });
   }

   /**
    * 敲回车或者搜索用户
    * @param event
    */
   onSearch(event) {
      this.inputSearchValue = event.value;
      this.onValueChange(event);
   }

   /**
    * 分页 页码按钮
    * @param event
    */
   onPageChange(event) {
      this.userTablePageNum = event.pageNumber;
      this.initHomeUserList();
   }

   /**
    * 选择公司和产品列表改变时
    * @param event
    */
   onValueChange(event) {
      if (this.userTablePageNum !== 1) {
         this.userTablePageNum = 1;
         return;
      } else {
         this.initHomeUserList();
      }
   }

   /**
    * 打开编辑用户弹窗
    * @param template   USER_TEMPLATE
    * @param {number} userId  用户id
    */
   openEditUser(template, userId: string) {
      this.companyEmails = [];
      this.companyEmailsValue = '';
      this.productBuyTemplateValues = [];
      this.productBuyTemplateData = [];
      this.ifAdd = false;
      this.ifSend = false;
      // 查询用户详细信息
      this._service.queryUser(userId).subscribe(
         resp => {
            let user = resp.data;
            // 默认选中的邮箱
            this.companyEmailsValue = user.email.substr(user.email.indexOf('@'), user.email.length);
            // 获取公司已授权产品
            for (let product of user.productList) {
               this.productBuyTemplateData.push({
                  value: product.productId,
                  text: product.name,
                  status: product.status,
                  // count: product.count
                  accountNum: product.accountNum,
                  accountUsedNum: product.accountUsedNum
               });
               if (product.used) {
                  this.productBuyTemplateValues.push(product.productId);
               }
            }
            this.user = this.fb.group({
               id: user.id,
               username: [user.username, [Validators.maxLength(30)]],
               nickname: [user.nickname, [Validators.required, Validators.maxLength(30)]],
               company: null,
               email: [user.email.substr(0, user.email.indexOf('@')), [Validators.pattern, Validators.required, Validators.maxLength(30)]],
               emails: [this.companyEmailsValue, Validators.required],
               password: [user.password],
               mobile: [user.mobile, [Validators.pattern]],
               telephone: [user.telephone, [Validators.maxLength(30)]],
               job: [user.job, [Validators.maxLength(30)]],
               department: [user.department, [Validators.maxLength(30)]],
               userType: [user.userType === 1 ? true : false],
               isSendEmail: [user.sendEmail === 1 ? true : false],
               isTrial: [user.isTrial],
               isEn: [user.isEnglish],
               products: ['']
            });
            // 企业邮箱
            for (let email of user.company.emails) {
               this.companyEmails.push({value: email, text: email});
            }
            this.modalRef = this.modalService.show(template);
         }
      );
   }

   /**
    * 打开修改用户密码弹窗
    * @param template   EDIT_USER_PWD
    * @param {number} userId  用户id
    */
   openEditUserPwd(template, userId: number) {
      this.passWord = '';
      this.singleModal = new SingleModal();
      this.singleModal.user = this.userTableData.find(p => p.id === userId);
      this.modalRef = this.modalService.show(template);
   }

   /**
    * 打开添加用户弹窗
    * @param template
    */
   openAddUser(template) {

      if (this.homeCompanyValue === undefined || this.homeCompanyValue === '' || this.homeCompanyValue === -1) {
         let userList = this.userTableData.filter(user => user.checked);
         if (userList.length === 1) {
            this.userCompanyId = userList[0].companyId;
         } else {
            this.toastr.warning('请先选择公司！');
            return;
         }
      } else {
         this.userCompanyId = this.homeCompanyValue;
      }
      this.ifAdd = true;
      this.ifSend = true;
      this.productBuyTemplateValues = [];
      this.productBuyTemplateData = [];
      this.dataCopy = [];
      this.passWord = '';
      /**
       * 获取该公司的邮箱后缀
       */
      this._service.getUserCompanyEmails(this.userCompanyId).subscribe(
         resp => {
            this.companyEmails = resp.data;
            // 获取公司已授权产品
            this._service.queryUserCompanyProducts(this.userCompanyId).subscribe(result => {// 获取公司已授权产品
               for (let product of result.data) {
                  let d = new Date(product.expireTime);
                  let dd = product.expireTime && (d.getTime() < this.dateNow.getTime());
                  // 过滤掉过期的产品
                  if (dd) {
                     continue;
                  }
                  this.productBuyTemplateData.push({
                     value: product.id,
                     text: product.productName,
                     status: dd,
                     accountNum: product.accountNum,
                     accountUsedNum: product.accountUsedNum
                  });
               }
               if (this.productBuyTemplateData.length === 0) {
                  this.toastr.error('该公司暂无可以授权的产品线！');
                  return;
               }

               // 初始化用户表单
               this.user = this.fb.group({
                  id: this.userCompanyId,
                  username: ['', [Validators.maxLength(30)]],
                  nickname: ['', [Validators.required, Validators.maxLength(30)]],
                  company: null,
                  email: ['', [Validators.required, Validators.maxLength(30), Validators.pattern]],
                  emails: [this.companyEmails[0].value, Validators.required],
                  password: ['', [Validators.required, Validators.maxLength(32), Validators.pattern]],
                  mobile: ['', [Validators.pattern]],
                  telephone: ['', [Validators.maxLength(30)]],
                  job: ['', [Validators.maxLength(30)]],
                  department: ['', [Validators.maxLength(30)]],
                  userType: [false],
                  isSendEmail: [true],
                  isTrial: [true],
                  isEn: [false],
                  products: ['']
               });
               this.modalRef = this.modalService.show(template);
            });
         });
   }

   showSendEmail(event: any) {
      this.ifSend = event;
   }

   /**
    * 提交修改用户表单
    * @param value
    */
   submitEditUserForm(value: any) {
      if (this.productBuyTemplateValues.length === 0) {
         this.toastr.error('必须授权产品！');
         return;
      }
      if (value.nickname.trim() === '') {
         this.toastr.error('必填项不能为空！');
         return;
      }
      value.products = this.productBuyTemplateValues.toString();
      value.userType = value.userType ? 1 : 0;
      value.sendEmail = value.sendEmail ? 1 : 0;
      value.email = value.email + value.emails;
      this._service.submitEditUserForm(value).subscribe(
         resp => {
            this.toastr.success('成功');
            this.initHomeUserList();
            this.modalRef.hide();
         });
   }

   /**
    * 提交添加用户表单
    * @param value
    */
   submitAddUserForm(value: any) {
      if (this.productBuyTemplateValues.length === 0) {
         this.toastr.error('必须授权产品！');
         return;
      }
      if (value.nickname.trim() === '') {
         this.toastr.error('必填项不能为空！');
         return;
      }
      value.products = this.productBuyTemplateValues.toString();
      value.userType = value.userType ? 1 : 0;
      value.email = value.email + value.emails;
      this._service.submitAddUserForm(value).subscribe(
         resp => {
            this.toastr.success('成功');
            this.initHomeUserList();
         });
      this.modalRef.hide();
   }

   /**
    * 全选、全不选 用户列表
    * @param $event
    */
   checkedAllUser($event: any) {
      this.userTableData.map(user => user.checked = $event);
   }

   /**
    * 打开批量重置密码弹窗
    * @param template
    */
   openBatchResetPwd(template) {
      this.batchModal = new BatchModal();
      this.batchModal.userList = this.userTableData.filter(user => user.checked);
      if (this.batchModal.userList.length === 0) {
         this.toastr.warning('请至少选择一个用户');
         return;
      }
      this.batchModal.click = BATCH_RESET_PWD;
      this.batchModal.title = '批量重置密码';
      this.batchModal.content = '您确定要重置以上用户的密码么？';
      this.modalRef = this.modalService.show(template);
   }

   /**
    * 打开批量删除 弹窗
    * @param template
    */
   openBatchDelete(template) {
      this.batchModal = new BatchModal();
      this.batchModal.userList = this.userTableData.filter(user => user.checked);
      if (this.batchModal.userList.length === 0) {
         this.toastr.warning('请至少选择一个用户');
         return;
      }
      this.batchModal.click = BATCH_DELETE;
      this.batchModal.title = '批量删除用户';
      this.batchModal.content = '您确定要删除以上用户么？';
      this.modalRef = this.modalService.show(template);
   }

   /**
    * 打开批量解绑微信 弹窗
    * @param template
    */
   openBatchDelWeChat(template) {
      this.batchModal = new BatchModal();
      this.batchModal.userList = this.userTableData.filter(user => user.checked);
      if (this.batchModal.userList.length === 0) {
         this.toastr.warning('请至少选择一个用户');
         return;
      }
      this.batchModal.click = BATCH_DEL_WECHAT;
      this.batchModal.title = '批量解绑微信';
      this.batchModal.content = '您确定要批量解绑以上用户的微信？';
      this.modalRef = this.modalService.show(template);
   }

   /**
    * 打开批量禁用的 弹窗
    * @param template
    */
   openBatchDisabled(template) {
      this.batchModal = new BatchModal();
      this.batchModal.userList = this.userTableData.filter(user => user.checked);
      if (this.batchModal.userList.length === 0) {
         this.toastr.warning('请至少选择一个用户');
         return;
      }
      this.batchModal.click = BATCH_DISABLED;
      this.batchModal.title = '批量禁用';
      this.batchModal.content = '您确定要批量禁用以上用户的账号？';
      this.modalRef = this.modalService.show(template);
   }

   /**
    * 打开批量 解除禁用的弹窗
    * @param template
    */
   openBatchUnDisabled(template) {
      this.batchModal = new BatchModal();
      this.batchModal.userList = this.userTableData.filter(user => user.checked);
      if (this.batchModal.userList.length === 0) {
         this.toastr.warning('请至少选择一个用户');
         return;
      }
      this.batchModal.click = BATCH_UNDISABLED;
      this.batchModal.title = '批量解除禁用';
      this.batchModal.content = '您确定要批量解除以上账号的禁用状态么？';
      this.modalRef = this.modalService.show(template);
   }

   /**
    * 用户批量操作 确定
    * @param {string} click   类型
    */
   batchSubmitUser(click: string) {
      switch (click) {
         case BATCH_RESET_PWD:
            this.batchResetPwd();
            break;
         case BATCH_DELETE:
            this.batchDelete();
            break;
         case BATCH_DEL_WECHAT:
            this.batchDelWeChat();
            break;
         case BATCH_DISABLED:
            this.batchDisabled(2);
            break;
         case BATCH_UNDISABLED:
            this.batchDisabled(3);
            break;
      }
   }

   /**
    * 批量重置密码
    */
   private batchResetPwd() {
      let userIds = [];
      if (this.batchModal.userList.length === 0) {
         this.toastr.warning('请至少选择一个用户');
         return;
      }
      userIds = this.batchModal.userList.map(p => p.id);
      this._service.resetPassWord(userIds).subscribe(result => {
         if (result.code === 100200) {
            this.toastr.success('重置密码成功！');
         } else {
            this.toastr.error(result.msg);
         }
         this.modalRef.hide();
      });
   }

   /**
    * 批量删除用户
    */
   private batchDelete() {
      let userIds = [];
      if (this.batchModal.userList.length === 0) {
         this.toastr.warning('请至少选择一个用户');
         return;
      }
      userIds = this.batchModal.userList.map(p => p.id);
      this._service.delUser(userIds).subscribe(result => {
         if (result.code === 100200) {
            if (result.data) {
               this.toastr.success('除当前登录用户,其他用户已删除');
            } else {
               this.toastr.success('删除成功！');
               this.initHomeUserList();
            }
         }
         this.modalRef.hide();
      });
   }

   /**
    * 批量解绑微信
    */
   private batchDelWeChat() {
      let userIds = [];
      if (this.batchModal.userList.length === 0) {
         this.toastr.warning('请至少选择一个用户');
         return;
      }
      userIds = this.batchModal.userList.map(p => p.id);
      this._service.delWeChat(userIds).subscribe(result => {
         if (result.code === 100200) {
            this.toastr.success('解绑成功！');
         } else {
            this.toastr.error(result.msg);
         }
         this.modalRef.hide();
      });
   }

   /**
    * 批量禁用
    * @param type 2 禁用 3 解除禁用
    */
   private batchDisabled(type) {
      let userIds = [];
      if (this.batchModal.userList.length === 0) {
         this.toastr.warning('请至少选择一个用户');
         return;
      }
      userIds = this.batchModal.userList.map(p => p.id);
      this._service.disUser(userIds, type).subscribe(result => {
         if (result.code === 100200) {
            this.userTableData.filter(p => userIds.includes(p.id)).map(p => p.status = (type === 3 ? 0 : 2));
            this.toastr.success('操作成功！');
         } else {
            this.toastr.error(result.msg);
         }
         this.modalRef.hide();
      });
   }

   /**
    * 生成新密码
    */
   newPassWord() {
      const member = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
         't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
         'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'];
      this.passWord = '';
      for (let i = 0; i < 6; i++) {
         let type = Math.floor(Math.random() * 2) % 2 === 0 ? 'char' : 'num';
         if (type === 'char') {
            this.passWord += member[Math.floor(Math.random() * 52)];
         } else if (type === 'num') {
            this.passWord += Math.floor(Math.random() * 10);
         }
      }
   }

   /**
    * 单用户 提交修改用户密码
    * @param {number} userId
    */
   submitUserPwd(userId: string) {
      if (this.passWord === null || this.passWord === '') {
         this.toastr.warning('密码不能为空！');
         return;
      }
      const passwordReg = /^[\w]+$/;
      const pwd = passwordReg.test(this.passWord);
      if (!pwd) {
         this.toastr.warning('密码格式不合法！');
         return;
      }
      this._service.editPassWord(this.passWord, userId).subscribe(result => {
         if (result.code === 100200) {
            this.toastr.success('修改密码成功！');
         } else {
            this.toastr.error(result.msg);
         }
         this.initHomeUserList();
         this.modalRef.hide();
      });
   }

   /**
    * 单用户操作提交
    * @param {string} click
    */
   singleSubmitUser(click: string) {
      switch (click) {
         case BATCH_DELETE:
            this.singleDelete();
            break;
         case SINGLE_DEL_WECHAT:
            this.singleDelWeChat();
            break;
         case SINGLE_DISABLED:
            this.singleDisabled(2);
            break;
         case SINGLE_UNDISABLED:
            this.singleDisabled(3);
            break;
      }
   }

   /**
    * 打开微信解绑模块
    * @param TEMPLATE
    * @param {number} userId
    */
   openDelWeChatModal(template, userId: number) {
      this.singleModal = new SingleModal();
      this.singleModal.title = '解绑微信';
      this.singleModal.user = this.userTableData.find(p => p.id === userId);
      this.singleModal.click = SINGLE_DEL_WECHAT;
      this.singleModal.content = `您确定要解除 <span  style="color: red;">${this.singleModal.user.username}</span> 用户的微信绑定吗？`;
      this.modalRef = this.modalService.show(template);

   }

   /**
    * 单用户 解绑微信
    */
   private singleDelWeChat() {
      let userId = this.singleModal.user.id;
      this._service.delWeChat(userId).subscribe(result => {
         if (result.code === 100200) {
            this.userTableData.filter(p => p.id === userId).map(p => p.isBind = (p.isBind === 0 ? 1 : 0));
            this.toastr.success('解除绑定成功！');
         } else {
            this.toastr.error(result.msg);
         }
         this.modalRef.hide();
      });
   }

   /**
    * 打开禁用账号模块
    * @param template
    * @param {number} userId
    */
   openDisabledModal(template, userId: number) {
      this.singleModal = new SingleModal();
      this.singleModal.title = '禁用账号';
      this.singleModal.user = this.userTableData.find(p => p.id === userId);
      this.singleModal.click = SINGLE_DISABLED;
      this.singleModal.content = `您确定要禁用 <span style="color: red;">${this.singleModal.user.username}</span> 账号吗？`;
      this.modalRef = this.modalService.show(template);
   }

   /**
    * 单用户禁用
    * @param {number} type
    */
   private singleDisabled(type: number) {
      let userId = this.singleModal.user.id;
      this._service.disUser(userId, type).subscribe(result => {
         if (result.code === 100200) {
            this.userTableData.filter(p => p.id === userId).map(p => p.status = (p.status === 0 ? 2 : 0));
            this.toastr.success('成功');
         } else {
            this.toastr.error(result.msg);
         }
         this.modalRef.hide();
      });
   }

   /**
    * 打开解除禁用弹窗
    * @param template
    * @param {number} userId
    */
   openUnDisabledModal(template, userId: number) {
      this.singleModal = new SingleModal();
      this.singleModal.title = '解除禁用';
      this.singleModal.user = this.userTableData.find(p => p.id === userId);
      this.singleModal.click = SINGLE_UNDISABLED;
      this.singleModal.content = `您确定要解除 <span style="color: red;">${this.singleModal.user.username}</span> 账号的禁用状态吗？`;
      this.modalRef = this.modalService.show(template);
   }

   /**
    * 打开删除用户模块
    * @param template
    * @param {number} userId
    */
   openDeleteModal(template, userId: number) {
      this.singleModal = new SingleModal();
      this.singleModal.title = '删除账号';
      this.singleModal.user = this.userTableData.find(p => p.id === userId);
      this.singleModal.click = SINGLE_DELETE;
      this.singleModal.content = `您确定要删除 <span style="color: red;">${this.singleModal.user.username}</span> 账号吗？`;
      this.modalRef = this.modalService.show(template);
   }

   /**
    * 删除用户
    */
   private singleDelete() {
      this._service.delUser(this.singleModal.user.id).subscribe(result => {
         if (result.code === 100200) {
            if (result.data) {
               this.toastr.warning('您不能删除当前登录账号！');
            } else {
               this.toastr.success('删除成功！');
               this.initHomeUserList();
            }
            this.modalRef.hide();
         } else {
            this.toastr.success(result.msg);
         }
      });
   }

   /**
    * 打开微信历史绑定记录弹窗
    * @param template
    * @param {number} userId
    */
   openWeChatModal(template, userId: number) {
      this._service.getUserWeChat(userId).subscribe(result => {
         this.weChatList = result.data;
         if (this.weChatList.length === 0) {
            this.toastr.error('该用户未曾绑定过微信！');
            return;
         }
         this.modalRef = this.modalService.show(template, Object.assign({},
            {
               class: 'gray modal-lg max-width-lg',
               ignoreBackdropClick: false,
               keyboard: false
            }));
      });
   }

   /**
    * 打开 用户编辑产品列表的模态框
    * @param {TemplateRef<any>} template
    * @param company
    */
   openProductBuyModal(template: TemplateRef<any>) {
      this.hasUser = false;
      if (this.homeCompanyValue === undefined || this.homeCompanyValue === '' || this.homeCompanyValue === -1) {
         let userList = this.userTableData.filter(user => user.checked);
         if (userList.length === 1) {
            this.userCompanyId = userList[0].companyId;
         } else {
            this.toastr.warning('请先选择公司！');
            return;
         }
      } else {
         this.userCompanyId = this.homeCompanyValue;
      }
      this.productEditTemplateActivateTime = null;
      this.productEditTemplateExpireTime = null;
      this.productEditTemplateAccountNum = null;
      this.productEditTemplateIsTrial = null;
      this.productEditTemplateIfSend = true;
      this.data = [];
      this.dataCopy = [];
      this.treeCopy = [];
      this.productBuyTemplateSelectedAll = false;
      this.productBuyTemplateValues = [];
      this.productBuyTemplateData = [];
      this._service.queryUsersCompanyProducts(this.userCompanyId).subscribe(result => {// 获取公司已授权产品


         for (let product of result.data) {
            let d = new Date(product.expireTime);
            let dd = product.expireTime && (d.getTime() < this.dateNow.getTime());
            // 过滤掉过期的产品
            if (dd) {
               continue;
            }
            this.productBuyTemplateData.push({
               value: product.id,
               text: product.productName,
               status: dd,
               count: (product.accountNum - product.accountUsedNum <= 0)
            });
            this.dataCopy.push(new CompanyProduct(
               product.id,
               product.code,
               product.productName,
               product.activateTime ? new Date(product.activateTime) : null,
               product.expireTime ? new Date(product.expireTime) : null,
               product.accountNum,
               product.accountUsedNum,
               product.language,
               product.dataPeriod,
               product.dataStartTime ? new Date(product.dataStartTime) : null,
               product.dataEndTime ? new Date(product.dataEndTime) : null,
               product.createTime,
               product.updateTime,
               product.modules,
               product.modules,
               product.tags,
               product.tags,
               product.isTrial
            ));

         }
         if (this.productBuyTemplateData.length === 0) {
            this.toastr.error('该公司暂无可以授权的产品线！');
            return;
         }

         this.batchModal = new BatchModal();
         this.batchModal.userList = this.userTableData.filter(user => user.checked);
         if (this.batchModal.userList.length === 0) {
            this.hasUser = true;
            this.toastr.warning('注意：不选择用户，默认授权全部用户 !');
         }

         this.modalRef = this.modalService.show(template, Object.assign({},
            {
               class: 'gray modal-lg max-width-lg',
               ignoreBackdropClick: false,
               keyboard: false
            }));
      });
   }

   /**
    * 用户编辑产品列表的模态框 是否发送邮件选项 勾选框
    * @param $event
    */
   batchEditIfSend($event: any) {
      this.data.map(p => {
         p.ifSendEmail = $event;
      });
   }

   /**
    * 用户编辑产品列表的模态框   全选按钮
    * @param $event
    */
   batchBuyProducts($event: any) {
      this.productBuyTemplateValues = [];
      if ($event) {
         this.productBuyTemplateData.map(
            p => {
               this.productBuyTemplateValues.push(p.value);
            }
         );
      } else {
         this.productBuyTemplateValues = [];
      }
   }

   /**
    * 用户编辑产品列表的模态框   产品列表下拉 选择 或者取消选择时触发
    * @param $event
    */
   changeProductDataValue($event: any) {
      let currentValue: number [] = $event.currentValue;
      let previousValue: number [] = $event.previousValue;
      // 新增
      if (currentValue.length > previousValue.length) {
         const changed: number [] = currentValue.filter(x => !previousValue.includes(x));
         for (let chang of changed) {
            let index = this.productBuyTemplateValues.indexOf(chang);
            let pro = this.productBuyTemplateData.find(p => p.value === chang);
            if (pro.status) {
               this.toastr.warning('该产品已经过期，请续费！');
               this.productBuyTemplateValues.splice(index, 1);
               continue;
            }
            if (pro.count) {
               this.toastr.warning('该产品已无可用账号数，请购买账号数！');
               this.productBuyTemplateValues.splice(index, 1);
               continue;
            }


            let product = this.dataCopy.find(p => p.id === chang);
            this.data.push(new CompanyProduct(
               product.id,
               product.code,
               product.productName,
               product.activateTime ? new Date(product.activateTime) : null,
               product.expireTime ? new Date(product.expireTime) : null,
               product.accountNum,
               product.accountUsedNum,
               product.language,
               product.dataPeriod ? 1 : 0,
               product.dataStartTime ? new Date(product.dataStartTime) : null,
               product.dataEndTime ? new Date(product.dataEndTime) : null,
               product.createTime,
               product.updateTime,
               null,
               [{id: 0}],
               null,
               [{id: 0}],
               product.isTrial ? 1 : 0
            ));
            this.treeCopy.push(new CompanyProduct(
               product.id,
               product.code,
               product.productName,
               product.activateTime ? new Date(product.activateTime) : null,
               product.expireTime ? new Date(product.expireTime) : null,
               product.accountNum,
               product.accountUsedNum,
               product.language,
               product.dataPeriod ? 1 : 0,
               product.dataStartTime ? new Date(product.dataStartTime) : null,
               product.dataEndTime ? new Date(product.dataEndTime) : null,
               product.createTime,
               product.updateTime,
               product.modules,
               product.checkedModuleNodes,
               product.tags,
               product.checkedTagNodes,
               product.isTrial ? 1 : 0
            ));
         }

      } else {  // 删除
         const changed: number [] = previousValue.filter(x => !currentValue.includes(x));
         for (let change of changed) {
            this.data.splice(this.data.findIndex(p => p.id === change), 1);
            this.treeCopy.splice(this.data.findIndex(p => p.id === change), 1);
         }
      }

   }

   changeProductUserValue($event: any) {
      let currentValue: number [] = $event.currentValue;
      let previousValue: number [] = $event.previousValue;
      if (currentValue.length > previousValue.length) {
         const changed: number [] = currentValue.filter(x => !previousValue.includes(x));
         for (let change of changed) {
            let pro = this.productBuyTemplateData.find(p => p.value === change);
            let index = this.productBuyTemplateValues.indexOf(pro.value);
            if (pro.status) {
               this.toastr.warning('该产品已经过期，请续费！');
               this.productBuyTemplateValues.splice(index, 1);
               continue;
            }
            if (pro.accountNum - pro.accountUsedNum <= 0) {
               this.toastr.warning('该产品已无可用账号数，请购买账号数！');
               this.productBuyTemplateValues.splice(index, 1);
               continue;
            } else {
               pro.accountUsedNum += 1;
            }
         }
      } else {
         const changed: number [] = previousValue.filter(x => !currentValue.includes(x));
         for (let change of changed) {
            let pro = this.productBuyTemplateData.find(p => p.value === change);
            pro.accountUsedNum -= 1 ;
         }
      }
   }

   /**
    * todo
    * 用户编辑产品列表的模态框   初始化 标签和模块列表
    */
   initTreeData() {
      this.dataTree = [{
         'id': 1,
         'text': 'My Documents',
         'state': 'closed',

         'children': [{
            'id': 11,
            'text': 'Photos',
            'state': 'closed',
            'children': [{
               'id': 111,
               'text': 'Friend'
            }, {
               'id': 112,
               'text': 'Wife'
            }, {
               'id': 113,
               'text': 'Company'
            }]
         }, {
            'id': 12,
            'text': 'Program Files',
            'children': [{
               'id': 121,
               'text': 'Intel'
            }, {
               'id': 122,
               'text': 'Java'
            }, {
               'id': 123,
               'text': 'Microsoft Office'
            }, {
               'id': 124,
               'text': 'Games'
            }]
         }, {
            'id': 13,
            'text': 'index.html'
         }, {
            'id': 14,
            'text': 'about.html'
         }, {
            'id': 15,
            'text': 'welcome.html'
         }]
      }];
   }

   /**
    * 点击是添加数据日期限制
    * @param $event
    * @param p
    * @constructor
    */
   EditIsTrial($event: any, p) {
      p.dataPeriod = $event;
      if ($event) {
         if (this.data.find(id => p.id).dataStartTime !== null) {
            p.dataStartTime = this.data.find(id => p.id).dataStartTime;
            p.dataEndTime = this.data.find(id => p.id).dataEndTime;
         }else {
            this._service.queryProductTime(p.id).subscribe(resp => {
               if (resp.data) {
                  p.dataStartTime = new Date(resp.data.startTime);
                  p.dataEndTime = new Date(resp.data.endTime);
               }
            });
         }
      } else {
         p.dataStartTime = null;
         p.dataEndTime = null;
      }
   }

   /**
    * 共用的判断操作
    * */
   commonAuthorizeTheUserTemplate() {
      this.validate = true;
      this.dataEdit = [];

      for (let i = 0; i < this.data.length; i++) {
         let copy_index = this.dataCopy.findIndex(p => p.id === this.data[i].id);
         if (this.data[i].activateTime > this.data[i].expireTime) {
            this.toastr.error(this.data[i].productName + '产品激活时间必须小于到期时间');
            this.validate = false;
            break;
         }
         if (this.data[i].dataPeriod && (!this.data[i].dataStartTime || !this.data[i].dataEndTime)) {
            this.toastr.error(this.data[i].productName + '产品数据日期限制时间不能为空！');
            this.validate = false;
            break;
         }
         if (this.data[i].dataEndTime < this.data[i].dataStartTime) {
            this.toastr.error(this.data[i].productName + '产品数据日期限制结束时间必须大于开始时间');
            this.validate = false;
            break;
         }
         if (this.data[i].accountNum < this.data[i].accountUsedNum) {
            this.toastr.error(this.data[i].productName + '产品开通账号数不能小于已使用账号数');

         }

         /**
          * 判断权限是否在公司之内
          * dataCopy 公司数据
          * */
         if (this.data[i].expireTime > this.dataCopy[copy_index].expireTime || this.data[i].activateTime < this.dataCopy[copy_index].activateTime) {
            this.toastr.error(this.data[i].productName + '产品的激活时间和到期时间应在公司购买的使用时间范围之内');
            this.validate = false;
            break;
         }
         if (this.data[i].isTrial === false && this.dataCopy[copy_index].isTrial === true) {
            this.toastr.error(this.data[i].productName + '产品是试用产品');
            this.validate = false;
            break;
         }
         if (this.dataCopy[copy_index].dataPeriod === true && this.data[i].dataPeriod === false) {
            this.toastr.error(this.data[i].productName + '产品必须设置时间限制');
            this.validate = false;
            break;
         }

         if (this.dataCopy[copy_index].dataPeriod === true && this.data[i].dataPeriod === true && (this.data[i].dataEndTime > this.dataCopy[copy_index].dataEndTime || this.data[i].dataStartTime < this.dataCopy[copy_index].dataStartTime)) {
            this.toastr.error(this.data[i].productName + '产品数据时间限制超出本公司数据时间限制范围');
            this.validate = false;
            break;
         }
         this.dataEdit.push(new CompanyEditProduct(
            this.data[i].id,
            this.data[i].code,
            this.data[i].productName,
            this.datePipe.transform(this.data[i].activateTime, 'yyyy-MM-dd HH:mm:ss'),
            this.datePipe.transform(this.data[i].expireTime, 'yyyy-MM-dd HH:mm:ss'),
            this.data[i].accountNum, this.data[i].accountUsedNum, this.data[i].language,
            this.data[i].dataPeriod,
            this.datePipe.transform(this.data[i].dataStartTime, 'yyyy-MM-dd HH:mm:ss'),
            this.datePipe.transform(this.data[i].dataEndTime, 'yyyy-MM-dd HH:mm:ss'),
            this.datePipe.transform(this.data[i].createTime, 'yyyy-MM-dd HH:mm:ss'),
            this.datePipe.transform(this.data[i].updateTime, 'yyyy-MM-dd HH:mm:ss'),
            this.nodesToStringPipe.transform(this.data[i].checkedModuleNodes),
            this.nodesToStringPipe.transform(this.data[i].checkedTagNodes),
            this.data[i].isTrial
         ));
      }
   }

   /**
    * 提交 批量用户授权template
    */
   commitAuthorizeTheUserTemplate() {
      if (this.data.length < 1) {
         this.validate = false;
         this.toastr.warning('请至少选择一个产品授权！');
         return;
      }
      let userList = this.userTableData.filter(user => user.checked).map(p => p.id);

      /**
       * 校验参数是否合法
       */
      this.commonAuthorizeTheUserTemplate();
      if (this.validate) {
         this._service.commitAuthorizeTheUserTemplate(this.userCompanyId, userList, this.dataEdit).subscribe(
            resp => {
               if (resp.code === 100200) {
                  this.toastr.success('操作成功');
               } else {
                  this.toastr.error(resp.msg);
               }
               this.modalRef.hide();
               this.dataEdit = [];
            });
      }
   }

   /**
    *提交 用户授权编辑
    *
    * */
   commitAuthorizeTheUserEdit() {
      if (this.data.length < 1) {
         this.validate = false;
         this.toastr.warning('请至少选择一个产品授权！');
         return;
      }
      let userList = [];
      userList.push(this.authorizeEditUserId);
      this.commonAuthorizeTheUserTemplate();

      if (this.validate) {
         this._service.commitAuthorizeTheUserTemplate(this.authorizeEditcompanyId, userList, this.dataEdit).subscribe(
            resp => {
               if (resp.code === 100200) {
                  this.toastr.success('操作成功');
               } else {
                  this.toastr.error(resp.msg);
               }
               this.modalRef.hide();
               this.dataEdit = [];
            });
      }
   }

   /**
    * 点击公司名字时弹出公司购买的产品列表
    * @param template
    * @param {number} companyId
    */
   openDialogCompanyTemplate(template, companyId: string) {
      this.productBuyTemplateData = [];
      this._service.queryUserCompanyProducts(companyId).subscribe(result => {// 获取公司已授权产品

         for (let product of result.data) {
            let d = new Date(product.expireTime);
            let dd = product.expireTime && (d.getTime() < this.dateNow.getTime());
            // 过滤掉过期的产品
            if (dd) {
               continue;
            }
            this.productBuyTemplateData.push({
               value: product.id,
               text: product.productName,
               status: product.isTrial,
               expireTime: product.expireTime
            });
         }
         if (this.productBuyTemplateData.length === 0) {
            this.toastr.info('暂无有效产品');
            return;
         }
         this.modalRef = this.modalService.show(template, Object.assign({},
            {
               class: `gray modal-sm max-width-sm`
            }));
      });
   }

   /**
    * 点击用户名字时弹出用户已经授权的产品列表
    * @param template
    * @param {number} userId
    */
   openDialogUserTemplate(template, userId: string, companyId: string) {
      this.productBuyTemplateData = [];
      this.data = [];
      this.dataCopy = [];
      this.treeCopy = [];
      this.authorizeEditUserId = userId;
      this.authorizeEditcompanyId = companyId;
      this.productBuyTemplateData = [];
      this.productBuyTemplateSelectedAll = false;
      this.productEditTemplateIsTrial = false;
      this.productBuyTemplateSelectedAll = false;
      this.productBuyTemplateValues = [];
      this.productBuyTemplateData = [];
      this.productEditTemplateActivateTime = null;
      this.productEditTemplateExpireTime = null;
      /**
       * 获取用户已授权产品
       */
      this._service.queryUserProducts(userId).subscribe(result => {

         for (let product of result.data) {
            let d = new Date(product.expireTime);
            let dd = product.expireTime && (d.getTime() < this.dateNow.getTime());
            // 过滤掉过期的产品
            if (dd) {
               continue;
            }
            this.productBuyTemplateData.push({
               value: product.id,
               text: product.productName,
               status: product.isTrial,
               expireTime: product.expireTime
            });

            this.dataCopy.push(new CompanyProduct(
               product.id,
               product.code,
               product.productName,
               product.activateTime ? new Date(product.activateTime) : null,
               product.expireTime ? new Date(product.expireTime) : null,
               product.accountNum,
               product.accountUsedNum,
               product.language,
               product.dataPeriod,
               product.dataStartTime ? new Date(product.dataStartTime) : null,
               product.dataEndTime ? new Date(product.dataEndTime) : null,
               product.createTime,
               product.updateTime,
               product.modules,
               product.modules,
               product.tags,
               product.tags,
               product.isTrial,
               product.dataPeriodCopy,
               product.isTrialCopy
            ));

         }
         if (this.productBuyTemplateData.length === 0) {
            this.toastr.info('暂无有效产品');
            return;
         }
         this.modalRef = this.modalService.show(template, Object.assign({},
            {
               class: 'gray modal-lg max-width-lg',
               ignoreBackdropClick: false,
               keyboard: false
            }));
      });
   }

   /**
    * 用户购买的产品修改 ，选择框点击时触发
    * */
   changeUserProductDataValue($event: any) {
      let currentValue: string [] = $event.currentValue;
      let previousValue: string [] = $event.previousValue;
      // 新增
      if (currentValue.length > previousValue.length) {
         const changed: string [] = currentValue.filter(x => !previousValue.includes(x));
         for (let chang of changed) {
            let product_index = this.productBuyTemplateData.findIndex(p => p.value === chang);
            let product = this.dataCopy[product_index];
            this.data.push(new CompanyProduct(
               product.id,
               product.code,
               product.productName,
               product.activateTime,
               product.expireTime,
               product.accountNum,
               product.accountUsedNum,
               product.language,
               product.dataPeriod ? 1 : 0,
               product.dataStartTime,
               product.dataEndTime,
               product.createTime,
               product.updateTime,
               null,
               [{id: 0}],
               null,
               [{id: 0}],
               product.isTrial ? 1 : 0,
               product.dataPeriodCopy ? 1 : 0,
               product.isTrialCopy ? 1 : 0
            ));
            this.treeCopy.push(new CompanyProduct(
               product.id,
               product.code,
               product.productName,
               product.activateTime,
               product.expireTime,
               product.accountNum,
               product.accountUsedNum,
               product.language,
               product.dataPeriod ? 1 : 0,
               product.dataStartTime,
               product.dataEndTime,
               product.createTime,
               product.updateTime,
               product.modules,
               product.checkedModuleNodes,
               product.tags,
               product.checkedTagNodes,
               product.isTrial ? 1 : 0,
               product.dataPeriodCopy ? 1 : 0,
               product.isTrialCopy ? 1 : 0
            ));
         }

      } else {  // 删除
         const changed: string [] = previousValue.filter(x => !currentValue.includes(x));
         for (let change of changed) {
            this.data.splice(this.data.findIndex(p => p.id.toString() === change), 1);
            this.treeCopy.splice(this.data.findIndex(p => p.id.toString() === change), 1);
         }
      }
   }

   batchEditUserProducts($event: any) {
      this.productBuyTemplateValues = [];
      if ($event) {
         this.productBuyTemplateData.map(
            p => {
               this.productBuyTemplateValues.push(p.value);
            }
         );
      } else {
         this.productBuyTemplateValues = [];
      }
   }

   /**
    * 批量修改 产品参数
    * @param $event
    */
   batchEditActivateTime($event: any) {
      this.data.map(p => {
         p.activateTime = $event.currentValue;
      });
   }

   /**
    * 产品到期时间
    * */
   batchEditExpireTime($event: any) {
      this.data.map(p => {
         p.expireTime = $event.currentValue;
      });
   }

   /**
    * 打开批量解除授权按钮
    * @param template
    */
   openDelCompanyToUser(template) {
      if (this.homeCompanyValue === undefined || this.homeCompanyValue === '' || this.homeCompanyValue === -1) {
         let userList = this.userTableData.filter(user => user.checked);
         if (userList.length === 1) {
            this.userCompanyId = userList[0].companyId;
         } else {
            this.toastr.warning('请先选择公司！');
            return;
         }
      } else {
         this.userCompanyId = this.homeCompanyValue;
      }
      this.batchModal = new BatchModal();
      this.batchModal.userList = this.userTableData.filter(user => user.checked);
      if (this.batchModal.userList.length === 0) {
         this.toastr.warning('注意：不选择用户，默认授权全部用户 !');
      }

      this.productBuyTemplateData = [];
      this._service.queryUserCompanyProducts(this.userCompanyId).subscribe(result => {// 获取公司已授权产品

         for (let product of result.data) {
            let d = new Date(product.expireTime);
            let dd = product.expireTime && (d.getTime() < this.dateNow.getTime());
            // 过滤掉过期的产品
            if (dd) {
               continue;
            }
            this.productBuyTemplateData.push({
               value: product.id,
               text: product.productName,
               status: product.isTrial,
               expireTime: product.expireTime,
               checked: false
            });
         }
         if (this.productBuyTemplateData.length === 0) {
            this.toastr.info('暂无有效产品');
            return;
         }
         this.modalRef = this.modalService.show(template, Object.assign({},
            {
               class: `gray modal-sm max-width-sm`
            }));
      });
   }

   /**
    * 提交批量解除授权
    */
   commitUnAuthorizeTheUserTemplate() {

      let userList = this.userTableData.filter(user => user.checked).map(p => p.id);
      let productIds = this.productBuyTemplateData.filter(product => product.checked).map(p => p.value);
      if (productIds.length < 1) {
         this.toastr.warning('请至少选择一个产品！');
         return;
      }
      this._service.batchDelUserAuthorize(userList, this.userCompanyId, productIds).subscribe(
         resp => {
            this.toastr.success('成功');
            this.modalRef.hide();
         }
      );
   }
   showTree(i: number) {
      if (this.data[i].modules === null) {
         this.data[i].modules = this.treeCopy[i].modules;
      }
      if (this.data[i].tags === null) {
         this.data[i].tags = this.treeCopy[i].tags;
      }
   }
}
