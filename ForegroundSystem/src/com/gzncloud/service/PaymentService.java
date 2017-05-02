package com.gzncloud.service;

import java.util.HashMap;

import com.gzncloud.domain.OrderedLockers;

public interface PaymentService {
	HashMap<String, Object> showErrorInfo(OrderedLockers orderedLockers);

	boolean paymentCompleted(OrderedLockers orderedLockers);
}
