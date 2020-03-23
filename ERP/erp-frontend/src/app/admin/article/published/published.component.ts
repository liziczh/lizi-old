import {Component, OnInit, TemplateRef} from '@angular/core';
import {Select2OptionData} from 'ng2-select2';
import {BsModalRef, BsModalService} from 'ngx-bootstrap';
import {ArticleService} from '../../../shared/service/article.service';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {ArrayToString} from '../../../shared/pipe/common/array-to-String';
import {ArticleFilterPipe} from '../../../shared/pipe/article-filter.pipe';
import {ToastrService} from 'ngx-toastr';

declare var $: any;
declare var jQuery: any;

@Component({
   selector: 'app-published',
   templateUrl: './published.component.html',
   styleUrls: ['./published.component.css']
})
export class PublishedComponent implements OnInit {

   categoryListOption: Array<Select2OptionData>;    // 行业分类选择项
   statusListOption: Array<Select2OptionData>;      // 报告状态选择项
   versionListOption: Array<Select2OptionData>;     // 报告类型选择项

   searchInput: FormControl = new FormControl();         // 搜索框中输入的值
   selectedCategoryText = '';       // 当前选中的分类文本
   selectedCategoryId = '0';      // 当前选中的分类id
   selectedStatus = '1';          // 当前选中的状态id
   selectedVersion = '0';         // 当前选中的版本id

   selectOption: Select2Options; // 下拉框配置

   articleList: Array<any>;       // 报告列表
   articleListPageNum = 1;        // 当前页
   articleListTotalSize: number;
   limit = 10;                    // 每页大小
   loading = true;                // 加载状态

   modalRef: BsModalRef;          // 模态框
   modalArticle: any;             // 当前模态框打开的报告
   modalCategories: string;       // 当前选中的行业分类

   articleForm: FormGroup;                                // 模态框报告表单
   articleFormCategories: Array<any> = new Array();       // 模态框表单的报告分类

   uploadArticlePdfUrl = '/api/admin/article/article-pdf';                       // 上传pdf报告地址
   uploadArticleJpgUrl = '/api/admin/article/article-jpg';                       // 上传jpg报告地址
   uploadArticleAcceptJpg = 'image/jpg,image/jpeg,image/JPG,image/JPEG';         // 上传报告jpg 接受类型
   uploadArticleAcceptPdf = 'application/pdf';                                   // 上传报告pdf 接受类型

   valid: boolean;      // 是否点击按钮保存 （之后校验）
   message = '报告参数校验失败！';  // 表单校验提示信息
   ifOnline: boolean;      // true/false = 新增报告/修改报告
   ifCanDownloadPdf: boolean;  // 是否可以下载PDF（编辑报告可下载PDF，上传PDF未提交表单时暂不可下载）
   ifCanUpdate: boolean;      // 是否有修改报告的权限
   ifCanDelete: boolean;      // 是否有删除报告的权限

   constructor(private modalService: BsModalService, private articlePipe: ArticleFilterPipe, private _service: ArticleService,
               private fb: FormBuilder, private arrayToStringPipe: ArrayToString, private toastr: ToastrService) {
      // 去掉下拉选择框的搜索功能
      this.selectOption = {
         minimumResultsForSearch: -1
      };

      // 实时搜索
      this.searchInput.valueChanges
         .debounceTime(500)
         .subscribe(value => {
            this.initArticleList();
         });

   }

   ngOnInit() {
      // 初始化报告分类
      this.categoryListOption = [{'id': '0', 'text': '全部分类'}];
      this._service.queryCategories().subscribe(result => {
         this.categoryListOption = this.categoryListOption.concat(result.data);
         this.articleFormCategories = JSON.parse(JSON.stringify(result.data));
      });
      // 初始化状态 上线/下线
      this.statusListOption = [
         {
            'id': '1',
            'text': '线上报告'
         }, {
            'id': '2',
            'text': '下线报告'
         }];
      // 初始化版本 中文版/英文版
      this.versionListOption = [
         {
            'id': '0',
            'text': '中文报告'
         }, {
            'id': '1',
            'text': '英文报告'
         }];
      // 初始化button权限
      this._service.initButtonAuthority(73).subscribe(result => {
         const list = result.data;
         if (list) {
            for (let i = 0; i < list.length; i++) {
               if (list[i].code === 'offline-article-button') {
                  this.ifCanDelete = true;
               } else if (list[i].code === 'update-article-button') {
                  this.ifCanUpdate = true;
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
      this._service.pagingQueryArticles(this.selectedCategoryId, this.selectedVersion, this.selectedStatus, this.searchInput.value,
         this.articleListPageNum, this.limit).subscribe(result => {
         this.articleList = result.data;
         this.articleListTotalSize = result.totalRecord;
         this.loading = false;
      });
   }

   /**
    * 分类改变事件
    * @param event  当前的分类对象
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
    * 打开编辑报告模态框
    * @param {TemplateRef<any>} template
    * @param {string} id : 报告id
    */
   openEditArticle(template: TemplateRef<any>, id: string) {
      this.valid = false;
      this.initForm(id);
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
    * 通过文报告id，生成一个报告表单（用于修改报告）
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
      if (this.modalArticle.publishStatus === 2) {
         this.ifOnline = false;
      } else if (this.modalArticle.publishStatus === 1) {
         this.ifOnline = true;
      }
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
    * 模态框上分类点击事件
    * @param category
    */
   onCheckedChange(event, category) {
      category.checked = event;
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
         this._service.updateOnlineArticle(this.articleForm.value).subscribe(result => {
            this.initArticleList();
            this.modalRef.hide();
            this.toastr.success('报告修改重新发布成功！', 'Success');
         });
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

   /**
    * 打开模态框
    * @param {TemplateRef<any>} template
    */
   openOfflineArticle(template: TemplateRef<any>) {
      this.modalRef.hide();
      this.modalRef = this.modalService.show(template);
   }

   /**
    * 下线报告
    */
   offlineArticle() {
      this._service.offlineArticle(this.articleForm.get('id').value).subscribe(
         result => {
            // 删除成功
            this.initArticleList();
            this.modalRef.hide();
            this.toastr.success('报告下线成功！', 'Success');
         }
      );
   }

   /**
    * 上传完pdf报告之后的回掉函数
    * @param result
    */
   uploadPdf(result) {
      this.articleForm.patchValue({contentPdfUrl: result.data});
      this.toastr.success('pdf报告上传成功', 'Success');
   }

   /**
    * 上传封面图
    * @param $event  文件
    */
   uploadBigImgUrl($event) {
      let formData: FormData = new FormData();
      formData.append('file', $event.files[0]);
      this._service.uploadBigImg(formData).subscribe(result => {
            this.articleForm.patchValue({bigImgUrl: result.data});
            this.toastr.success('封面图上传成功', 'Success');
         }
      );
   }

   /**
    * 上传jpg成功之后回掉函数
    * @param result  返回结果
    */
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
