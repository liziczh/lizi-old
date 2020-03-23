export class Wx {
   appid = 'wx475393b030ffc70c';
   scope = 'snsapi_login';
   redirect_uri: string;
   state: string;
   style: string; // 二维码样式, 提供"black"、"white"可选
   href: string;

   constructor(redirect_uri: string, state: string, style: string, href: string) {
      this.redirect_uri = encodeURIComponent(redirect_uri);
      this.state = state;
      this.style = style;
      this.href = href;
   }

}
