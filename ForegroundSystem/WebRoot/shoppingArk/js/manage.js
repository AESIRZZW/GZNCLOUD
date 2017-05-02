$(function() {
	//底部菜单选择
	$('.nav1').addClass('color');
	$('.main1').show();
	var len = $('.col-xs-12').length;
	for (var i = 0; i < len; i++) {
		(function(i) {
			i = i + 1;
			$('.nav' + i).click(function() {
				$('.nav' + i).addClass('color').siblings().removeClass('color');
				$('.col-xs-12').fadeOut('slow');
				$('.main' + i).fadeIn('slow');
				var name=$('.nav' + i).children('span').eq(1).html();
				$('header').html('<a href="manage.html">首页</a> <span>>></span><a href="javascript:0" class="navigation1">'+name+'</a>');
			});
		})(i)
	}
//	$('.navigation1').click(function(){
//		if($('footer').find('span').html()==$(this).html()){
//			
//		}
//	})
	//页面菜单选择
	var height1 = $(window).height();
	var lenth1 = $('.main1').children().length;
	$('.main1').find('ul').height((height1 - 120 - lenth1 * 48) + 'px').css('overflow-y', 'scroll');
	$('.main1').find('ul').hide();
	for (var i = 0; i < len; i++) {
		(function(i) {
			i = i + 1;
			$('.title' + i).children('.tit').click(function() {
				$('.title' + i).siblings().children('ul').removeClass('animated fadeInUp').hide();
				$('.title' + i).children('ul').toggleClass('animated fadeInUp').toggle();
			});
		})(i)
	}
	//	购物柜详情加载
	var url = '';
	var userName = "admin";
	var html = "";
	$.ajax({
		type: "get",
		//		contentType: "application/json",
		url: "json/test3.json",
		data: JSON.stringify({
			"userName": userName
		}),
		dataType: "json",
		async: false,
		success: function(data) {
			//			console.log(data);
			//拼接列表
			//				console.log(data.cabinet_details[0].lokers.length);
			for (var i = 0; i < data.cabinet_details.length; i++) {
				for (var j = 0; j < data.cabinet_details[i].lokers.length; j++) {
					html += "<li>";
					html += "<img src='img/" + data.cabinet_details[i].lokers[j].img + "'/>";
					html += "<div><b class='glyphicon glyphicon-signal'></b><span>" + data.cabinet_details[i].device_status + "</span></div>";
					html += "<div><b class='glyphicon glyphicon-tag'></b><span>" + data.cabinet_details[i].lokers[j].cabinet + "&nbsp;&nbsp;(" + data.cabinet_details[i].lokers[j].loker_name + ")" + "</span></div>";
					html += "<div><b class='glyphicon glyphicon-map-mcabineter'></b><span>" + data.cabinet_details[i].lokers[j].loker_site + "&nbsp;&nbsp;(" + data.cabinet_details[i].lokers[j].coordinate + ")</span></div>";
					html += "<div><b class='glyphicon glyphicon-edit'></b><a  target='_blank'>查看更多</a></div>";
					html += "</li>";
				}
			}
			$('.title1').children('ul').append(html);

		},
		error: function(data) {
			console.log(data);
		}
	});
	$('.title1 li a').click(function() {
		var device = $(this).parent('div').siblings('div:eq(1)').find('span').html().slice(0, 10);
		console.log(device);
		$('.title1 li a').attr("href","demo/cabinet_details.html?device=" + device);
	})

	//商品详情加载
	var html1 = '';
	var userName = "admin";
	$.ajax({
		type: "get",
		//		contentType: "application/json",
		url: "json/test5.json",
		data: JSON.stringify({
			"userName": userName
		}),
		dataType: "json",
		async: false,
		success: function(data) {
			console.log(data);
			//拼接列表
			//				console.log(data.cabinet_details[0].lokers.length);
			for (var i = 0; i < data.goods.length; i++) {
				html1 += "<li>";
				html1 += "<img src='img/1.jpg' />";
				html1 += "<div><b class='glyphicon glyphicon-list-alt'></b>供货：<span>" + data.goods[i].goods_gross + "</span></div>";
				html1 += "<div><b class='glyphicon glyphicon-tasks'></b>售出：<span>" + data.goods[i].goods_sold + "</span></div>";
				html1 += "<div><b class='glyphicon glyphicon-stats'></b>余下：<span>" + data.goods[i].goods_surplus + "</span></div>";
				html1 += "<div><b class='glyphicon glyphicon-yen'></b>单价：￥&nbsp;<span>" + data.goods[i].goods_price + "</span>元</div>";
				html1 += "<div><b class='glyphicon glyphicon-gift'></b>折扣：<span>" + data.goods[i].goods_discount + "</span>折</div>";
				html1 += "<div><b class='glyphicon glyphicon-edit'></b><a  target='_blank'>查看更多</a></div>";
				html1 += "<span style='display:none;'>"+data.goods[i].goods_name+"</span>"
				html1 += "</li>";

			}
			$('.title2').children('ul').append(html1);
		},
		error: function(data) {
			console.log(data);
		}
		
	})
	$('.title2 li a').click(function() {
		var device = $(this).parent('div').siblings('span').html();
		console.log(device);
		$('.title2 li a').attr("href","demo/goods_details.html?device=" + device);
	})
});