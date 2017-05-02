$(function() {
	var url = '';
	//在全局定义验证码 
	var code;
	//产生验证码   
	function createCode() {
		code = "";
		var codeLength = 5; //验证码的长度     
		var checkCode = document.getElementById("checkCode");
		var random = new Array(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
			'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'); //随机数     
		for (var i = 0; i < codeLength; i++) { //循环操作     
			var charIndex = Math.floor(Math.random() * 36); //取得随机数的索引     
			code += random[charIndex]; //根据索引取得随机数加到code上     
		}
		checkCode.value = code; //把code值赋给验证码     
	}
	//校验验证码     
	function validate() {
		var inputCode = $('.captcha').val().toUpperCase(); //取得输入的验证码并转化为大写           
		if (inputCode.length <= 0) { //若输入的验证码长度为0     
			alert("请输入验证码！"); //则弹出请输入验证码     
		} else if (inputCode != code) { //若输入的验证码与产生的验证码不一致时     
			alert("验证码输入错误！"); //则弹出验证码输入错误     
			createCode(); //刷新验证码     
		} else { //输入正确时     
			alert("^-^"); //弹出^-^     
		}
	}
	$(document).ready(function() {　　
		createCode();
	});
	$('.code').click(function() {
		createCode();
	})
	$('.change').mouseenter(function() {
		$(this).css({
			'opacity': '.9',
			'background-color': 'rgba(148,148,148,.3)'
		});
	})
	$('.change').mouseleave(function() {
		$(this).css('opacity', '0');
	})
	$('.change').click(function() {
		createCode();
		$('.change').css('opacity', '0');
	})
	$('.sub').click(function() {
		validate();
		var userName = $('.userName').val();
		var passWord = $('.passWord').val();
		console.log(userName + "--" + passWord);
		if (userName.length <= 0 || passWord.length <= 0) {
			alert("请输入账号密码");
		} else if (userName.length < 4 || userName.length > 8) {
			alert('请输入4-8位账号名');
		} else if (passWord.length < 6 || passWord.length > 10) {
			alert('请输入6-10位密码');
		}
		var data = {};
		data[userName] = userName;
		data[passWord] = passWord;
		data = JSON.stringify(data);
		$.ajax({
			type: "POSt",
			url: url,
			contentType: "application/json",
			dataType: "json",
			data: data,
			async: false,
			success: function(data) {
				alert('登陆成功');
				console.log(data);
			},
			error: function(data) {
				alert('登陆失败');
				console.log(data);
			}
		});
	})
})