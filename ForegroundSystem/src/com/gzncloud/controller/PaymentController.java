package com.gzncloud.controller;

import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.gzncloud.domain.OrderedLockers;
import com.gzncloud.domain.Orders;
import com.gzncloud.service.PaymentService;
import com.gzncloud.service.WeChatService;
import com.gzncloud.wechat.core.Wepay;
import com.gzncloud.wechat.core.WepayBuilder;
import com.gzncloud.wechat.model.pay.JsPayResponse;

@Controller
@RequestMapping("/payment")
public class PaymentController {

	@Resource
	private PaymentService paymentService;
	private WeChatService weChatService;

	/**
	 * 支付成功出货失败错误服务控制器
	 * 
	 * @param orderedLockers
	 * @return HashMap<String, Object>
	 */
	@RequestMapping("/errorinfo")
	public @ResponseBody HashMap<String, Object> showErrorInfo(@RequestBody OrderedLockers orderedLockers) {
		System.out.println("错误服务控制器已封装所获取的OrderedLockers-JSON");
		System.out.println("正在连接客服信息回执服务");
		HashMap<String, Object> map = paymentService.showErrorInfo(orderedLockers);
		if (map == null) {
			System.out.println("当前客服信息回执服务出错");
			System.out.println();
			return null;
		}
		System.out.println("当前客服信息回执服务完成");
		System.out.println();
		return map;
	}

	/**
	 * 支付成功请求开盒服务控制器
	 * 
	 * @param orderedLockers
	 * @return HashMap<String, Object>
	 */
	@RequestMapping("/paymentcompleted")
	public String paymentCompleted(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		System.out.println("开盒服务控制器已封装所获取的OrderedLockers-JSON");
		System.out.println("正在连接支付完成服务");
		OrderedLockers orderedLockers = (OrderedLockers) httpServletRequest.getAttribute("LockersNeedOpen");
		Boolean ifSuccess = paymentService.paymentCompleted(orderedLockers);	
		if (ifSuccess == true) {
			System.out.println("请求校验完成，准备打开购物盒");
			System.out.println();
			return "forward:/rest/lockers/openlockers";
		}
		System.out.println("请求校验失败，购物盒无法打开");
		System.out.println();
		return "forward:/payment/errorinfo";
	}

	@RequestMapping("/paymentmethod")
	public String paymentMethod(HttpServletRequest httpServletRequest) {
		Orders orders = (Orders) httpServletRequest.getAttribute("prePayOrders");
		System.out.println("支付服务控制器已获取所封装的orders对象");
		System.out.println("正在选择对应的支付方式");
		if (orders.getPayment_channel().equals("wechat")) {
			System.out.println("正在连接微信支付服务控制器");
			System.out.println();
			return "forward:/payment/wechatpay";
		} else if (orders.getPayment_channel().equals("alipay")) {
			System.out.println("正在连接支付宝支付服务控制器");
			System.out.println();
			return "forward:/payment/alipay";
		}
		System.out.println("请使用支付宝或微信客户端重新扫描二维码购买");
		System.out.println();
		return "forward:/payment/errorinfo";
	}

	@RequestMapping("/wechatpay")
	public @ResponseBody JsPayResponse wetChatPay(HttpServletRequest httpServletRequest) {
		Orders orders = (Orders) httpServletRequest.getAttribute("prePayOrders");
		System.out.println("支付服务控制器已获取所封装的orders对象");
		System.out.println("正在连接微信支付服务");
		JsPayResponse jsPayResponse = weChatService.h5Pay(orders, httpServletRequest);
		return jsPayResponse;
	}
}
