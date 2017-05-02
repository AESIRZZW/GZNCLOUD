package com.gzncloud.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gzncloud.domain.OrderedLockers;
import com.gzncloud.domain.Orders;
import com.gzncloud.service.OrdersService;

@Controller
@RequestMapping("/Order")

public class OrdersController {

	@Resource
	private OrdersService ordersService;

	@RequestMapping("/ordergeneration")
	public String orderGeneratio(@RequestBody OrderedLockers orderedLockers, HttpServletRequest httpServletRequest) {
		System.out.println("订单服务控制器已封装所获取的OrderedLockers-JSON");
		System.out.println("正在连接订单生成服务");
		Orders orders = ordersService.orderGeneration(orderedLockers, httpServletRequest);
		if (orders == null) {
			System.out.println("当前订单生成服务出错");
			System.out.println();
			return null;
		}
		System.out.println("当前订单生成服务完成");
		System.out.println();
		httpServletRequest.setAttribute("prePayOrders", orders);
		return "forward:/payment/paymentmethod";
	}

}
