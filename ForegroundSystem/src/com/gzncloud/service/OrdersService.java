package com.gzncloud.service;

import javax.servlet.http.HttpServletRequest;

import com.gzncloud.domain.OrderedLockers;
import com.gzncloud.domain.Orders;

public interface OrdersService {
	Orders orderGeneration(OrderedLockers orderedLockers,HttpServletRequest httpServletRequest);
}
