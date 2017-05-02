package com.gzncloud.service;

import javax.servlet.http.HttpServletRequest;

import com.gzncloud.domain.Orders;

public interface AlipayService {

	String webPay(Orders orders, HttpServletRequest httpServletRequest);
	
}
