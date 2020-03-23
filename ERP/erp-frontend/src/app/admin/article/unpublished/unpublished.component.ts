import {Component, OnInit, TemplateRef} from '@angular/core';
import {Select2OptionData} from 'ng2-select2';
import {BsModalRef, BsModalService} from 'ngx-bootstrap';
import {ArticleService} from '../../../shared/service/article.service';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {ArrayToString} from '../../../shared/pipe/common/array-to-String';
import {ToastrService} from 'ngx-toastr';


declare var $: any;
declare var jQuery: any;

@Component({
   selector: 'app-unpublished',
   templateUrl: './unpublished.component.html',
   styleUrls: ['./unpublished.component.css']
})
export class UnpublishedComponent implements OnInit {

   categoryListOption: Array<Select2OptionData>;          // 行业分类列表

   searchInput: FormControl = new FormControl();        // 搜索框中输入的值
   selectedCategoryText = '';       // 当前选中的分类文本
   selectedCategoryId = '0';      // 当前选中的分类id

   articleList: Array<any>;       // 报告列表
   articleListPageNum = 1;        // 当前页
   articleListTotalSize: number;
   limit = 10;       // 每页大小
   loading = true;   // 加载状态
   categoriesLoading = true;  // 分类加载状态

   modalRef: BsModalRef;                              // 模态框
   modalArticle: any;                                 // 当前模态框打开的报告
   modalCategories: string;                           // 当前选中的行业分类

   articleForm: FormGroup;                                // 模态框报告表单
   articleFormCategories: Array<any> = new Array();       // 模态框表单的报告分类

   uploadArticlePdfUrl = '/api/admin/article/article-pdf';                       // 上传pdf报告地址
   uploadArticleJpgUrl = '/api/admin/article/article-jpg';                       // 上传jpg报告地址
   uploadArticleAcceptJpg = 'image/jpg,image/jpeg,image/JPG,image/JPEG';         // 上传报告jpg 接受类型
   uploadArticleAcceptPdf = 'application/pdf';                                   // 上传报告pdf 接受类型

   valid: boolean;      // 是否点击按钮保存 （之后校验）
   message = '报告参数校验失败！';  // 表单校验提示信息
   ifAdd: boolean;      // true/false = 新增报告/修改报告
   ifCanDownloadPdf: boolean;  // 是否可以下载PDF（编辑报告可下载PDF，上传PDF未提交表单时暂不可下载）
   ifCanPublish: boolean;      // 是否有发布报告的权限

   constructor(private modalService: BsModalService, private _service: ArticleService, private fb: FormBuilder,
               private arrayToStringPipe: ArrayToString, private toastr: ToastrService) {
      // 实时搜索  不区分大小写
      this.searchInput.valueChanges
         .debounceTime(500)
         .subscribe(value => {
            // console.log("searchInput: " + value);
            this.initArticleList();
         });
   }

   ngOnInit() {
      // 初始化报告分类
      this.categoriesLoading = true;
      this.categoryListOption = [{'id': '0', 'text': '全部分类'}];
      this._service.queryCategories().subscribe(result => {
         this.categoryListOption = this.categoryListOption.concat(result.data);
         this.articleFormCategories = JSON.parse(JSON.stringify(result.data));
         this.categoriesLoading = false;
      });
      // 初始化button权限
      this._service.initButtonAuthority(72).subscribe(result => {
         const list = result.data;
         if (list) {
            for (let i = 0; i < list.length; i++) {
               if (list[i].code === 'published-article-button') {
                  this.ifCanPublish = true;
                  return;
               }
            }
         }
      });
      // 初始化报告列表
      this.initArticleList();
   }

   /**
    * 初始化报告列表
    */
   private initArticleList() {
      this.articleList = new Array();
      this.loading = true;
      this._service.pagingQueryUnPublishedArticles(this.selectedCategoryId, this.searchInput.value,
         this.articleListPageNum, this.limit).subscribe(result => {
         this.articleList = result.data;
         this.articleListTotalSize = result.totalRecord;
         this.loading = false;
      });
   }

   /**
    * 分类改变事件
    * @param $event  当前的分类对象
    */
   onCategoryChange(event) {
      this.selectedCategoryText = this.categoryListOption.find(p => p.id === event.currentValue).text;
      this.articleListPageNum = 1;
      this.initArticleList();
   }

   /**
    * 状态/版本 下拉选项改变事件
    * @param event
    */
   onValueChange(event) {
      this.articleListPageNum = 1;
      this.initArticleList();
   }

   /**
    * 分页事件
    * @param event
    */
   onPageChange(event) {
      this.articleListPageNum = event.pageNumber;
      this.initArticleList();
   }

