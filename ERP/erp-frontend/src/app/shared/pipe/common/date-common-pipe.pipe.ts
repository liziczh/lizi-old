import {Pipe, PipeTransform} from '@angular/core';
import {DatePipe} from '@angular/common';

@Pipe({
   name: 'dateCommonPipe'
})
export class DateCommonPipePipe extends DatePipe implements PipeTransform {

   transform(value: any, format?: string, timezone?: string, locale?: string): any {
      if (value === null) {
         return ;
      }
      let date = value.toString().split('-').join('/');
      return super.transform(new Date(date), format, timezone, locale);
   }

}
