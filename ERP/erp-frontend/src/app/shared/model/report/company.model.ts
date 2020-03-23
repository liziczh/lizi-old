export class CompanyModel {

   productName: string;
   companyCode: string;
   companyName: string;
   dateTime: string;
   sale: string;
   userCount: number;


   constructor(productName: string, companyCode: string, companyName: string, dateTime: string, sale: string, userCount: number) {
      this.productName = productName;
      this.companyCode = companyCode;
      this.companyName = companyName;
      this.dateTime = dateTime;
      this.sale = sale;
      this.userCount = userCount;
   }
}
