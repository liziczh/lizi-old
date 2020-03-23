import {Pipe, PipeTransform} from '@angular/core';

/**
 *  数组的某一列转string
 *  column 列名
 *  marking 连接符
 *
 */
@Pipe({
   name: 'arrayToString'
})
export class ArrayToString implements PipeTransform {
   transform(value: Array<any>, column: string, marking: any): any {
      let result = '';
      value.map(entity => result = result + marking + entity[column]);
      return result.toString().substr(result.indexOf(marking) + 1);
   }

}
