import {Component, OnInit} from '@angular/core';
import {ArticleService} from '../../../shared/service/article.service';
import {ToastrService} from 'ngx-toastr';

declare var $: any;
declare var jQuery: any;

@Component({
   selector: 'app-configuration',
   templateUrl: './configuration.component.html',
   styleUrls: ['./configuration.component.css']
})
export class ConfigurationComponent implements OnInit {

   slideshow_zh: Array<any>;        // 中文版轮播图
   slideshow_en: Array<any>;        // 英文版轮播图

   constructor(private toastr: ToastrService, private _service: ArticleService) {
      this._service.queryAllSlideshowPic().subscribe(
         result => {
            this.slideshow_zh = result.data.filter(pic => pic.version === 0);
            this.slideshow_en = result.data.filter(pic => pic.version === 1);
            result.data.map(
               pic => {
                  setTimeout(() => {

                     $('#' + pic.code).attr('data-default-file', pic.url);
                     $('#' + pic.code).attr('#' + pic.code);
                     $('#' + pic.code).dropify({
                        messages: {
                           'default': 'Drag and drop a image here or click',
                           'replace': 'Drag and drop or click to replace',
                           'remove': 'Remove',
                           'error': 'Ooops, something wrong happended.'
                        }
                     });
                  });
               }
            );

         }
      );
   }

   ngOnInit() {


   }

   uploadSlideshowPic(code: string) {
      let formData: FormData = new FormData();
      formData.append('file', $('#' + code)[0].files[0]);
      formData.append('code', code);
      this._service.uploadSlideshowPic(formData).subscribe(result => {
            this.toastr.success('滚动图上传成功', 'Success');
         }
      );
   }
}