   /**
    * 打开新建报告模态框
    * @param {TemplateRef<any>} template
    */
   openCreateArticle(template: TemplateRef<any>) {
      this.createForm();
      this.valid = false;
      this.ifAdd = true;
      this.articleFormCategories.map(article => article.checked = false);
      this.modalRef = this.modalService.show(template, Object.assign({},
         {
            class: 'gray modal-lg',
            ignoreBackdropClick: true,
            keyboard: false
         }));
      $('.dropify').dropify({
         messages: {
            'default': 'Drag and drop a image here or click',
            'replace': 'Drag and drop or click to replace',
            'remove': 'Remove',
            'error': 'Ooops, something wrong happended.'
         }
      });
   }

   /**
    * 打开编辑报告模态框
    * @param {TemplateRef<any>} template
    * @param {string} id : 报告id
    */
   openEditArticle(template: TemplateRef<any>, id: string) {
      this.ifAdd = false;
      this.valid = false;
      this.initForm(id);
      this.modalRef = this.modalService.show(template, Object.assign({},
         {
            class: 'gray modal-lg',
            ignoreBackdropClick: true, // 忽略背景点击
            keyboard: false
         }));
      $('.dropify').dropify({
         messages: {
            'default': 'Drag and drop a image here or click',
            'replace': 'Drag and drop or click to replace',
            'remove': 'Remove',
            'error': 'Ooops, something wrong happended.'
         }
      });

   }

   onCheckedChange(event, category) {
      category.checked = event;
   }

   /**
    * 打开删除报告模态框
    * @param {TemplateRef<any>} template
    */
   openDeleteArticle(template: TemplateRef<any>) {
      this.modalRef.hide();
      this.modalRef = this.modalService.show(template);
   }

   /**
    * 删除报告
    */
   deleteArticle() {
      this._service.deleteArticle(this.articleForm.get('id').value).subscribe(
         result => {
            // 删除成功
            this.initArticleList();
            this.modalRef.hide();
            this.toastr.success('报告删除成功！', 'Success');
         }
      );
   }

   // --------------------------------------------------------------------

   /**
    *    创建一个新的报告表单（用于新建报告）
    */
   createForm() {
      this.articleForm = this.fb.group({
         id: [''],
         title: ['', Validators.compose([Validators.required, Validators.maxLength(100)])],
         version: [0, [Validators.required]],
         bigImgUrl: ['', [Validators.required]],
         source: ['QuestMobile移动大数据研究院'],
         keywords: ['', Validators.compose([Validators.required, Validators.maxLength(50)])],
         categories: [''],
         introduction: ['', Validators.compose([Validators.required, Validators.maxLength(400)])],
         contentImgUrl: ['', [Validators.required]],
         contentImgNum: [0, [Validators.required]],
         contentPdfUrl: ['', [Validators.required]]
      });
   }

   /**
    *    通过文报告id，生成一个报告表单（用于修改报告）
    * @param id   报告id
    */
   initForm(id) {
      let temp = this.articleList.find(article => article.id === id);
      this.modalArticle = JSON.parse(JSON.stringify(temp));
      // 初始化分类（判断是否选中）
      let initCategories = [];
      this.articleFormCategories.forEach(category => {
         if (temp.categories.filter(p => p.id.toString() === category.id.toString()).length > 0) {
            category.checked = true;
            initCategories.push(category.id.toString());
         } else {
            category.checked = false;
         }
      });
      this.ifCanDownloadPdf = true;  // 可下载PDF
      this.articleForm = this.fb.group({
         id: [this.modalArticle.id],
         title: [this.modalArticle.title, Validators.compose([Validators.required, Validators.maxLength(100)])],
         version: [this.modalArticle.version, [Validators.required]],
         bigImgUrl: [this.modalArticle.bigImgUrl, [Validators.required]],
         source: ['QuestMobile移动大数据研究院'],
         keywords: [this.arrayToStringPipe.transform(this.modalArticle.keywords, 'keywordZh', ','),
            Validators.compose([Validators.required, Validators.maxLength(50)])],
         categories: [initCategories],
         introduction: [this.modalArticle.introduction, Validators.compose([Validators.required, Validators.maxLength(400)])],
         contentImgUrl: [this.modalArticle.contentImgUrl, [Validators.required]],
         contentImgNum: [this.modalArticle.contentImgNum, [Validators.required]],
         contentPdfUrl: [this.modalArticle.contentPdfUrl, [Validators.required]]
      });
   }

