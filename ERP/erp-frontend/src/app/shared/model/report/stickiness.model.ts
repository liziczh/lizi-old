import {CustomerTypeEnum} from './customerType.enum';

export class StickinessModel {

   companyName: string;
   userType: CustomerTypeEnum;
   sale: string;


   constructor(companyName: string, userType: CustomerTypeEnum, sale: string) {
      this.companyName = companyName;
      this.userType = userType;
      this.sale = sale;
   }
}
