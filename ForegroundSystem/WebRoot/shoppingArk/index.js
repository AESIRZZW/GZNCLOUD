$(function() {
	var url = "http://192.168.0.112:8080/SpringMVC_Spring_mybatis/goods/getAllGoods";
	//			加载页面时发送请求
	var numeber = window.document.location.search.slice(8),
		device = numeber.slice(0, 9);

	$.ajax({
		type: "post",
		url: "test.json",
		data: {
			"device": device
		},
		dataType: "json",
		async: false,
		success: function(data) {
			console.log(data)
				//拼接柜列表
			for (var i = 0; i < data.cabinets_count; i++) {
				var uls = "<ul class='row row" + (i + 1) + "'></ul>";
				var spans = "<span class='ark ark" + (i + 1) + "'>" + (i + 1) + "柜</span>"
				$('.showLists').before(uls);
				$('.title').append(spans);
			}
			//			拼接盒列表

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
					//					console.log(newLockers[key][k]);
					var classNumber = +newLockers[key][k].cabinet + newLockers[key][k].locker;
					var imgNumber = +newLockers[key][k].image;
					var name = newLockers[key][k].name;
					var money = newLockers[key][k].prize;
					html += "<li class=" + "'checkbox  lis lis" + imgNumber + "'>" + "<input type='checkbox' value='" + classNumber + "'  id='" + "checkbox" + classNumber + "'name='number'/>" + "<label for='checkbox" + classNumber + "'>" + name + "</label><span style='display:none;'>" + money + "</span></li>";
				}
				$('.row' + i).append(html);

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
			var name = $(this).siblings('label').html();
			var prize = $(this).siblings('span').html();
			var html = "<li class=li" + number1 + ">" + "<img src=img/2.jpg><div>柜号：" + "<span class='span span3'>" + number2 + "</span></div>" + "<div>产品：<span class='span span4'>" + name + "</span>&nbsp;&nbsp;&nbsp;价格：￥<span class='span span5'>" + prize + "</span></div><i><span class='glyphicon glyphicon-remove'></span></i></li>";
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
				console.log("商品已售出，请选择其他商品。")
				$(this).siblings('label').css({
					'backgroundColor': '#ddd',
					'color': 'red',
					'lineHeight': '36px'
				});
				$(this).siblings('label').html('空')
			}

		});
		$("body").on('click', "i", function() {

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
		})

		//			发送选中商品

		function submitMoney() {
			//				拼接data
			var length = $('.showLists li').length - 1;
			console.log(length);
			var data = {};
			var arr1 = [];
			var arr2 = [];
			var obj = document.getElementsByName("number");
			check_val = [];
			for (k in obj) {
				if (obj[k].checked)
					check_val.push(obj[k].value);
			}
			data['deveice'] = 'device';
			var lockers = {}
			for (var i = 0; i < length; i++) {
				lockers['locker' + i] = {};
				lockers['locker' + i]['cabinet'] = check_val[i].slice(0, 12);
				lockers['locker' + i]['locker'] = check_val[i].slice(12);
			}
			data['lockers'] = lockers;
			console.log(JSON.stringify(data));

			$.ajax({
				type: "post",
				url: url,
				data: $("#form").serializeArray(),
				dataType: "json",
				success: function(data) {
					alert(data);
				},
				error: function(data) {
					alert("异常！");
				}
			});
		}
		$('.pay1').click(function() {
			submitMoney();
		})
		$('.pay2').click(function() {
			submitMoney();
		})
	})

})