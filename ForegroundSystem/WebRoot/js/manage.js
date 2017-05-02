$(function() {
	//底部菜单选择
	$('.nav1').addClass('color');
	$('.main1').show();
	var len=$('.col-xs-12').length;
	for (var i = 0; i < len; i++) {
		(function(i) {
			i = i + 1;
			$('.nav' + i).click(function() {
				$('.nav' + i).addClass('color').siblings().removeClass('color');
				$('.col-xs-12').fadeOut();
				$('.main' + i).fadeIn();
				$('header').html($('.nav' + i).children('span').eq(1).html())
			});
		})(i)
	}
	//页面菜单选择
	var height1=$(window).height();
	var lenth1=$('.main1').children().length;
	$('.main1').find('ul').height((height1-120-lenth1*48)+'px').css('overflow-y','scroll');
	$('.main1').find('ul').hide();
	for (var i = 0; i < len; i++) {
		(function(i) {
			i = i + 1;
			$('.title'+i).find('.tit').click(function() {
				$('.title'+i).siblings().find('ul').hide();
				$('.title'+i).find('ul').fadeToggle();
			});
		})(i)
	}
})