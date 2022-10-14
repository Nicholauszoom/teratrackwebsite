/*****
ON WINDOWS RESIZE
*****/
$( window ).resize(function() {	
	
});

$(window).scroll(function () {

});

/*****
ON DOCUMENT READY
*****/
$(document).ready(function() {	
	
	// To Activate the Tooltip
	$('[data-toggle="tooltip"]').tooltip(); 
	
	// activate search
	$(".search-wrapper span").click(function(){
		$(".search-wrapper").toggleClass("active");	
		
		if($(".search-wrapper").hasClass("active")){
			//$(".search-wrapper input[type='text']").focus();
			
			$("body")[0].addEventListener("click",function(target){
				if(target.target.name && target.target.name === "search_string"){
					// do nothing
				}else{
					$(".search-wrapper").removeClass("active");	
				}
			},true);
		}else{			
		}
	});
	// activate search2
	$(".search-wrapper-2 span").click(function(){	
		$(".search-wrapper-2").toggleClass("active");	
		
		if($(".search-wrapper-2").hasClass("active")){
			//$(".search-wrapper input[type='text']").focus();
			
			$("body")[0].addEventListener("click",function(target){
				if(target.target.name && target.target.name === "search_string"){
					// do nothing
				}else{
					$(".search-wrapper-2").removeClass("active");	
				}
			},true);
		}else{			
		}
	});
	
});



/*********
GENERAL 
*********/

/*** get query string from url ***/
function getUrlVars(url)
{
    var vars = [], 
		hash,
		hashes;
	
	if(validateURL(url)){
		if(url.indexOf('?') !== -1 || url.indexOf('&') !== -1){
			hashes = url.slice(url.indexOf('?') + 1).split('&');
			
			for(var i = 0; i < hashes.length; i++)
			{
				hash = hashes[i].split('=');
				vars.push(hash[0]);
				vars[hash[0]] = hash[1];
			}
		}
		return vars;
	}
	
	return false;
   
}

/*** validate url ***/
function validateURL(str) {
	/*var url_reg = new RegExp('^(https|http?:\\/\\/)?'+ // protocol
	'((([a-z\\d]([a-z\\d-]*[a-z\\d])*)\\.?)+[a-z]{2,}|'+ // domain name
	'((\\d{1,3}\\.){3}\\d{1,3}))'+ // OR ip (v4) address
	'(\\:\\d+)?(\\/[-a-z\\d%_.~+]*)*'+ // port and path
	'(\\?[;&a-z\\d%_.~+=-]*)?'+ // query string
	'(\\#[-a-z\\d_]*)?$','i'); // fragment locator*/

	var url_reg = /(ftp|http|https):\/\/(\w+:{0,1}\w*@)?(\S+)(:[0-9]+)?(\/|\/([\w#!:.?+=&%@!\-\/]))?/;
	return url_reg.test(str); // return true or false
}

/*** validate email ***/
function validateEmail(email) {	// VALIDATE EMAIL
    var emailReg = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
    if (!emailReg.test(email)) {
        return false;
    } else {
        return true;
    }
}

/*** check if element is in viewport ***/
function elementInViewport(el) {
	var top = el.offsetTop;
	var left = el.offsetLeft;
	var width = el.offsetWidth;
	var height = el.offsetHeight;

	while(el.offsetParent) {
		el = el.offsetParent;
		top += el.offsetTop;
		left += el.offsetLeft;
	}

	return (
		top >= window.pageYOffset &&
		left >= window.pageXOffset &&
		(top + height) <= (window.pageYOffset + window.innerHeight) &&
		(left + width) <= (window.pageXOffset + window.innerWidth)
	);
}