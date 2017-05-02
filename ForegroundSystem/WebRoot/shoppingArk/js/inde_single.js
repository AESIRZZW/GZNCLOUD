$(function() {
	//		单个商品时获取二维码中设备号
	//						var numeber = window.document.location.search.slice(8);
	var numeber = "17040000111";
	var device = numeber.slice(0, 9);
	var cabinet = numeber.slice(9, 10);
	var locker = numeber.slice(10);
	var price = 0;
	var locker_id = 0;
	var locker_name = "";
	var locker_img = "";
	console.log(device + "--" + cabinet + "--" + locker);
	var url = "http://192.168.0.12:8080/ForegroundSystem/rest/lockers/lockerinfo";

	var data = {};
	data['device'] = device;
	data['cabinet'] = cabinet;
	data['locker'] = locker;
	data = JSON.stringify(data);
	console.log(data);
	$.ajax({
		type: "POSt",
		url: url,
		contentType: "application/json",
		dataType: "json",
		data: data,
		async: false,
		success: function(data) {
			console.log(data);
			if (data.device_status == '在线') {
				if (data.error_message == "请使用微信或支付宝扫描该二维码1") {
					alert('请使用微信或支付宝扫描该二维码');
					return false;
				} else {
					$('img').attr('src', 'img/' + data.lockers.image);
					$('.span1').html(data.lockers.name);
					$('.span2').html(data.lockers.price / 100);
					price = data.lockers.price / 100;
					locker_id = data.lockers.locker_id;
					locker_img = data.lockers.image;
					locker_name = data.lockers.name;
				}
			} else {
				alert('设备不在线，请选择其他购物柜购买！')
			}
		},
		error: function(xhr, textStatus, errMsg) {
			alert("异常！");
			console.log(xhr);
			console.log(textStatus);
			console.log(errMsg);
		}
	})
	$('.submit').click(function() {
		alert('准备支付');
		var data = {};
		var lockers = [];
		data['device'] = device;
		lockers[0] = {};
		lockers[0]['cabinet'] = cabinet;
		lockers[0]['locker'] = locker;
		lockers[0]['locker_id'] = locker_id;
		lockers[0]['price'] = price;
		data['lockers'] = lockers;
		data = JSON.stringify(data);
		console.log(data);
		var url = "http://192.168.0.12:8080/ForegroundSystem/rest/payment/paymentcompleted";
		$.ajax({
			type: "POSt",
			url: url,
			contentType: "application/json",
			dataType: "json",
			data: data,
			async: false,
			success: function(data) {
				console.log(data);
				alert('向后台请求成功');
				//调用微信支付
				//				pay();
			},
			error: function(xhr, textStatus, errMsg) {
				alert("异常！");
				console.log(xhr);
				console.log(textStatus);
				console.log(errMsg);
			}
		});
		//			本地缓存
		//			判断是否支持H5缓存
		if (window.localStorage) {
			data = JSON.parse(data);
			console.log(data);
			data['price'] = price;
			lockers[0]['locker_name'] = locker_name;
			lockers[0]['locker_img'] = locker_img;
			data['lockers'] = lockers;
			console.log(data);
			data = JSON.stringify(data);
			console.log(data);
			sessionStorage.setItem('key_1', data);
			//			window.location.href = "pay.html";

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

	//					　微信支付
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