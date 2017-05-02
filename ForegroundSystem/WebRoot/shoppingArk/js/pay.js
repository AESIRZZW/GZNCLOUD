$(function() {
	var url = "http://192.168.0.12:8080/ForegroundSystem/rest/payment/errorinfo";
	//		加载时隐藏客服
	$('footer').hide();
	$('footer').children('img').hide();
	//		收取本地存储
	var key_1 = sessionStorage.getItem('key_1');
	var data = JSON.parse(key_1);
	console.log(data);
	$('.money').html(data.price);
	delete data["price"];
	var html = "";
	for (var i = 0; i < data.lockers.length; i++) {
		var name = data["lockers"][i].locker_name;
		var img = data["lockers"][i].locker_img;
		html += "<li><img src='img/" + img + "'><span>产品：<b>" + name + "</b></span></li>"
		delete data["lockers"][i].locker_name;
		delete data["lockers"][i].locker_img;
	}
	$('.lists').children('ul').append(html);
	console.log(data);
	//		解析成json字符串
	data = JSON.stringify(data);
	console.log(data);
	//		支付事件
	$('.sub').click(function() {
		console.log(data);
		alert('正在支付');
		$.ajax({
			type: "post",
			contentType: "application/json",
			url: url,
			data: data,
			dataType: "json",
			success: function(data) {
				if (data.device_status == "在线") {
					console.log(data);
					if (data.error_message == "") {
						$('支付成功')
					} else {
						alert(data.error_message);
						$('footer').show();
						$('footer').children('.a1').attr('href', 'tel:' + data.customer_service_hotline);
						$('footer').children('.a2').click(function() {
							$(this).siblings('img').attr('src', "img/" + data.customer_service_wechat).toggle('slow');
						})
					}
				} else {
					alert('设备离线，请选择其他购物柜');
					return false;
				}
			},
			error: function(data) {
				alert("异常！");
				console.log(data);
			}
		})
	})
})