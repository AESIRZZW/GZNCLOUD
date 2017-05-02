package com.gzncloud.service.Impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.gzncloud.domain.Orders;
import com.gzncloud.service.WeChatService;
import com.gzncloud.util.IPUtil;
import com.gzncloud.wechat.core.Wepay;
import com.gzncloud.wechat.core.WepayBuilder;
import com.gzncloud.wechat.model.pay.JsPayRequest;
import com.gzncloud.wechat.model.pay.JsPayResponse;

@Service
public class WeChatServiceImpl implements WeChatService {

	@Override
	public JsPayResponse h5Pay(Orders orders ,HttpServletRequest httpServletRequest) {
		// TODO 自动生成的方法存根
		Wepay wepay = WepayBuilder.newBuilder("appId", "appKey", "mchId").build();
		
		// 声明变量
		JsPayRequest request = new JsPayRequest();
		
		// 获取当前时间
		SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		
		// 初始化请求参数
		request.setBody("广州云近科技"+orders.getDevice()+"号自动贩卖购物设备");
		request.setOutTradeNo(orders.getTrade_no());
		request.setTotalFee(orders.getDiscounted_amount());
		request.setClientId(IPUtil.getIpAddress(httpServletRequest));
		request.setNotifyUrl("/payment/paymentcompleted");
		request.setTimeStart(tempDate.format(new Date(System.currentTimeMillis())));
		JsPayResponse jsPayResponse = wepay.pay().jsPay(request);
		return jsPayResponse;
	}
}
