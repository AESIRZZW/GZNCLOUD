$(function() {
	//			加载页面时，获取链接的值，发送请求
	var url = "http://192.168.0.12:8080/ForegroundSystem/rest/devices/deviceinfo",
		urlPost,
		//		number = window.document.location.search.slice(8),
		number = "170400001",
		device = number.slice(0, 9);
	//		device = 1000000001;
	$.ajax({
		type: "post",
		contentType: "application/json",
		url: url,
		data: JSON.stringify({
			"device": device
		}),
		dataType: "json",
		async: false,
		success: function(data) {
			console.log(data)
				//拼接柜列表
			if (data.device_status == "在线") {
				urlPost = data.url;
				//				支付按钮判断
				if (data.error_message == "123") {
					alert("请使用微信或支付宝扫描该二维码!");
					return false;
				}
				for (var i = 0; i < data.cabinets_count; i++) {
					var uls = "<ul class='row row" + (i + 1) + "'></ul>";
					var spans = "<span class='cabinet cabinet" + (i + 1) + "'>" + (i + 1) + "号柜</span>";
					$('.showLists').before(uls);
					$('.title').append(spans);
					$(".cabinet").css('width', 100 / (i + 1) + '%');
				}
				var arr = data.cabinets;
				for (var i = 0; i < arr.length; i++) {
					var html = "";
					for (var j = 0; j < arr[i].lockers.length; j++) {
						var imgNumber = arr[i].lockers[j].image,
							classNumber = arr[i].lockers[j].cabinet.toString() + arr[i].lockers[j].locker,
							name = arr[i].lockers[j].name,
							money,
							locker_id = arr[i].lockers[j].locker_id;
						if (arr[i].lockers[j].price == '') {
							money = "";
						} else {
							money = arr[i].lockers[j].price / 100;
						}
						html += "<li class=" + "'checkbox  lis' style='background:url(img/" + imgNumber + ") center no-repeat;background-size:100% 100%'>" + "<input type='checkbox' value='" + classNumber + "'  id='" + "checkbox" + classNumber + "'name='number'/>" + "<label for='checkbox" + classNumber + "'><i>" + name + "</i><img src='img/checked.png'></label><span style='display:none;'>" + money + "</span><b style='display:none;'>" + locker_id + "</b><a style='display:none;'>" + imgNumber + "</a></li>";
					}
					$('.row' + (i + 1)).append(html);
				}
			} else {
				alert('设备故障，请选择其他设备购买！');
			}
		},
		error: function(xhr, textStatus, errMsg) {
			console.log(textStatus);
			console.log(errMsg);
		}
	});
	//	加载完操作dom
	$(document).ready(function() {
		//			选择货柜样式
		var len = $(".row").length;
		$('.cabinet1').addClass('bgc').siblings().addClass('border');
		$('.row1').slideDown();
		for (var i = 0; i < len; i++) {
			(function(i) {
				i = i + 1;
				$('.cabinet' + i).click(function() {
					$('.cabinet' + i).addClass('bgc').siblings().removeClass('bgc').addClass('border');
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
			var number2 = number1.slice(0, 1);
			var number3 = number1.slice(1);
			var name = $(this).siblings('label').children('i').html();
			var price = $(this).siblings('span').html();
			var imgNumber = $(this).parent('li').css('background-image');
			var html = "<li class=li" + number1 + ">" + "<span class='img' style='background:" + imgNumber + " center no-repeat;background-size:100% 100%'></span><div>柜号：" + "<span class='span span3'>" + number2 + "</span>&nbsp;&nbsp;&nbsp;&nbsp;盒号：<span class='span'>" + number3 + "</span></div>" + "<div>产品：<span class='span span4'>" + name + "</span>&nbsp;&nbsp;&nbsp;价格：￥<span class='span span5'>" + price + "</span></div><i onclick=''><span class='glyphicon glyphicon-remove'></span></i></li>";
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
				$(this).siblings('label').html('空');
				$(this).prop('checked', false);
			}

		});
		$("body").on('click', 'i', function() {
			var length = +$(this).parents('.lists').children('li').length - 2;
			var a = $(this).parent('li').prop("className").slice(2);
			money = +$('.span2').html();
			money -= +$('.li' + a).find('.span5').html();
			money = parseFloat(money.toFixed(2));
			$('.span2').html(money);
			$('.span1').html(length);
			$('#checkbox' + a).prop("checked", false);
			$(this).parent('li').remove();
			if (money < 1) {
				$('.showLists').fadeOut('slow');
			}
		});

		//		模拟支付

		$('.submit').click(function() {

				//				拼接data
				var check1 = [];
				var length = $('.showLists li').length - 1;
				var obj = document.getElementsByName("number");
				for (k in obj) {
					if (obj[k].checked)
						check1.push(obj[k].value);
				}
				console.log(check1);
				var data = {};
				var lockers = [];
				data['device'] = device;
				for (var i = 0; i < length; i++) {
					lockers[i] = {};
					lockers[i]['cabinet'] = check1[i].slice(0, 1);
					lockers[i]['locker'] = check1[i].slice(1);
					lockers[i]['price'] = $('#checkbox' + check1[i]).siblings('.span5').html();
					lockers[i]['locker_id'] = $('#checkbox' + check1[i]).siblings('b').html();
				}
				data['lockers'] = lockers;
				data = JSON.stringify(data);
				console.log(data);
				var url = "http://192.168.0.12:8080/ForegroundSystem/rest/payment/paymentcompleted";
				$.ajax({
					type: "post",
					contentType: "application/json",
					url: url,
					data: data,
					dataType: "json",
					async: false,
					success: function(data) {
						alert('123');
						console.log(data);
//						pay();
					},
					error: function(xhr, textStatus, errMsg) {
						console.log(textStatus);
						console.log(errMsg);
						alert('向微信请求失败');
					}
				})
				data = JSON.parse(data);
				console.log(data);
				//			本地缓存
				//			判断是否支持H5缓存
				if (window.localStorage) {
					data['price'] = money;
					for (var i = 0; i < length; i++) {
						lockers[i] = {};
						lockers[i]['locker_name'] = $('#checkbox' + check1[i]).siblings('label').children('i').html();
						lockers[i]['locker_img'] = $('#checkbox' + check1[i]).siblings('a').html();
					}
					data['lockers'] = lockers;
					data = JSON.stringify(data);
					console.log(data);
					sessionStorage.setItem('key_1', data);
//					window.location.href = "pay.html";
				} else {
					alert('您的浏览器不支持H5数据缓存，请换手机~');
				}
			})
			
					function pay() {
						alert('进入支付');
						var url = '${ctx}/wxOfficialAccountsPay/pay.do';
						$.ajax({
							type: "post",
							url: url,
							dataType: "json",
							data: {
								openId: '${openId}'
							},
							success: function(data) {
								if (data.result_code == 'SUCCESS') {　　
									appId = data.appid;　　　　
									sign = data.sign;　　
									timeStamp = data.timeStamp;　　
									nonceStr = data.nonce_str;　　
									packageStr = data.packageStr;　　
									signType = data.signType;
									//调起微信支付控件 
									callpay();　
									alert('调起微信支付控件');　
								} else {　　　　
									alert("统一下单失败");　　　　　　
								}
							},
							error: function(data) {
								alert('向微信请求失败');
							}
						});
					}
			
					//微信支付
					var prepay_id;
					var sign;
					var appId;
					var timeStamp;
					var nonceStr;
					var packageStr;
					var signType;
			
					function onBridgeReady() {
						WeixinJSBridge.invoke(
							'getBrandWCPayRequest', {
								"appId": appId, //公众号名称，由商户传入
								"paySign": sign, //微信签名
								"timeStamp": timeStamp, //时间戳，自1970年以来的秒数
								"nonceStr": nonceStr, //随机串
								"package": packageStr, //预支付交易会话标识
								"signType": signType //微信签名方式
							},
							function(res) {
								if (res.err_msg == "get_brand_wcpay_request:ok") {
									//window.location.replace("index.html");
									alert('支付成功');
								} else if (res.err_msg == "get_brand_wcpay_request:cancel") {
									alert('支付取消');
								} else if (res.err_msg == "get_brand_wcpay_request:fail") {
									alert('支付失败');
								}
								//使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回    ok，但并不保证它绝对可靠。
							}
						);
					}
			
					function callpay() {
						if (typeof WeixinJSBridge == "undefined") {
							if (document.addEventListener) {
								document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
							} else if (document.attachEvent) {
								document.attachEvent('WeixinJSBridgeReady', onBridgeReady);
								document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
							}
						} else {
							onBridgeReady();
						}
					}
	})

})