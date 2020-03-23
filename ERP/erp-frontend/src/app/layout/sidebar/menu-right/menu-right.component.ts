import {Component, OnInit} from '@angular/core';

declare var $: any;
declare var jQuery: any;

@Component({
  selector: 'app-menu-right',
  templateUrl: './menu-right.component.html'
})
export class MenuRightComponent implements OnInit {

  constructor() { }

  ngOnInit() {
    $(function () {

      // scripts for "menu-right" module

      // toggle right menu
      $('.cat__menu-right__action--menu-toggle').on('click', function () {
        $('body').toggleClass('cat__menu-right--visible');
      });

      // custom scroll init
      if (!(/Mobi/.test(navigator.userAgent)) && jQuery().jScrollPane) {
        $('.cat__menu-right').each(function () {
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

      // options scripts
      $('.cat__menu-right--example-option').each(function () {

        const inputs = $(this).find('input'), buttons = $(this).find('.btn');

        // detect current options and set active buttons
        let found = false;
        inputs.each(function () {

          if ($('body').hasClass($(this).val())) {
            $(this).parent().trigger('click');
            found = true;
          }

        });
        if (!found) {
          $(this).find('input[value=""]').parent().trigger('click');
          $('.cat__menu-right .jspPane').css({ top: 0 });
        }

        // change options on click
        $(this).find('.btn').on('click', function () {
          let removeClasses = '';
          const addClass = $(this).find('input').val();

          buttons.removeClass('active');
          $(this).addClass('active');

          inputs.each(function () {
            removeClasses += $(this).val() + ' ';
          });

          $('body').removeClass(removeClasses).addClass(addClass);

          if ($(this).find('input').attr('name') === 'options-colorful' && $(this).find('input').val() === 'cat__menu-left--colorful') {
            $('body').trigger('removeColorfulClasses');
            $('body').trigger('setColorfulClasses');
          }

          if ($(this).find('input').attr('name') === 'options-colorful' && $(this).find('input').val() === '') {
            $('body').trigger('removeColorfulClasses');
          }
        });

      });


    });
  }

}
