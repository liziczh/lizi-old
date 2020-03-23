
import {StickinessModel} from './stickiness.model';

export class CustomerModel {

   heavyUsers: Array<StickinessModel>;          // 重度用户
   moderateUsers: Array<StickinessModel>;       // 中度用户
   lightUsers: Array<StickinessModel>;          // 轻度用户


   constructor(heavyUsers: Array<StickinessModel>, moderateUsers: Array<StickinessModel>, lightUsers: Array<StickinessModel>) {
      this.heavyUsers = heavyUsers;
      this.moderateUsers = moderateUsers;
      this.lightUsers = lightUsers;
   }
}
