(function($, window){
  $(function () {
    $.get(ruptela.ajax_url, { action: 'show_na_popop' }, function (response) {
      if (response.success) {
        if (response.data.show_popup) {
          $('.na-overlay').fadeTo(600, 1);
        }
      }
    });

    $(function () {
      // append close mobile menu starts
      var closeMenu = $('<div class="menu_trigger"><img src="https://www.ruptela.com/wp-content/themes/ruptela-v2/assets/img/ruptela-close.svg"></div>');
      $('.main-menu').append(closeMenu);
      $('.main-menu').on('click', '.menu_trigger', function () {
        $('html').removeClass('menu-visible');
      });
      // append close mobile menu ends
      $('.region-map-right > ul > li > a').each(function () {
        var link = $(this).attr('href');
        var cSlug = $(this).data('c');
        $('.region-map-parts svg > g#' + cSlug).find('a').attr('xlink:href', link);
      });

      $('.region-map-right > ul > li > a').hover(
        function () {
          var cSlug = $(this).data('c');
          $('.region-map-parts svg > g').removeClass('hover');
          $('.region-map-parts svg > g#' + cSlug).addClass('hover');

          var pos = $('.region-map-parts svg > g#' + cSlug).position();
          var width = $('.region-map-parts svg > g#' + cSlug + ' a')[0].getBoundingClientRect().width;
          var height = $('.region-map-parts svg > g#' + cSlug + ' a')[0].getBoundingClientRect().height;



          if ($(this).hasClass('has-lang')) {
            $('body').append("<div class='tt " + cSlug + "' style='left:" + pos.left + "px;width:" + width + "px;top:" + pos.top + "px;height:" + height + "px;'></div>");
            $(this).parent().find('ul').clone().appendTo("body div." + cSlug + '.tt');

            $('.tt ul li a').each(function () {
              var subtitle = $(this).data('subtitle');
              $(this).find('span').html(subtitle);
            });

          }

          // $('.region-map-parts img').hide();
          // $('.region-map-parts img[data-cimg="'+cSlug+'"').show();
        }, function () {
          // $('.region-map-parts img').hide();

          $('body div.tt').remove();
          $('.region-map-parts svg > g').removeClass('hover');
        }
      );

      $('.region-map-right ul li ul li a').hover(
        function () {
          $(this).addClass('hover');
          var cSlug = $(this).closest('ul').parent().find(' > a').data('c');
          $('.region-map-parts svg > g').removeClass('hover');
          $('.region-map-parts svg > g#' + cSlug).addClass('hover');

          var pos = $('.region-map-parts svg > g#' + cSlug).position();
          var width = $('.region-map-parts svg > g#' + cSlug + ' a')[0].getBoundingClientRect().width;
          var height = $('.region-map-parts svg > g#' + cSlug + ' a')[0].getBoundingClientRect().height;


          if ($(this).closest('ul').parent().find(' > a').hasClass('has-lang')) {
            $('body').append("<div class='tt " + cSlug + "' style='left:" + pos.left + "px;width:" + width + "px;top:" + pos.top + "px;height:" + height + "px;'></div>");
            $(this).closest('ul').parent().find('ul').clone().appendTo("body div." + cSlug + '.tt');
            $('.tt ul li a').each(function () {
              var subtitle = $(this).data('subtitle');
              $(this).find('span').html(subtitle);
            });
          }
        }, function () {
          $(this).removeClass('hover');
          $('body div.tt ul li a').removeClass('hover');
          $('body div.tt').remove();
          $('.region-map-parts svg > g').removeClass('hover');
        }
      );

      $('.region-map-parts svg > g').hover(
        function () {
          $('body div.tt ul li a').removeClass('hover');
          $('body div.tt').remove();
          $('.region-map-parts svg > g').removeClass('hover');
          $(this).addClass('hover');
          var cSlug = $(this).attr('id');
          $('.region-map-parts svg > g').removeClass('hover');
          $('.region-map-parts svg > g#' + cSlug).addClass('hover');

          var pos = $('.region-map-parts svg > g#' + cSlug).position();
          var width = $('.region-map-parts svg > g#' + cSlug + ' a')[0].getBoundingClientRect().width;
          var height = $('.region-map-parts svg > g#' + cSlug + ' a')[0].getBoundingClientRect().height;


          if ($('.region-map-right > ul > li > a[data-c="' + cSlug + '"]').hasClass('has-lang')) {
            $('body').append("<div class='tt " + cSlug + "' style='left:" + pos.left + "px;width:" + width + "px;top:" + pos.top + "px;height:" + height + "px;'></div>");
            $('.region-map-right > ul > li > a[data-c="' + cSlug + '"]').parent().find('ul').clone().appendTo("body div." + cSlug + '.tt');
            $('.tt ul li a').each(function () {
              var subtitle = $(this).data('subtitle');
              $(this).find('span').html(subtitle);
            });
          }
        }, function () {

        }
      );

      $(".home-cto-block, .hero-media").mouseenter(function () {
        $('.region-map-parts svg > g').removeClass('hover');
        $('body div.tt ul li a').removeClass('hover');
        $('body div.tt').remove();
        $('.region-map-parts svg > g').removeClass('hover');
      });



      $('.fbApply').on('click', function () {
        fbq('track', 'Lead');
      });

      // Sticky contacts block
      function stickyContacts() {
        if ($('.vacancy-contacts').length > 0) {
          if ($(window).width() > 960) {
            $('.vacancy-contacts').stick_in_parent({
              offset_top: 90
            });
          } else {
            $('.vacancy-contacts').trigger("sticky_kit:detach");
          }
        }
      }
      stickyContacts();

      $(window).on("load", function () {
        var urlHash = window.location.href.split("#")[1];
        if (typeof urlHash != 'undefined') {
          $('html,body').animate({
            scrollTop: $('#' + urlHash).offset().top - 110
          }, 100);
        }
      });

      var $window = $(window);

      new WOW().init();


      /*Placeholder for old browsers*/
      $('input[placeholder], textarea[placeholder]').placeholder();

      // Custom select
      $('.custom-select').selectmenu();


      resizeSensitive();

      $window.resize(function () {
        resizeSensitive();
        stickyContacts();
        $('.custom-select').selectmenu('destroy');
        $('.custom-select').selectmenu();
      });

      // --- Paralax ---
      // $('.site').parallax("right", 0.3, true);

      // jQuery('ul.main-menu > li > a').on( 'click', function(){
      // 	var parent = jQuery(this).parent('li');
      // 	if( parent.children('ul.sub-menu').length > 0 ) {
      // 		parent.toggleClass( 'show-submenu' );
      // 		return jQuery(window).innerWidth() <= 1024 ? false : true;
      // 	}
      // });
      jQuery('.menu-item-has-children > a').on('click', function () {
        $(this).next().toggle();
        $(this).parent().toggleClass('menu-item-has-children-opened');
        return jQuery(window).innerWidth() <= 1024 ? false : true;
      });

      // --- OWL sarousele ---
      var sliderHero = $('.hero-fader');
      sliderHero.owlCarousel({
        items: 1,
        loop: sliderHero.children().length > 1,
        margin: 0,
        nav: false,
        dots: sliderHero.children().length > 1,
        navSpeed: '800',
        dotsSpeed: '800',
        autoplaySpeed: '800',
        mouseDrag: false,
        fallbackEasing: "easeInOutCubic",
        animateOut: 'fadeOut',
        animateIn: 'fadeIn',
        navRewind: false,
        navigationText: false,
        autoplay: true,
        navText: []
      });

      // --- OWL sarousele [mobile] ---
      var sliderHeroMobile = $('.hero-fader-mobile');
      sliderHeroMobile.owlCarousel({
        items: 1,
        loop: sliderHeroMobile.children().length > 1,
        margin: 0,
        nav: false,
        dots: sliderHeroMobile.children().length > 1,
        navSpeed: '800',
        dotsSpeed: '800',
        autoplaySpeed: '800',
        mouseDrag: false,
        fallbackEasing: "easeInOutCubic",
        animateOut: 'fadeOut',
        animateIn: 'fadeIn',
        navRewind: false,
        navigationText: false,
        autoplay: true,
        navText: [],
        autoHeight: true
      });

      $('.news-slider').owlCarousel({
        loop: true,
        nav: true,
        dots: false,
        navSpeed: '800',
        dotsSpeed: '800',
        autoplaySpeed: '800',
        mouseDrag: false,
        fallbackEasing: "easeInOutCubic",
        animateOut: 'fadeOut',
        animateIn: 'fadeIn',
        navRewind: false,
        navigationText: false,
        autoplay: false,
        navText: ['&larr;', '&rarr;'],
        autoHeight: true,
        responsiveClass: true,
        responsive: {
          0: {
            items: 1
          },
          550: {
            items: 2,
            margin: 20
          },
          1024: {
            items: 3,
            margin: 30
          }
        }
      });



      $(".benefits-acc").accordion();


      var waypoints0 = $('html').waypoint(function (direction) {
        if (direction == "up") {
          $('html').removeClass('pre-sticky');
        } else {
          $('html').addClass('pre-sticky');
        }
      }, {
        offset: '-180px'
      });
      var waypoints = $('html').waypoint(function (direction) {
        if (direction == "up") {
          $('html').removeClass('pre-sticky-ready');
        } else {
          $('html').addClass('pre-sticky-ready');
        }
      }, {
        offset: '-300px'
      });

      var waypoints2 = $('html').waypoint(function (direction) {
        if (direction == "up") {
          $('html').removeClass('sticky');
        } else {
          $('html').addClass('sticky');
        }
      }, {
        offset: '-360px'
      });

      $('.toggle-mobile-menu').on('click', function (e) {
        var $this = $(this);

        $('html').toggleClass('menu-visible');

        e.preventDefault();
      });

      $('.menu.list-menu a, a[href="#RuptelaDay2019"]').on('click', function (e) {
        var $this = $(this);
        $('html, body').animate({
          scrollTop: $($this.attr('href')).offset().top - 120
        }, 600);

        e.preventDefault();
      });


      // --- MatchHeight ---
      //$('.same-height').matchHeight();



      // --- Waypints ---



      // --- Isotope filtering ---
      /*var $container = $('.product-list-cont').isotope({
        itemSelector: '.product-list-item',
        layoutMode: 'fitRows'
      });

        // bind filter button click
        $('.list-filter a').on( 'click', function() {
          var filterValue = $( this ).attr('data-filter');

          $container.isotope({ filter: filterValue });
        });

        // change is-checked class on buttons
        $('.list-filter').each( function( i, buttonGroup ) {
          var $buttonGroup = $( buttonGroup );
          $buttonGroup.on( 'click', 'a', function(e) {
            $buttonGroup.find('.active').removeClass('active');
            $( this ).addClass('active');

            e.preventDefault();
          });
        });*/


      // --- Ion Range sliders ---
      /*$('#slider-area').ionRangeSlider({
          type: "double",
          grid: false,
          min: 35,
          max: 125,
          from: 40,
          to: 125,
          hide_from_to: true
      });*/

      // Custom checkbox
      /*$('.custom-checkbox').icheck();*/





      // ---------------------- Common helpfull scripts -------------------------

      // Slide to target
      /**/


      // Toggle target script
      /*$('.toggle-target').on('click', function(e){
          var $this = $(this),
              $toggleTarget = $($this.data('target')),
              $clickSafeElements =  $toggleTarget.add($this);

           $(document).on('click', function eventHadler(event) {
            if (!$(event.target).closest($clickSafeElements).length) {


              $toggleTarget.removeClass('active');


              if ($this.attr('data-this-class')) {
                  $this.removeClass($this.data('this-class'));
              }

              $(document).off('click', eventHadler);

            }
          });


          $toggleTarget.toggleClass('active');

          if ($this.attr('data-this-class')) {
              $this.toggleClass($this.data('this-class'));
          }

          e.preventDefault();
      });*/






      // Custom functions
      function resizeSensitive() {

      }

      $("[data-fancybox]").fancybox();
      var $container = $('.vacancies-list').isotope({
        itemSelector: '.vacancy-item__wrap',
        layoutMode: 'fitRows'
      });

      $('.list-filter a').on('click', function (e) {
        var filterValue = $(this).attr('data-filter');

        $container.isotope({ filter: filterValue });

        e.preventDefault();
      });

      // change is-checked class on buttons
      $('.list-filter').each(function (i, buttonGroup) {
        var $buttonGroup = $(buttonGroup);
        $buttonGroup.on('click', 'a', function (e) {
          $buttonGroup.find('.active').removeClass('active');
          $(this).addClass('active');

          e.preventDefault();
        });
      });

      $('.newsletter-form').on('submit', function (e) {
        $('.mailchimp-subscribe').removeClass('has-error');
        $('.mailchimp-subscribe .ajax-response').html('').toggle();
        var form = e.target;
        var data = { action: 'ruptela_mailerlite_subscribe', formdata: $(form).serialize() }
        $.post(ruptela.ajax_url, data, function (r) {
          if (r.success) {
            $('.mailchimp-subscribe .ajax-response').html(r.data.msg).show();
            $('.mailchimp-subscribe .form-row input, .mailchimp-subscribe .form-row button, .mailchimp-subscribe .form-row label').hide().remove();
          } else {
            $('.mailchimp-subscribe').addClass('has-error');
            $('.mailchimp-subscribe .ajax-response').html(r.data.msg).show();
          }
        });
        return false;
      });

    });
  });

})(jQuery, window);
