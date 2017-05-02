$(function() {
	//				增
	$('#add_cabinet').click(function() {
		$.get("../json/test6.json", function(data) {
			console.log(data);
			$('.add_cabinet .modal_ul li:eq(0) input').val(data.device);
		})
		sub();
	})

	//				删
	$('td button').click(function() {
			var data = $(this).parents('td:eq(0)').html();
			$.post('test3.json', {
				"data": data
			}, function(data) {})
		})
		//				改
	$('td button').click(function() {
			for (var j = 0; j < $('.modification .modal_ul li').length; j++) {
				$('.modification .modal_ul li:eq(' + j + ') input').val($(this).parent('td').siblings().eq(j).html());
			}
			sub();
		})
		//				查
	$('#inquire_cabinet').click(function() {
		$('tr').show();
		var teams = $("#teams option:selected").text();
		var status = $("#status option:selected").text();
		var device = $("#device").val();
		console.log(teams + "-" + status + "-" + device);

	})

	function sub() {
		$('.add_cabinet .sub').click(function() {
			alert("提交");
			console.log($(this).siblings('ul').children('li:eq(0)').find('input').val());
			var data = {};
			data["device"] = $(this).siblings('ul').children('li:eq(0)').find('input').val();
			data["loker_name"] = $(this).siblings('ul').children('li:eq(1)').find('input').val();
			data["loker_teams"] = $(this).siblings('ul').children('li:eq(2)').find('input').val();
			data["locker_status"] = $(this).siblings('ul').children('li:eq(3)').find('input').val();
			data["loker_site"] = $(this).siblings('ul').children('li:eq(4)').find('input').val();
			data["loker_coordinate"] = $(this).siblings('ul').children('li:eq(5)').find('input').val();
			data["locker_iccid"] = $(this).siblings('ul').children('li:eq(6)').find('input').val();
			data["activation_date"] = $(this).siblings('ul').children('li:eq(7)').find('input').val();
			data["expiry_date"] = $(this).siblings('ul').children('li:eq(8)').find('input').val();
			data["package_type"] = $(this).siblings('ul').children('li:eq(9)').find('input').val();
			data["pay_way"] = $(this).siblings('ul').children('li:eq(10)').find('input').val();
			data = JSON.stringify(data);
			console.log(data);
			$.post('../json/cabinet_manage.json', data, function(data) {
				alert('post');
				console.log(data);
				alert('end');
			})
			alert('end');
		})
	}

})