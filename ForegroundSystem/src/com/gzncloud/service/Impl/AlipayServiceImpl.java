package com.gzncloud.service.Impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gzncloud.alipay.core.Alipay;
import com.gzncloud.alipay.core.AlipayBuilder;
import com.gzncloud.alipay.model.pay.WebPayDetail;
import com.gzncloud.domain.Orders;
import com.gzncloud.service.AlipayService;

@Service
public class AlipayServiceImpl implements AlipayService {

	@Override
	public @ResponseBody String webPay(Orders orders, HttpServletRequest httpServletRequest) {
		// TODO 自动生成的方法存根
		Alipay alipay = AlipayBuilder.newBuilder("merchantId", "secret").build();
		
		// 声明变量
		String outTradeNo = orders.getTrade_no();
		String orderName = "广州云近科技"+orders.getDevice()+"号设备";
		String totalFee = orders.getDiscounted_amount().toString();
		
		WebPayDetail webpayDetail = new WebPayDetail(outTradeNo, orderName, totalFee);
		
		// 初始化请求参数
	    webpayDetail.setOutTradeNo(outTradeNo);
	    webpayDetail.setOrderName(orderName);
	    webpayDetail.setTotalFee(totalFee);
		String string = alipay.pay().webPay(webpayDetail);
		return string;
	}
}
