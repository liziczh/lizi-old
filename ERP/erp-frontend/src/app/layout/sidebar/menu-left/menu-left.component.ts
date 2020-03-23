import {AfterViewInit, Component, OnInit} from '@angular/core';
import {AuthenticationService} from '../../../shared/service/authentication.service';
import {Router} from '@angular/router';
import {UserService} from '../../../shared/service/user.service';
import {StorageUtil} from '../../../shared/utils/storage.util';

declare var $: any;
declare var jQuery: any;

@Component({
    selector: 'app-menu-left',
    templateUrl: './menu-left-vertical.component.html'
})
export class MenuLeftComponent implements OnInit, AfterViewInit {

    user: any; // 用户角色权限

    constructor(private storageUtil: StorageUtil, private authenticationService: AuthenticationService, private router: Router) {
        setTimeout(() => {
            this.initScroll();
            this.colorfulMenu();
            this.submenu();
        }, 0);
    }

    ngOnInit() {
        this.user = this.storageUtil.get('currentUser');
        console.log(this.user);
    }

    ngAfterViewInit(): void {

    }


    // custom scroll init
    initScroll() {
        if ($('body').hasClass('cat__config--vertical')) {
            if (!(/Mobi/.test(navigator.userAgent)) && jQuery().jScrollPane) {
                $('.cat__menu-left__inner').each(function () {
                    $(this).jScrollPane({
                        contentWidth: '0px',
                        autoReinitialise: true,
                        autoReinitialiseDelay: 100
                    });
                    const api = $(this).data('jsp');
                    let throttleTimeout: any;
                    $(window).bind('resize', function () {
                        if (!throttleTimeout) {
                            throttleTimeout = setTimeout(function () {
                                api.reinitialise();
                                throttleTimeout = null;
                            }, 50);
                        }
                    });
                });
            }
        }
    }

    // begin: toggle menu
    menuVisible() {
        if ($('body').width() < 768) {
            $('body').toggleClass('cat__menu-left--visible--mobile');
        } else {
            $('body').toggleClass('cat__menu-left--visible');
        }
    }

    // begin: colorful menu
    colorfulMenu() {
        if ($('body').hasClass('cat__menu-left--colorful')) {
            this.setColorfulClasses();
        }

        $('body').on('setColorfulClasses', function () {
            this.setColorfulClasses();
        });

        $('body').on('removeColorfulClasses', function () {
            this.removeColorfulClasses();
        });
    }

    setColorfulClasses() {
        const clzArray = MenuVariable.clzArray;
        $('.cat__menu-left__list--root > .cat__menu-left__item').each(function () {
            const randomClass = clzArray[Math.floor(Math.random() * clzArray.length)];
            $(this).addClass(randomClass);
        });
    }

    removeColorfulClasses() {
        $('.cat__menu-left__list--root > .cat__menu-left__item').removeClass(MenuVariable.COLORFUL_CLASSES);
    }


    submenu() {

        // add backdrop
        $('.cat__menu-left').after('<div class="cat__menu-left__backdrop cat__menu-left__action--backdrop-toggle"><!-- --></div>');

        // submenu
        $('.cat__menu-left__submenu > a').on('click', function () {

            if ($('body').hasClass('cat__config--vertical') || $('body').width() < 768) {

                const parent = $(this).parent(), opened = $('.cat__menu-left__submenu--toggled');

                if (!parent.hasClass('cat__menu-left__submenu--toggled') && !parent.parent().closest('.cat__menu-left__submenu').length) {
                    opened.removeClass('cat__menu-left__submenu--toggled').find('> .cat__menu-left__list').slideUp(200);
                }

                parent.toggleClass('cat__menu-left__submenu--toggled');
                parent.find('> .cat__menu-left__list').slideToggle(200);

            }

        });

        // remove submenu toggle class when viewport back to full view
        $(window).on('resize', function () {
            if ($('body').hasClass('cat__config--horizontal') || $('body').width() > 768) {
                $('.cat__menu-left__submenu--toggled')
                    .removeClass('cat__menu-left__submenu--toggled')
                    .find('> .cat__menu-left__list')
                    .attr('style', '');
            }
        });

        $('.cat__menu-left__action--backdrop-toggle').on('click', function () {
            $('body').removeClass('cat__menu-left--visible--mobile');
        });

    }

    logout() {
        this.authenticationService.logout();
        this.router.navigate(['/login']);
    }
}

export const MenuVariable = Object.freeze({

    COLORFUL_CLASSES: 'cat__menu-left--colorful--primary ' +
        'cat__menu-left--colorful--secondary cat__menu-left--colorful--primary ' +
        'cat__menu-left--colorful--default cat__menu-left--colorful--info ' +
        'cat__menu-left--colorful--success cat__menu-left--colorful--warning ' +
        'cat__menu-left--colorful--danger cat__menu-left--colorful--yellow',

    get clzArray() {
        return this.COLORFUL_CLASSES.split(' ');
    }

});
