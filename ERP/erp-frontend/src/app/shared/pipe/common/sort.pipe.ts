/**
 * 自定义排序管道组件
 */
import {Pipe, PipeTransform} from '@angular/core';

@Pipe({
   name: 'sort'
})
export class SortPipe implements PipeTransform {

   transform(array: Array<any>, column: string, type: boolean): any {

      if (!column) {
         return array;
      }
      if (!type) {
         return array.sort((a, b) => {
            return b[column] - a[column];
         });
      } else {
         return array.sort((a, b) => {
            return a[column] - b[column];
         });
      }

   }


}
