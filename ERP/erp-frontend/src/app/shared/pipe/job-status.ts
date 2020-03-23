import {Pipe, PipeTransform} from '@angular/core';

@Pipe({
   name: 'jobStatusPipe'
})
export class JobStatusPipe implements PipeTransform {
   transform(value: Array<any>, jobStatus: number, jobModules: string): any {

      if (jobStatus.toString() !== '5') {
         value = value.filter(p => jobStatus === p.jobCommitStatus.toString());
      }
      if (jobModules !== '全部模块') {
         value = value.filter(p => jobModules === p.jobModules.toString());
      }
      return value;
   }

}
