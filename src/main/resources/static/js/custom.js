$(document).ready(function(){



$(document).on('change', '.selector select', function(e) {
    //console.log(this.options[e.target.selectedIndex].text);
	if((this.options[e.target.selectedIndex].text) < 50){
		$(this).parents(".selector").siblings(".lowe").show();
		$(this).parents(".selector").siblings(".high").hide();
		var amount = $(this).parents(".selector").siblings(".lowe").find('.right').find('.max').html();
		var qty = $(this).val();
		$(this).parents(".selector").siblings('.paypal_form').find('.amount').val(amount);
		$(this).parents(".selector").siblings('.paypal_form').find('.quantity').val(qty);
	}
	else{
		$(this).parents(".selector").siblings(".lowe").hide();
		$(this).parents(".selector").siblings(".high").show();	

		var amount = $(this).parents(".selector").siblings(".high").find('.right').find('.all-max').html();
		var qty = $(this).val();
		$(this).parents(".selector").siblings('.paypal_form').find('.amount').val(amount);
		$(this).parents(".selector").siblings('.paypal_form').find('.quantity').val(qty);
	}
});

$(function(){
  // bind change event to select
  $('.lang select').on('change', function () {
   var url = $(this).val(); // get selected value
   if (url) { // require a URL
    window.location = url; // redirect
   }
   return false;
  });
});

$(function() {
	$('.accordion .content').hide();
	$('.accordion h2:first').addClass('active').next().slideDown('slow');
	$('.accordion h2').click(function() {
		if($(this).next().is(':hidden')) {
			$('.accordion h2').removeClass('active').next().slideUp('slow');
			$(this).toggleClass('active').next().slideDown('slow');
		}
	});
});
 
$('a[href^="#"]').on('click',function (e) { 
	e.preventDefault();
	var href = $(this).attr('href');
	href = '#' + href.split('#').pop();

	var $target = $(href).offset().top - 97;

	$('html, body').animate({
		'scrollTop': $target
	}, 900, 'swing', function () { 
		window.history.pushState("object or string", "Title", href);
	});
});
 
$('.bxslider2').bxSlider({
  minSlides: 1,
  maxSlides: 3,
  slideWidth:335,
  pager:false,
  moveSlides: 1,
 }); 
 $('#menu').slicknav();
});    

$(window).scroll(function(){
  var header = $('.header'),
   scroll = $(window).scrollTop();

  if (scroll >= 40) header.addClass('fixed_header');
  else header.removeClass('fixed_header');
});

$(window).scroll(function(){
var inner_banner = $('.inner_banner'),
   scroll = $(window).scrollTop();

  if (scroll >= 40) inner_banner.addClass('inner_banner_fixed');
  else inner_banner.removeClass('inner_banner_fixed');
});