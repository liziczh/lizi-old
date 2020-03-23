import {Pipe, PipeTransform} from '@angular/core';
import {DomSanitizer} from '@angular/platform-browser';

/**
 *
 *  自定义字体高亮管道
 *    text: 原始文本
 *    search： 要高亮的文本
 *    红色加粗
 */
@Pipe({
   name: 'highLightPipe'
})
export class HighLightPipe implements PipeTransform {

   constructor(private sanitizer: DomSanitizer) {
   }


   transform(text: string, search: string): any {

      if (!search) {
         return this.sanitizer.bypassSecurityTrustHtml(text);
      }

      // text = encodeURI(text);
      // search = encodeURI(search);
      let regex = new RegExp(search, 'gi');
      let result = text.replace(regex, '<span class="text-danger font-weight-bold">$&</span>');
      // result = decodeURI(result);

      return this.sanitizer.bypassSecurityTrustHtml(result);
   }

}
