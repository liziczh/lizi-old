import {Pipe, PipeTransform} from '@angular/core';

/**
 * 自定义分页管道组件
 */
@Pipe({
   name: 'pagination'
})
export class PaginationPipe implements PipeTransform {

   transform(value: Array<any>, pageNow: number): Array<any> {

      return this.pagination(pageNow, 10, value);
   }

   pagination(pageNo: number, pageSize: number, array: Array<any>): Array<any> {
      let offset = (pageNo - 1) * pageSize;
      return (offset + pageSize >= array.length) ? array.slice(offset, array.length) : array.slice(offset, offset + pageSize);
   }

}
