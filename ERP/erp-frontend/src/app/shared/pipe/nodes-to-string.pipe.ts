import {Pipe, PipeTransform} from '@angular/core';

@Pipe({
   name: 'nodesToStringPipe'
})
export class NodesToStringPipe implements PipeTransform {
   transform(checkedNodes: Array<any>): any {
      /**
       * 树节点存储规则（新）：
       * * 全选："-1"；如果checkedNodes[0] == 0，则存为-1；
       * * 全不选：null；
       * * 部分选：将选中节点ID序列存入数据库
       */
      let nodeIds = null;
      if (checkedNodes === null) {
         nodeIds = null;
      } else {
         if (checkedNodes.length === 0) {
            nodeIds = null;
         } else if (checkedNodes[0] === '-1') {
            nodeIds = '-1';
         } else if (checkedNodes[0].id === 0) {
            nodeIds = '-1';
         } else {
            let nodeIdArr = [];
            for (let node of checkedNodes) {
               nodeIdArr.push(node.id);
            }
            nodeIds = nodeIdArr.join(',');
         }
      }
      return nodeIds;
   }
}
