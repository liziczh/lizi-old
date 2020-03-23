import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {HttpClient} from '@angular/common/http';


/**
 *    自定义多文件上传组件
 */
@Component({
   selector: 'app-files-upload',
   template: `
      <eui-filebutton #multiple_files [accept]="accept" [multiple]="multiple?'multiple':''" [autoUpload]="false"
                      (select)="onFileSelect($event)">选择文件</eui-filebutton>
      <label *ngIf="filename">{{filename}}</label>
      <eui-linkbutton *ngIf="filename" title="上传"
                      (click)="uploadFiles(multiple_files)">上传</eui-linkbutton>
      <label *ngIf="!filename">未选中任何文件</label>
   `
})
export class FilesUploadComponent implements OnInit {

   @Input() multiple: boolean;       // 是否多文件上传
   @Input() accept: string;          // 设置或返回指示文件传输的 MIME 类型的列表（逗号分隔）
   @Input() uploadUrl: string;       // 文件上传的地址
   @Output() childEvent = new EventEmitter<any>();

   filename: string;

   constructor(private http: HttpClient) {

   }

   ngOnInit() {
   }

   onFileSelect(event) {
      console.log(event);
      this.filename = event[0].name;
   }

   uploadFiles(file_input) {
      if (file_input.files.length > 0) {
         let formData: FormData = new FormData();
         if (this.multiple) {
            for (let file of file_input.files) {
               formData.append('file', file);
            }
            this.http.post(this.uploadUrl, formData)
               .subscribe(result => this.childEvent.emit(result));
         } else {
            formData.append('file', file_input.files[0]);
            this.http.post(this.uploadUrl, formData)
               .subscribe(result => this.childEvent.emit(result));
         }
      }

   }


}
