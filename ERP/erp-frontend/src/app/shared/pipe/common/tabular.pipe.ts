import {Pipe, PipeTransform} from '@angular/core';
import {SortPipe} from './sort.pipe';
import {PaginationPipe} from './pagination.pipe';

/**
 * 自定义整合管道  分页+排序
 */
@Pipe({
   name: 'tabular'
})
export class TabularPipe implements PipeTransform {

   constructor(private sortPipe: SortPipe, private pagePipe: PaginationPipe) {
   }

   transform(array: Array<any>, pageNow: number, column: string, type: boolean): Array<any> {
      // 先排序
      array = this.sortPipe.transform(array, column, type);
      // 再分页
      return this.pagePipe.transform(array, pageNow);
   }

}
