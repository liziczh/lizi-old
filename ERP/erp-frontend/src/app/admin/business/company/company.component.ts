import {Component, OnInit, TemplateRef} from '@angular/core';
import {FormArray, FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Select2OptionData} from 'ng2-select2';
import {ToastrService} from 'ngx-toastr';
import {BsModalRef, BsModalService} from 'ngx-bootstrap';
import {Router} from '@angular/router';
import {BusinessService} from '../../../shared/service/business.service';
import {NodesToStringPipe} from '../../../shared/pipe/nodes-to-string.pipe';
import {DateCommonPipePipe} from '../../../shared/pipe/common/date-common-pipe.pipe';

declare var $: any;

export class CompanyProduct {
   id: number;

   code: string;
   productName: string;

   activateTime: Date;
   expireTime: Date;

   accountNum: number;
   accountUsedNum: number;

   language: number;
   dataPeriod: boolean;

   dataStartTime: Date;
   dataEndTime: Date;

   createTime: Date;
   updateTime: Date;

   modules: Array<any>;
   checkedModuleNodes: Array<any>;
   tags: Array<any>;
   checkedTagNodes: Array<any>;
   ifSendEmail: boolean;
   isTrial: boolean;
   isTrialCopy: boolean;
   dataPeriodCopy: boolean;


   constructor(id: number, code: string, productName: string,
               activateTime: Date, expireTime: Date, accountNum:
                  number, accountUsedNum: number, language: number,
               dataPeriod: number, dataStartTime: Date, dataEndTime: Date,
               createTime: Date, updateTime: Date, modules: Array<any>,
               checkedModuleNodes: Array<any>, tags: Array<any>,
               checkedTagNodes: Array<any>, isTrial: number,
               dataPeriodCopy?: number, isTrialCopy?: number
   ) {
      this.id = id;
      this.code = code;
      this.productName = productName;
      this.activateTime = activateTime;
      this.expireTime = expireTime;
      this.accountNum = accountNum;
      this.accountUsedNum = accountUsedNum;
      this.language = language;
      this.dataPeriod = dataPeriod === 1 ? true : false;
      this.dataStartTime = dataStartTime;
      this.dataEndTime = dataEndTime;
      this.createTime = createTime;
      this.updateTime = updateTime;
      this.modules = modules;
      this.checkedModuleNodes = checkedModuleNodes;
      this.tags = tags;
      this.checkedTagNodes = checkedTagNodes;
      this.ifSendEmail = true;
      this.isTrial = isTrial === 1 ? true : false;
      this.dataPeriodCopy = dataPeriodCopy ? (dataPeriodCopy === 1 ? true : false) : null;
      this.isTrialCopy = isTrialCopy ? (isTrialCopy === 1 ? true : false) : null;
   }
}

export class CompanyEditProduct {
   id: number;

   code: string;
   productName: string;

   activateTime: string;
   expireTime: string;

   accountNum: number;
   accountUsedNum: number;

   language: number;
   dataPeriod: number;

   dataStartTime: string;
   dataEndTime: string;

   createTime: string;
   updateTime: string;

   modules: string;
   tags: string;

   isTrial: number;


   constructor(id: number, code: string, productName: string,
               activateTime: string, expireTime: string, accountNum: number,
               accountUsedNum: number, language: number, dataPeriod: boolean,
               dataStartTime: string, dataEndTime: string, createTime: string,
               updateTime: string, modules: string, tags: string, isTrial: boolean) {
      this.id = id;
      this.code = code;
      this.productName = productName;
      this.activateTime = activateTime;
      this.expireTime = expireTime;
      this.accountNum = accountNum;
      this.accountUsedNum = accountUsedNum;
      this.language = language;
      this.dataPeriod = dataPeriod ? 1 : 0;
      this.dataStartTime = dataStartTime;
      this.dataEndTime = dataEndTime;
      this.createTime = createTime;
      this.updateTime = updateTime;
      this.modules = modules;
      this.tags = tags;
      this.isTrial = isTrial ? 1 : 0;
   }
}

export class SingleModal {
   title: string;
   content: string;
   click: string;
   point: string;
   company: any;
}

const COMPANY_DELETE = 'delete';           // 公司删除
const COMPANY_DISABLED = 'disabled';       // 公司禁用
const COMPANY_UNDISABLED = 'un_disabled';  // 解除禁用
@Component({
   selector: 'app-company',
   templateUrl: './company.component.html',
   styleUrls: ['./company.component.css']
})
export class CompanyComponent implements OnInit {

