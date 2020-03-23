/**
 * @author QM.JM
 * @createTime 2017/11/20
 */
import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {AuthenticationService} from '../shared/service/authentication.service';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Wx} from '../shared/model/auth/wx';
import {ToastrService} from 'ngx-toastr';
import {CookieService} from 'ngx-cookie-service';
import {Md5} from 'ts-md5';
import {Buffer} from 'buffer';

declare var $: any;
declare var jQuery: any;

@Component({
   selector: 'app-login',
   templateUrl: './login.component.html',
   styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

   loginForm: FormGroup;
   code: string;
   state: string;
   timestamp: number;
   rememberUrl: string;

   private md5username: string = <string>Md5.hashStr('username');
   private md5password: string = <string>Md5.hashStr('password');

   constructor(private router: Router, private activatedRoute: ActivatedRoute, private fb: FormBuilder,
               private authenticationService: AuthenticationService, private toastr: ToastrService,
               private cookieService: CookieService) {
      // reset login status
      this.authenticationService.logout();
      this.createForm();
      this.rememberUrl = localStorage.getItem('router');
   }

   ngOnInit() {

      // Show/Hide Password
      $('.password').password({
         eyeClass: '',
         eyeOpenClass: 'icmn-eye',
         eyeCloseClass: 'icmn-eye-blocked'
      });

      // Change BG
      $('.random-bg-image').on('click', function () {
         const min = 1, max = 5, next = Math.floor($('.random-bg-image').data('img')) + 1, final = next > max ? min : next;
         $('.random-bg-image').data('img', final);
         $('.cat__pages__login').data('img', final).css('backgroundImage', 'url(assets/images/login/' + final + '.jpg)');
      });


   }

   createForm() {
      const username = this.base64decoder(this.cookieService.get(this.md5username));
      const password = this.base64decoder(this.cookieService.get(this.md5password));
      this.loginForm = this.fb.group({
         username: [username, [Validators.required, Validators.minLength(2)]],
         password: [password, [Validators.required, Validators.minLength(6)]],
         rememberMe: [true, Validators.required]
      });
   }

   submit() {
      if (this.loginForm.valid) {
         let formValue = this.loginForm.value;
         let username = formValue.username, password = formValue.password;
         this.authenticationService.login(username, password)
            .subscribe(result => {
                if (result.code === 100200) {
                  // login successful

                  if (formValue.rememberMe === true) {
                     const expires = new Date(new Date().getTime() + 7 * 24 * 60 * 60 * 1000);
                     this.cookieService.set(this.md5username, this.base64encoder(formValue.username), expires, '/');
                     this.cookieService.set(this.md5password, this.base64encoder(formValue.password), expires, '/');
                  } else {
                     this.cookieService.delete(this.md5username, '/');
                     this.cookieService.delete(this.md5password, '/');
                  }
                  // console.log(this.rememberUrl);
                  this.router.navigate(['/admin/info/supplier']);
                  this.toastr.success('登录成功');
               } else if (result.code === 100400) { // 用户名或者密码错误
                  // login failed
                  this.toastr.error(result.message);
               } else {
                  // console.log(result.message);
                  this.toastr.error(result.message);
               }
            });
      }
   }

   base64encoder(context): any {
      return new Buffer(context).toString('base64');
   }

   base64decoder(Context): any {
      return new Buffer(Context, 'base64').toString();
   }

}
