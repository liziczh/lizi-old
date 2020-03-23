import {Pipe, PipeTransform} from '@angular/core';

@Pipe({
   name: 'articleFilter'
})
export class ArticleFilterPipe implements PipeTransform {

   constructor() {}

   transform(value: Array<any>, category: string, status: string, version: string): any {
      if (category !== 'all') {
         value = value.filter(article => article.categories.filter(p => p.id.toString() === category).length > 0);
      }
      if (status !== 'all') {
         value = value.filter(article => article.publishStatus.toString() === status);
      }
      if (version !== 'all') {
         value = value.filter(article => article.version.toString() === version);
      }
      return value;
   }

}