   loading = true;            // 加载状态

   homeProductValue = '-1';           // 选中的产品value
   homeSaleValue = '-1';              // 选中的销售value
   homeStatusValue = '-1';            // 选中的状态value

   homeProductData = [{value: '-1', text: '全部产品'}];     // 请选择产品
   homeSaleData = [{value: '-1', text: '全部销售'}];        // 请选择销售
   homeStatusData = [{value: '-1', text: '全部客户'}];       // 请选择状态

   inputSearchValue = null;              // 搜索框中输入的值

   /**
    * 公司列表table
    * @type {any[]}
    */
   companyTableData = [];                 // 公司列表
   companyTablePageNum = 1;               // 当前页
   companyTableLimit = 10;                // 每页大小
   companyTableDataTotalSize = 1;         // 总页数
   companyTableDataCheckedAll = false;   // 是否全选

   /**
    * 单用户操作modal
    */
   singleModal: SingleModal;

   /**
    *  修改公司
    * */
   companyForm: FormGroup;                               // 修改公司 公司表单
   companySaleData = [{value: '-1', text: '无'}];        // 请选择公司销售
   companySaleValue: string;                            // 选择的销售人

   modalRef: BsModalRef;                                 // 模态框
   modalCompany: any;                                   // 模态框 公司

   ifAdd = true;                                               // true/false = 新增公司/修改公司
   type = 0;                                                   // 判断栓除、禁用、解禁
   isTrial = false;                                           // 是否试用
   analysisDates: Array<Select2OptionData> = new Array();     // 下拉框日期
   currentDate: Select2OptionData;                            // 当前页面的日期（随选择而变化）
   selectDate: Select2OptionData;                             // 当前选中的日期
   validate: boolean;                                        // 是否经过验证
   /**
    ************************************************************************************
    */
   productTemplateProductList: Array<any>;    // 公司已经购买的产品列表
   productTemplateCompany: any;                 // 公司产品模态框的公司
   productTemplateStartDate: Date;
   productTemplateExpireDate: Date;
   productTemplateChecked = true;
   dateNow = new Date();
   data: Array<CompanyProduct>;
   dataCopy: Array<CompanyProduct>;
   treeCopy: Array<CompanyProduct>;

   dataEdit: Array<CompanyEditProduct>;

   /**
    * 产品修改
    * */
   productEditTemplateCompany: any;             // 公司产品修改模态框
   productEditTemplateActivateTime: null;       // 产品修改激活时间
   productEditTemplateExpireTime: null;         // 产品修改到期时间
   productEditTemplateAccountNum: null;         // 产品用户数
   productEditTemplateIsTrial: boolean;         // 产品是否试用
   productEditTemplateIfSend: boolean;          // 产品修改是否发送邮件
   productEditTemplateValues: number[] = [];    // 修改产品已选中的产品
   productEditTemplateData = [];                 // 已授权的产品
   productEditTemplateSelectedAll = false;      // 产品修改全选

   /**
    *购买产品
    */
   productBuyTemplateValues: number[] = [];   // 选中的产品
   productBuyTemplateData = [];                // 公司已授权产品
   productBuyTemplateSelectedAll = false;     // 产品全选
   productBuyTemplateCompany: any;            // 购买产品的公司名称

   /**
    *批量添加用户
    */
   dataUser = null;
   addUserTemplateValues: string;
   addUserTemplateData = [];
   addUserTemplateCompanyID: string;
   ifSend = true;
   addUserTemplateIsTrial = true;  // 是否试用
   addUserTemplateIsEn = false;     // 是否英文
   inputIds = [true, false];

   showSendEmail(event: any) {
      this.ifSend = event;
   }

   formatText(value: string) {
      if (this.productBuyTemplateValues && this.productBuyTemplateValues.length > 3) {
         return this.productBuyTemplateValues.length + ' items selected';
      }
      return value;
   }

   constructor(private route: Router, private _service: BusinessService,
               private modalService: BsModalService, private fb: FormBuilder,
               private toastr: ToastrService, private datePipe: DateCommonPipePipe,
               private nodesToStringPipe: NodesToStringPipe) {

      // 初始化日期选择框
      this.calculateMonth();

      // 默认设置最近的第一个日期为当前页面的初始化日期
      this.currentDate = this.selectDate = this.analysisDates[0];

   }

