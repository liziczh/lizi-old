export class AccountModel {

   code: string;
   productName: string;
   userCount: number;
   accounts: number;

   constructor(code: string, productName: string, userCount: number, accounts: number) {
      this.code = code;
      this.productName = productName;
      this.userCount = userCount;
      this.accounts = accounts;
   }
}
