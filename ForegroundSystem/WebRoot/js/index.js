$(function() {
	//			加载页面时，获取链接的值，发送请求
	//var url = "http://192.168.0.112:8080/SpringMVC_Spring_mybatis/goods/getAllGoods",
	//	urlPost,
//		number = window.document.location.search.slice(8),
				number="170300001001",
		device = number.slice(0, 9);
	 var pathName = document.location.pathname;   
     var index = pathName.substr(1).indexOf("/");   
     var result = pathName.substr(0,index+1); 
     var url = result + "/rest/devices/deviceinfo";
	$.ajax({
		type: "post",
		url: url,
		data: '{"device":170300001}',
		contentType:"application/json",
		dataType: "json",
		async: false,
		success: function(data) {
			console.log(data);
				//拼接柜列表
			alert(data["cabinets"][0]["lockers"][1]["locker"]);
			if (data.device_status == "在线") {
				urlPost = data.url;
				//				支付按钮判断
				if (data.error_message == "alipay") {
					alert("请使用微信或支付宝扫描该二维码!");
					return false;
				}
				for (var i = 0; i < data.cabinets_count; i++) {
					var uls = "<ul class='row row" + (i + 1) + "'></ul>";
					var spans = "<span class='ark ark" + (i + 1) + "'>" + (i + 1) + "号柜</span>";
					$('.showLists').before(uls);
					$('.title').append(spans);
				}
				$('.ark').addClass('width' + data.cabinets_count);

				//拼接盒列表
				var lockers = data['lockers'];
				var newLockers = {};
				for (var key in lockers) {
					//遍历lockers
					cabinet = lockers[key]['cabinet'];
					//新的newLockers以cabinet为key,第一次添加像创建对象	
					if (typeof(newLockers[cabinet]) == "undefined") {
						newLockers[cabinet] = {};
					}
					newLockers[cabinet][key] = lockers[key];
				}

				var i = 0;
				for (key in newLockers) {
					var html = "";
					i++;
					for (k in newLockers[key]) {
						var classNumber = +newLockers[key][k].cabinet + newLockers[key][k].locker;
						var imgNumber = newLockers[key][k].image;
						console.log(imgNumber);
						var name = newLockers[key][k].name;
						var money = newLockers[key][k].prize;
						html += "<li class=" + "'checkbox  lis' style='background:url(img/" + imgNumber + ") center no-repeat;background-size:100% 100%'>" + "<input type='checkbox' value='" + classNumber + "'  id='" + "checkbox" + classNumber + "'name='number'/>" + "<label for='checkbox" + classNumber + "'><i>" + name + "</i><img src='img/checked.png'></label><span style='display:none;'>" + money + "</span></li>";
					}
					$('.row' + i).append(html);
				}
			} else {
				alert('设备故障，请选择其他设备购买！');
			}
		},
		error: function(data) {
			alert("异常！");
		}
	});
	//	加载完操作dom
	$(document).ready(function() {
		//			选择货柜样式
		var len = $(".row").length;
		$('.ark1').addClass('bgc').siblings().addClass('border');
		$('.row1').slideDown();
		for (var i = 0; i < len; i++) {
			(function(i) {
				i = i + 1;
				$('.ark' + i).click(function() {
					$('.ark' + i).addClass('bgc').siblings().removeClass('bgc').addClass('border');
					$('.row').slideUp();
					$('.row' + i).slideDown();
				});
			})(i)
		}
		//			选取产品拼接列表

		var money = 0;
		$("body").on('click', "input[type='checkbox']", function() {
			var length = $("input[type='checkbox']:checked").length;
			var number1 = $(this).val();
			var number2 = number1.slice(9, 16);
			var name = $(this).siblings('label').children('i').html();
			var prize = $(this).siblings('span').html();
			var imgNumber = $(this).parent('li').css('background-image');
			console.log(imgNumber);
			var html = "<li class=li" + number1 + ">" + "<span class='img' style='background:" + imgNumber + " center no-repeat;background-size:100% 100%'></span><div>柜号：" + "<span class='span span3'>" + number2 + "</span></div>" + "<div>产品：<span class='span span4'>" + name + "</span>&nbsp;&nbsp;&nbsp;价格：￥<span class='span span5'>" + prize + "</span></div><i onclick=''><span class='glyphicon glyphicon-remove'></span></i></li>";
			if ($(this).siblings('span').html().length > 0) {
				if ($(this).is(':checked')) {
					$('.lastLi').after(html);
					$('.span1').html(length);
					money += +$('.li' + number1).find('.span5').html();
					money = parseFloat(money.toFixed(2));
					$('.span2').html(money);
				} else {
					$('.span1').html(length);
					money -= +$('.li' + number1).find('.span5').html();
					money = parseFloat(money.toFixed(2));
					$('.span2').html(money);
					$('.li' + number1).remove();
				}
				if (length > 0) {
					$('.showLists').fadeIn('slow');
				} else {
					$('.showLists').fadeOut('slow');
				}
			} else {
				alert("商品已售出，请选择其他商品。");
				$(this).siblings('label').css({
					'backgroundColor': '#ddd',
					'color': 'red',
					'lineHeight': '40px'
				});
				$(this).siblings('label').html('空')
			}

		});
		$("body").on('click', 'i', function() {
			var length = +$(this).parents('.lists').children('li').length - 2;
			var a = $(this).parent('li').prop("className").slice(2);
			money = +$('.span2').html();
			console.log(money);
			money -= +$('.li' + a).find('.span5').html();
			money = parseFloat(money.toFixed(2));
			console.log(money);
			$('.span2').html(money);
			$('.span1').html(length);
			$('#checkbox' + a).prop("checked", false);
			$(this).parent('li').remove();
			if (money < 1) {
				$('.showLists').fadeOut('slow');
			}
		});

		//			发送选中商品
		
//		var data = {};
		var check = [];
//		function submitMoney() {
			//				拼接data
			//			获取选取的商品列表长度
			var length = $('.showLists li').length - 1;
			//			把选中的CheckBox的序号拼成数组
			var obj = document.getElementsByName("number");
			for (k in obj) {
				if (obj[k].checked)
					check.push(obj[k].value);
			}
			check=check.join(",");
			console.log(check)
				//		data转化成字符串
//			data['device'] = device;
//			var lockers = {};
//			for (var i = 0; i < length; i++) {
//					lockers['locker'+i] = {};
//					lockers['locker'+i]['cabinet'] = check[i].slice(0, 12);
//					lockers['locker'+i]['locker'] = check[i].slice(12);
//			}
//			var lockers = [];
//			for (var i = 0; i < length; i++) {
//					lockers[i] = {};
//					lockers[i]['cabinet'] = check[i].slice(0, 12);
//					lockers[i]['locker'] = check[i].slice(12);
//			}
//			data['lockers'] = lockers;
//			console.log(data);
//			console.log(JSON.stringify(data));
//
//			$.ajax({
//				type: "post",
//				url: url,
//				data: data,
//				dataType: "json",
//				success: function(data) {
//					alert(data);
//					if (data.device_status == "在线") {
//						window.location.href =urlPost;
//					} else {
//						alert('设备故障，请选择其他设备购买！');
//					}
//				},
//				error: function(data) {
//					alert("异常！");
//				}
//			});
//		}
//		$('.pay1').click(function() {
//			submitMoney();
//		})
//		$('.pay2').click(function() {
//			submitMoney();
//		})
//		模拟支付
		
		$('.submit').click(function(){
			window.location.href ="pay.html?money="+money+"&data="+check;
		})
	})

})