export class Response {

   code: number;
   message: any;

   constructor(code: number, message: any) {
      this.code = code;
      this.message = message;
   }
}