   ngOnInit() {
      /**
       * 查询全部产品
       */
      this._service.queryProductList().subscribe(result => {
         for (let product of result.data) {
            this.homeProductData.push({value: product.id, text: product.text});
         }
      });
      /**
       *查询全部销售
       */
      this._service.querySaleList().subscribe(result => {
         for (let sale of result.data) {
            this.homeSaleData.push({value: sale.id, text: sale.text});
            this.companySaleData.push({value: sale.id, text: sale.text});
         }
      });
      /**
       * 全部状态
       * */
      this.homeStatusData.push({value: '0', text: '正式客户'}, {value: '1', text: '试用客户'});
   }

   /**
    * 初始化首页公司列表
    */
   private initHomeCompanyList() {
      this.companyTableDataCheckedAll = false;
      this.loading = true;
      this._service.pagingQueryCompanyList(this.homeProductValue, this.homeSaleValue, this.homeStatusValue, this.inputSearchValue,
         this.companyTablePageNum, this.companyTableLimit).subscribe(result => {
         this.companyTableData = result['data'];
         this.companyTableDataTotalSize = result['totalRecord'];
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
    * 选择销售和产品和状态列表改变时
    * @param event
    */
   onValueChange(event) {
      if (this.companyTablePageNum !== 1) {
         this.companyTablePageNum = 1;
         return;
      } else {
         this.initHomeCompanyList();
      }
   }

   /**
    * 分页 页码按钮
    * @param event
    */
   onPageChange(event) {
      this.companyTablePageNum = event.pageNumber;
      this.initHomeCompanyList();
   }

   /**
    * 全选、全不选 用户列表
    * @param $event
    */
   checkedAllUser($event: any) {
      this.companyTableData.map(company => company.checked = $event);
   }

   /**
    * 打开添加公司弹窗
    * */
   openAddCompany(template) {
      this.ifAdd = true;
      this.companySaleValue = '-1';
      this.companyForm = this.fb.group({
         companyId: null,
         name: ['', Validators.compose([Validators.required, Validators.maxLength(100)])],
         saleId: [-1, Validators.required],
         isTrial: [1, Validators.required],
         startDate: [''],
         description: ['', Validators.compose([Validators.maxLength(255)])],
         loginType: [0, Validators.required],
         companyType: [0, Validators.required],
         emails: this.fb.array([
               this.fb.control('',
                  Validators.compose([Validators.required, Validators.minLength(1), Validators.maxLength(100),
                     Validators.pattern('^([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$')]))],
            Validators.required)
      });
      this.modalRef = this.modalService.show(template);
   }

   /**
    * 打开修改公司弹窗
    * */
   openEditCompany(template, companyID: number) {
      this.ifAdd = false;
      this.companySaleValue = '-1';
      // 查询详细的公司信息
      this._service.queryCompany(companyID).subscribe(
         resp => {
            this.modalCompany = resp.data;
            // 控制销售选择项
            if (this.modalCompany.saleId) {
               this.companySaleValue = this.companySaleData.find(p => p.value === this.modalCompany.saleId.toString()).text;
            }
            let e = [];
            for (let email of this.modalCompany.emails) {
               e.push(this.fb.control(email,
                  Validators.compose([Validators.required, Validators.minLength(1), Validators.maxLength(100),
                     Validators.pattern('^([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$')])));
            }
            this.companyForm = this.fb.group({
               companyId: [this.modalCompany.companyId],
               name: [this.modalCompany.name, Validators.compose([Validators.required, Validators.maxLength(100)])],
               saleId: [this.modalCompany.saleId, Validators.required],
               isTrial: [this.modalCompany.isTrial, Validators.required],
               startDate: [this.modalCompany.startDate],
               description: [this.modalCompany.description, Validators.compose([Validators.maxLength(255)])],
               loginType: [this.modalCompany.loginType, Validators.required],
               companyType: [this.modalCompany.companyType, Validators.required],
               emails: this.fb.array(e, Validators.required)
            });
            this.modalRef = this.modalService.show(template);
         });
   }

   /**
    * 邮件列表
    * @returns {FormArray}
    */
   get emails(): FormArray {
      return this.companyForm.get('emails') as FormArray;
   }

   /**
    *  计算显示的月份
    */
   calculateMonth(): void {
      let now = new Date();
      let date: number;
      do {
         now.setMonth(now.getMonth() - 1);
         let m = now.getMonth() + 1;
         date = now.getFullYear() * 100 + m;
         this.analysisDates.push({
            'id': date.toString(),
            'text': now.getFullYear() + '年' + m + '月'
         });
      } while (date > 201410);
   }

   /**
    * 添加email
    */
   addEmail() {
      let len = this.emails.length;
      if (!this.emails.value[len - 1]) {
         this.toastr.warning('请先输入邮箱后缀！');
         return;
      }
      if (len < 10) {
         this.emails.push(this.fb.control('',
            Validators.compose([Validators.required, Validators.minLength(1), Validators.maxLength(100), Validators.pattern('^([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$')])));
      } else {
         this.toastr.warning('企业邮箱最大关联10个，超过限制！');
      }
   }

   /**
    * 删除email
    * @param {number} id
    */
   removeEmail(id: number) {
      this.emails.removeAt(id);
   }

   /**
    * 删除公司
    * @param {number} companyId
    */
   delCompany() {
      let companyId = this.singleModal.company.id;
      this._service.delCompany(companyId).subscribe(result => {
         if (result.code === 100200) {
            this.toastr.success('删除成功！');
            this.initHomeCompanyList();
            this.modalRef.hide();
         } else {
            this.toastr.success(result.msg);
         }
      });
   }

   /**
    * 禁用 解禁 公司
    * @param {number} companyId  公司id
    * @param {number} type 2 禁用 3 解禁
    */
   disCompany(type: number) {
      let companyId = this.singleModal.company.id;
      this._service.disCompany(companyId, type).subscribe(result => {
         if (result.code === 100200) {
            this.companyTableData.filter(p => p.id === companyId).map(p => p.companyStatus = (p.companyStatus === 0 ? 2 : 0));
            this.toastr.success('成功！');
         } else {
            this.toastr.error(result.msg);
         }
         this.modalRef.hide();
      });
   }

   /**
    * 公司修改提交按钮
    * */
   submitEditCompanyForm() {
      if (this.companyForm.value.saleId === -1) {
         this.toastr.warning('请先选择销售！');
         return;
      }
      if (this.companyForm.value.name.trim() === '') {
         this.toastr.error('必填项不能为空！');
         return;
      }
      this._service.submitEditCompany(this.companyForm.value).subscribe(
         resp => {
            this.initHomeCompanyList();
            this.toastr.success('公司 \'' + this.companyForm.get('name').value + '\' 修改成功');
            this.modalRef.hide();
         }
      );
   }

   /**
    * 公司编辑提交按钮
    * */
   submitAddCompanyForm() {
      if (this.companyForm.value.saleId === -1) {
         this.toastr.warning('请先选择销售！');
         return;
      }
      if (this.companyForm.value.name.trim() === '') {
         this.toastr.error('必填项不能为空！');
         return;
      }
      this._service.submitCreateCompany(this.companyForm.value).subscribe(
         resp => {
            if (resp.code === 100200) {
               this.initHomeCompanyList();
               this.toastr.success('公司' + this.companyForm.get('name').value + '添加成功');
            } else {
               this.toastr.error(resp.msg);
            }
            this.modalRef.hide();
         }
      );
   }

   /**
    * 打开产品模态框
    * @param {TemplateRef<any>} template
    * @param company    点击的公司
    */
   openProductModal(template: TemplateRef<any>, company: any) {
      this.productTemplateStartDate = null;
      this.productTemplateExpireDate = null;
      this.productTemplateChecked = true;
      this.productTemplateCompany = company;
      this._service.queryCompanyProducts(company.id).subscribe(result => {// 获取公司已授权产品
         this.productTemplateProductList = result.data;
         this.productTemplateProductList.map(product => product.checked = true);
         this.openModalTemplate(template);
      });
   }

   /**
    * 打开批量添加用户模态框
    * ifAdd true 添加
    * */
   openAddUsers(template, companyID: string, productNum: number) {
      if (productNum === 0) {
         this.toastr.warning('请先购买产品！');
         return;
      }
      this.ifAdd = true;
      this.ifSend = true;
      this.addUserTemplateCompanyID = companyID;
      this.productEditTemplateData = [];
      this.productEditTemplateValues = [];
      this.productEditTemplateSelectedAll = true;
      this.addUserTemplateValues = null;
      this.addUserTemplateData = [];
      this.dataUser = [];
      this.addUserTemplateIsTrial = true;
      this.addUserTemplateIsEn = false;

      /**
       * 默认添加50条
       */
      for (let i = 0; i < 50; i++) {
         this.dataUser.push({'username': '', 'email': '', 'userType': false});
      }

      // 1.获取公司邮箱后缀
      this._service.getCompanyEmails(companyID).subscribe(result => {
         this.addUserTemplateData = result.data;
         this.addUserTemplateValues = this.addUserTemplateData[0].value;
         // 2.获取公司已授权产品
         this._service.getCompanyProducts(companyID).subscribe(result2 => {
            this.productEditTemplateData = result2.data;
            for (let product of result2.data) {
               this.productEditTemplateValues.push(product.value);
            }
            this.modalRef = this.modalService.show(template);
         });

      });

   }

   /**
    * 打开禁用公司模态框
    * */
   openDisabledModal(template, companyID: number) {
      this.singleModal = new SingleModal();
      this.singleModal.title = '禁用公司';
      this.singleModal.company = this.companyTableData.find(p => p.id === companyID);
      this.singleModal.click = COMPANY_DISABLED;
      this.singleModal.content = `您确定要禁用 <span style="color: red;">${this.singleModal.company.name}</span> 吗？`;
      this.singleModal.point = '此公司下的所有账号都将被禁用！';
      this.modalRef = this.modalService.show(template);
   }

   /**
    * 打开解除禁用公司模态框
    * */
   openUnDisabledModal(template, companyID: number) {
      this.singleModal = new SingleModal();
      this.singleModal.title = '解除禁用';
      this.singleModal.company = this.companyTableData.find(p => p.id === companyID);
      this.singleModal.click = COMPANY_UNDISABLED;
      this.singleModal.content = `您确定要解除 <span style="color: red;">${this.singleModal.company.name}</span> 的禁用状态么吗？`;
      this.singleModal.point = '此公司下的所有账号都将被解除禁用！';
      this.modalRef = this.modalService.show(template);
   }

   /**
    *打开删除公司模态框
    **/
   openDeleteModal(template, companyID: number) {
      this.singleModal = new SingleModal();
      this.singleModal.title = '删除公司';
      this.singleModal.company = this.companyTableData.find(p => p.id === companyID);
      this.singleModal.click = COMPANY_DELETE;
      this.singleModal.content = `您确定要删除 <span style="color: red;">${this.singleModal.company.name}</span> 吗？`;
      this.singleModal.point = '此公司下的所有账号都将被删除！';
      this.modalRef = this.modalService.show(template);
   }

   /**
    * 打开购买产品模态框
    * */
   openProductBuyModal(template: TemplateRef<any>, company: any) {
      this.productEditTemplateActivateTime = null;
      this.productEditTemplateExpireTime = null;
      this.productEditTemplateAccountNum = null;
      this.productEditTemplateIsTrial = null;
      this.productEditTemplateIfSend = true;
      this.data = [];
      this.treeCopy = [];
      this.productBuyTemplateSelectedAll = false;
      this.productBuyTemplateValues = [];
      this.productBuyTemplateData = [];
      this.productBuyTemplateCompany = company;
      this._service.queryCompanyNoneProducts(company.id).subscribe(result => {// 获取公司未购买
         for (let product of result.data) {
            this.productBuyTemplateData.push({value: product.id, text: product.name, modules: product.modules, tags: product.tags});
         }
         this.openBigModalTemplate(template);
      });
   }

   /**
    * 打开 用户编辑产品列表的模态框
    * @param {TemplateRef<any>} template
    * @param company
    */
   openProductEditModal(template: TemplateRef<any>, company: any) {
      this.productEditTemplateCompany = company;
      this.productEditTemplateActivateTime = null;
      this.productEditTemplateExpireTime = null;
      this.productEditTemplateAccountNum = null;
      this.productEditTemplateIsTrial = null;
      this.productEditTemplateIfSend = true;
      this.productEditTemplateData = [];
      this.productEditTemplateValues = [];
      this.data = [];
      this.dataCopy = [];
      this.treeCopy = [];
      this.productEditTemplateSelectedAll = false;
      this._service.queryCompanyProducts(company.id).subscribe(result => {// 获取公司已授权产品
         for (let product of result.data) {
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

            this.productEditTemplateData.push({value: product.id, text: product.productName});
            // this.productEditTemplateValues.push(product.id);
         }
         this.openBigModalTemplate(template);
      });

   }

   /**
    * 打开模态框
    * */
   openModalTemplate(template: TemplateRef<any>) {
      this.modalRef = this.modalService.show(template);
   }

   /**
    * 打开模态框
    * */
   openBigModalTemplate(template: TemplateRef<any>) {
      this.modalRef = this.modalService.show(template, Object.assign({},
         {
            class: 'gray modal-lg max-width-lg',
            ignoreBackdropClick: false,
            keyboard: false
         }));
   }

   /**
    * productTemplate 模态框 全选所有产品
    */
   selectAllProduct(): void {
      this.productTemplateChecked = !this.productTemplateChecked;
      this.productTemplateProductList.map(product => product.checked = this.productTemplateChecked);
   }

   /**
    * 产品列表
    * */
   commitProductTemplate() {
      let ids = '';
      this.productTemplateProductList.filter(product => product.checked).map(p => ids += (p.id + ','));
      if (ids.length < 1) {
         this.toastr.warning('请至少选择一个产品！');
         return;
      }
      if (this.productTemplateStartDate && this.productTemplateExpireDate) {

         if (this.productTemplateStartDate.getTime() >= this.productTemplateExpireDate.getTime()) {
            this.toastr.warning(`产品到期时间必须大于激活时间！`);
            return;
         }
         if (this.productTemplateExpireDate && ids !== '') {
            this._service.commitProductTemplate(this.productTemplateCompany.id, ids,
               this.datePipe.transform(this.productTemplateStartDate, 'yyyy-MM-dd'),
               this.datePipe.transform(this.productTemplateExpireDate, 'yyyy-MM-dd')).subscribe(resp => {
                  this.toastr.success('批量延期成功！');
                  this.modalRef.hide();
                  this.productTemplateProductList = [];
               }
            );
         } else {
            this.toastr.warning('请选择需要延期的产品和时间！');
            return;
         }
      } else {
         this.toastr.warning('请设置激活时间和到期时间！');
         return;
      }


   }

   /**
    * 提交修改产品
    */
   commitEditProductTemplate() {
      this.validate = true;

      if (this.data.length < 1) {
         this.toastr.warning('请至少选择一个产品！');
         this.validate = false;
         return;
      }
      this.dataEdit = [];
      for (let pro of this.data) {
         if (!(pro.activateTime && pro.expireTime)) {
            this.toastr.warning(`请设置 ${pro.productName} 产品激活时间和到期时间！`);
            return;
         }
         if (pro.activateTime.getTime() >= pro.expireTime.getTime()) {
            this.toastr.warning(`${pro.productName} 产品到期时间必须大于激活时间！`);
            return;
         }
         if (pro.dataPeriod) {
            if (!(pro.dataStartTime && pro.dataEndTime)) {
               this.toastr.warning(`请设置 ${pro.productName} 产品数据开始时间和到期时间！`);
               return;
            }
            if (pro.dataStartTime.getTime() >= pro.dataEndTime.getTime()) {
               this.toastr.warning(`${pro.productName} 数据限制结束时间必须大于开始时间！`);
               return;
            }
         }
         if (pro.accountNum < 1) {
            this.toastr.warning(`请设置 ${pro.productName} 开通账号数！`);
            return;
         }
         if (pro.accountNum < pro.accountUsedNum) {
            this.toastr.error(`${pro.productName} 产品开通账号数不能小于已使用账号数！`);
            this.validate = false;
            return;
         }
         this.dataEdit.push(new CompanyEditProduct(
            pro.id,
            pro.code,
            pro.productName,
            this.datePipe.transform(pro.activateTime, 'yyyy-MM-dd HH:mm:ss'),
            this.datePipe.transform(pro.expireTime, 'yyyy-MM-dd HH:mm:ss'),
            pro.accountNum,
            pro.accountUsedNum,
            pro.language,
            pro.dataPeriod,
            this.datePipe.transform(pro.dataStartTime, 'yyyy-MM-dd HH:mm:ss'),
            this.datePipe.transform(pro.dataEndTime, 'yyyy-MM-dd HH:mm:ss'),
            this.datePipe.transform(pro.createTime, 'yyyy-MM-dd HH:mm:ss'),
            this.datePipe.transform(pro.updateTime, 'yyyy-MM-dd HH:mm:ss'),
            this.nodesToStringPipe.transform(pro.checkedModuleNodes),
            this.nodesToStringPipe.transform(pro.checkedTagNodes),
            pro.isTrial
         ));

      }
      if (this.validate) {
         this._service.commitEditProductTemplate(this.productEditTemplateCompany.id, this.dataEdit).subscribe(
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
    * 改变全选按钮
    * @param {number} id
    */
   changeCheckedAll(id: number) {
      this.productTemplateProductList.filter(product => product.id === id).map(p => p.checked = !p.checked);
      let checkedNum = this.productTemplateProductList.filter(product => product.checked).length;
      if (checkedNum !== this.productTemplateProductList.length) {
         this.productTemplateChecked = false;
      } else {
         this.productTemplateChecked = true;
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
    * 产品开通账户数
    * */
   batchEditAccountNum($event: any) {
      this.data.map(p => {
         p.accountNum = $event.currentValue;
      });
   }

   /**
    * 是否试用
    * */
   batchEditIsTrial($event: any) {
      this.data.map(p => {
         p.isTrial = $event;
         p.dataPeriod = $event;
         if (p.dataPeriod) {
            this._service.findProductDataTime(p.id).subscribe(resp => {
               if (resp.data !== null) {
                  p.dataStartTime = new Date(resp.data.startTime);
                  p.dataEndTime = new Date(resp.data.endTime);
               } else {
                  p.dataStartTime = null;
                  p.dataEndTime = null;
               }
            });
         } else {
            p.dataStartTime = null;
            p.dataEndTime = null;
        }
     });
   }

   /**
    * 产品是否试用参数
    * 试用默认有数据时间限制，但也可以不限制
    * */
   EditIsTrial($event: any, p) {
      p.dataPeriod = $event;
      if ($event) {
         if (this.data.find(id => p.id).dataStartTime !== null) {
            p.dataStartTime = this.data.find(id => p.id).dataStartTime;
            p.dataEndTime = this.data.find(id => p.id).dataEndTime;
         }else {
            this._service.findProductDataTime(p.id).subscribe(resp => {
               if (resp.data) {
                  p.dataStartTime = new Date(resp.data.startTime);
                  p.dataEndTime = new Date(resp.data.endTime);
               } else {
                  p.dataStartTime = null;
                  p.dataEndTime = null;
               }
            });
         }
      } else {
         p.dataStartTime = null;
         p.dataEndTime = null;
      }
   }

   /**
    * 修改选中的产品
    * */
   changeProductEditDataValue($event: any) {
      let currentValue: string [] = $event.currentValue;
      let previousValue: string [] = $event.previousValue;
      // 新增
      if (currentValue.length > previousValue.length) {
         const changed: string [] = currentValue.filter(x => !previousValue.includes(x));
         for (let chang of changed) {
            let product_index = this.productEditTemplateData.findIndex(p => p.value === chang);
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
               product.isTrial ? 1 : 0
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
               product.isTrial ? 1 : 0
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

   /**
    * 选择购买的产品
    * */
   changeProductDataValue($event: any) {
      let currentValue: string [] = $event.currentValue;
      let previousValue: string [] = $event.previousValue;
      // 新增
      if (currentValue.length > previousValue.length) {
         const changed: string [] = currentValue.filter(x => !previousValue.includes(x));
         for (let chang of changed) {
            let product = this.productBuyTemplateData.find(p => p.value === chang);
            this.data.push(new CompanyProduct(
               product.value,
               null,
               product.text,
               null,
               null,
               0,
               0,
               0,
               0,
               null,
               null,
               null,
               null,
               null,
               [{id: 0}],
               null,
               [{id: 0}],
               0
            ));
            this.treeCopy.push(new CompanyProduct(
               product.value,
               null,
               product.text,
               null,
               null,
               0,
               0,
               0,
               0,
               null,
               null,
               null,
               null,
               product.modules,
               [{id: 0}],
               product.tags,
               [{id: 0}],
               0
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

   batchEditProducts($event: any) {
      this.productEditTemplateValues = [];
      if ($event) {
         this.productEditTemplateData.map(
            p => {
               this.productEditTemplateValues.push(p.value);
            }
         );
      } else {
         this.productEditTemplateValues = [];
      }
   }

   /**
    * 批量购买产品
    * */
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
    * 购买产品
    * */
   commitBuyProductTemplate() {
      if (this.data.length < 1) {
         this.toastr.warning('请至少购买一个产品！');
         return;
      }
      this.dataEdit = [];
      for (let pro of this.data) {
         if (!(pro.activateTime && pro.expireTime)) {
            this.toastr.warning(`请设置 ${pro.productName} 产品激活时间和到期时间！`);
            return;
         }
         if (pro.activateTime.getTime() >= pro.expireTime.getTime()) {
            this.toastr.warning(`${pro.productName} 产品到期时间必须大于激活时间！`);
            return;
         }
         if (pro.dataPeriod) {
            if (!(pro.dataStartTime && pro.dataEndTime)) {
               this.toastr.warning(`请设置 ${pro.productName} 产品数据开始时间和到期时间！`);
               return;
            }
            if (pro.dataStartTime.getTime() >= pro.dataEndTime.getTime()) {
               this.toastr.warning(`${pro.productName} 数据限制结束时间必须大于开始时间！`);
               return;
            }
         }
         if (pro.accountNum < 1) {
            this.toastr.warning(`请设置 ${pro.productName} 开通账号数！`);
            return;
         }

         this.dataEdit.push(new CompanyEditProduct(
            pro.id,
            pro.code,
            pro.productName,
            this.datePipe.transform(pro.activateTime, 'yyyy-MM-dd HH:mm:ss'),
            this.datePipe.transform(pro.expireTime, 'yyyy-MM-dd HH:mm:ss'),
            pro.accountNum,
            pro.accountUsedNum,
            pro.language,
            pro.dataPeriod,
            this.datePipe.transform(pro.dataStartTime, 'yyyy-MM-dd HH:mm:ss'),
            this.datePipe.transform(pro.dataEndTime, 'yyyy-MM-dd HH:mm:ss'),
            this.datePipe.transform(pro.createTime, 'yyyy-MM-dd HH:mm:ss'),
            this.datePipe.transform(pro.updateTime, 'yyyy-MM-dd HH:mm:ss'),
            this.nodesToStringPipe.transform(pro.checkedModuleNodes),
            this.nodesToStringPipe.transform(pro.checkedTagNodes),
            pro.isTrial
         ));

      }

      this._service.commitBuyProductTemplate(this.productBuyTemplateCompany.id, this.dataEdit).subscribe(
         resp => {
            this.toastr.success('产品购买成功！');
            this.modalRef.hide();
            this.data = [];
            this.dataEdit = [];
            this.initHomeCompanyList();
         }
      );
   }

   /**
    * 批量添加用户
    * */
   commitAddUserTemplate() {
      let users = this.dataUser.filter(p => p.email !== '');
      if (users.length < 1) {
         this.toastr.warning('请添加至少一位用户！');
         return;
      }
      if (this.productEditTemplateValues.length < 1) {
         this.toastr.warning('请选择至少一个产品授权！');
         return;
      }
      const str = /^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]$/;
      for (let user of users) {
         if (user.userName !== undefined && user.userName !== '') {
            if (user.userName.length > 30 || user.userName.trim() === '') {
               this.toastr.warning('用户名格式不合法' + user.userName);
               return;
            }
         }
         if (!str.test(user.email)) {
            this.toastr.warning('邮箱格式不合法!!!' + user.email);
            return;
         }
      }
      this._service.commitAddUserTemplate(this.addUserTemplateCompanyID, this.addUserTemplateValues,
         this.productEditTemplateValues, users, this.addUserTemplateIsTrial, this.addUserTemplateIsEn, this.ifSend)
         .subscribe(
            resp => {
               this.toastr.success('批量添加成功！');
               this.initHomeCompanyList();
               this.modalRef.hide();
            }
         );
   }

   /**
    * 操作提交
    * @param {string} click
    */
   singleSubmitCompany(click: string) {
      switch (click) {
         case COMPANY_DELETE:
            this.delCompany();
            break;
         case COMPANY_DISABLED:
            this.disCompany(2);
            break;
         case COMPANY_UNDISABLED:
            this.disCompany(3);
            break;
      }
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
