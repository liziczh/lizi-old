/**
 * @author QM.JM
 * @createTime 2017/11/20
 */

import {Component, OnInit} from '@angular/core';
import {PlatformLocation } from '@angular/common';
import {NavigationEnd, Router} from '@angular/router';
import {AuthenticationService} from '../../shared/service/authentication.service';
import {UserService} from '../../shared/service/user.service';

@Component({
   selector: 'app-header',
   templateUrl: './header.component.html',
   styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

   public username: string;
   public url: string;

   constructor(private  authenticationService: AuthenticationService, private  userService: UserService,  private router: Router,  private location: PlatformLocation) {
      this.username = this.authenticationService.username;
   }

   ngOnInit() {
     //  // 初始时获取路由
     //  this.menuList = null;
     //  for (const i in this.location) {
     //     if (i === 'location') {
     //       this.url = this.location[i].pathname;
     //       this.getPath(this.url);
     //       break;
     //     }
     //  }
     // // 路由变化时刷新
     //  this.router.events
     //     .filter((event) => event instanceof NavigationEnd)
     //     .subscribe((event: NavigationEnd) => {
     //        this.getPath(event.url);
     //        window.scroll(0, 0);
     //     });
   }
   logout() {
      this.authenticationService.logout();
      this.router.navigate(['/login']);
   }
}