   /**
    * 提交表单的参数校验提醒
    */
   validate() {
      this.valid = true;
      // console.log(this.articleForm.value);
      if (this.articleForm.value.title === '') {
         this.message = '请输入标题！';
         this.valid = false;
         return;
      }
      if (this.articleForm.value.title.length > 50) {
         this.message = '您输入的标题超出50字限制！';
         this.valid = false;
         return;
      }
      if (this.articleForm.value.bigImgUrl === '') {
         this.message = '请先上传封面图！';
         this.valid = false;
         return;
      }
      if (this.articleForm.value.keywords === '') {
         this.message = '请输入关键词！';
         this.valid = false;
         return;
      }
      if (this.articleForm.value.keywords.length > 50) {
         this.message = '您输入的关键词超出50字限制！';
         this.valid = false;
         return;
      }
      // 行业分类验证
      if (this.articleForm.value.version === 0 && this.modalCategories === '') {
         this.message = '请至少选择一个行业分类！';
         this.valid = false;
         return;
      }
      if (this.articleForm.value.introduction === '') {
         this.message = '请输入导语！';
         this.valid = false;
         return;
      }
      if (this.articleForm.value.introduction.length > 400) {
         this.message = '您输入的导语超出400字限制！';
         this.valid = false;
         return;
      }
      if (this.articleForm.value.contentImgUrl === '') {
         this.message = '请先上传报告（JPEG/JPG版）！';
         this.valid = false;
         return;
      }
      if (this.articleForm.value.contentPdfUrl === '') {
         this.message = '请先上传报告（PDF版）！';
         this.valid = false;
         return;
      }

   }

   /**
    * 报告保存草稿
    */
   submit() {
      // 所勾选的分类
      this.modalCategories = '';
      this.articleFormCategories.filter(article => article.checked).forEach(article => this.modalCategories += article.id + ',');
      // 参数验证
      this.validate();
      if (this.valid && this.articleForm.valid) {
         this.articleForm.patchValue({categories: this.modalCategories});
         // console.log("校验成功");
         this._service.createArticle(this.articleForm.value).subscribe(
            result => {
               // 新增文章成功
               this.initArticleList();
               this.modalRef.hide();
               this.toastr.success('草稿保存成功！', 'Success');
            }
         );
      } else {
         // console.log("校验失败");
         this.toastr.error(this.message, 'Error');
      }
   }

   /**
    * 修改报告
    */
   update() {
      // 所勾选的分类
      this.modalCategories = '';
      this.articleFormCategories.filter(article => article.checked).forEach(article => this.modalCategories += article.id + ',');
      // 参数验证
      this.validate();
      if (this.valid && this.articleForm.valid) {
         this.articleForm.patchValue({categories: this.modalCategories});
         // console.log("校验成功");
         this._service.updateArticle(this.articleForm.value).subscribe(result => {
               // 修改文章成功
               this.initArticleList();
               this.modalRef.hide();
               this.toastr.success('报告修改成功！', 'Success');
            }
         );
      } else {
         // console.log("校验失败");
         this.toastr.error(this.message, 'Error');
      }
   }

   /**
    * 发布草稿
    */
   publish() {
      // 所勾选的分类
      this.modalCategories = '';
      this.articleFormCategories.filter(article => article.checked).forEach(article => this.modalCategories += article.id + ',');
      // 参数验证
      this.validate();
      if (this.valid && this.articleForm.valid) {
         this.articleForm.patchValue({categories: this.modalCategories});
         // console.log("校验成功");
         this._service.publishArticle(this.articleForm.value).subscribe(
            result => {
               // 文章发布成功
               this.initArticleList();
               this.modalRef.hide();
               this.toastr.success('报告发布成功！', 'Success');
            }
         );
      } else {
         // console.log("校验失败");
         this.toastr.error(this.message, 'Error');
      }
   }

   uploadPdf(result) {
      this.articleForm.patchValue({contentPdfUrl: result.data});
      this.toastr.success('pdf报告上传成功', 'Success');
   }

   /**
    * 上传封面图
    * @param $event
    */
   uploadBigImgUrl($event) {
      let formData: FormData = new FormData();
      formData.append('file', $event.files[0]);
      this._service.uploadBigImg(formData).subscribe(result => {
         this.articleForm.patchValue({bigImgUrl: result.data});
         this.toastr.success('封面图上传成功', 'Success');
      });
   }

   uploadJpg(result) {
      this.articleForm.patchValue({contentImgUrl: result.data.path});
      /** 文件夹地址 **/
      this.articleForm.patchValue({contentImgNum: result.data.total});
      /** 文件夹文件数量 **/
      this.toastr.success('jpg报告上传成功', 'Success');
   }

   preview() {
      this.toastr.info('功能开发中');
   }
   /**
    * 下载文件
    * @param path 文件路径
    * @param title 文件名
    */
   download(path: string, title: string) {
      this._service.download(path, title);
   }

}
