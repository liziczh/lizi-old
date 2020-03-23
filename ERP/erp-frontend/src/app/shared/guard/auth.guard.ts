/**
 * @author QM.JM
 * @createTime 2017/11/20
 */

import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from '@angular/router';
import {StorageUtil} from '../utils/storage.util';

@Injectable()
export class AuthGuard implements CanActivate {
    public user: any; // 当前用户

    constructor(private router: Router, private storageUtil: StorageUtil) {
    }

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        this.user = this.storageUtil.get('currentUser');
        console.log(this.user);
        // logged in so return true
        if (this.user) {
            // this.router.navigate(['/admin/dashboard']);
            return true;
        } else {
            // this.router.navigate(['/login']);
            return false;
        }
    }

}
