package com.gzncloud.service;

import javax.servlet.http.HttpServletRequest;

import com.gzncloud.domain.Orders;
import com.gzncloud.wechat.model.pay.JsPayResponse;

public interface WeChatService {

	JsPayResponse h5Pay(Orders orders, HttpServletRequest httpServletRequest);

}
