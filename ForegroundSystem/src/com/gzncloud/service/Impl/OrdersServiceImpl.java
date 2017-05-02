package com.gzncloud.service.Impl;

import static org.hamcrest.CoreMatchers.nullValue;

import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.gzncloud.domain.Devices;
import com.gzncloud.domain.OrderedLockers;
import com.gzncloud.domain.Orders;
import com.gzncloud.service.DevicesService;
import com.gzncloud.service.OrdersService;
import com.gzncloud.util.MD5Util;

@Service
public class OrdersServiceImpl implements OrdersService {

	@Resource
	private DevicesService devicesService;

	@Override
	public Orders orderGeneration(OrderedLockers orderedLockers, HttpServletRequest httpServletRequest) {
		// TODO 自动生成的方法存根
		// 声明变量
		Orders orders = new Orders();
		Devices devices = new Devices();
		String payment_channel = null;
		Long initial_amount = (long) 0;
		String timeStampStr = null;
		String ordersInfoMd5 = null;
		
		// 调用查询服务获取所需数据
		devices = devicesService.selectDevicesByDevice(orderedLockers.getDevice());

		if (devices.getStatus().equals("在线") == false) {
			System.out.println("     当前设备信息有误或已离线");
			return null;
		}

		// 判断支付方式
		if (httpServletRequest.getHeader("user-agent").contains("MicroMessenger")) {
			payment_channel = "wechat";
		} else if (httpServletRequest.getHeader("user-agent").contains("Alipay")) {
			payment_channel = "alipay";
		}
		else{
			System.out.println("     支付方式暂未受支持,请使用支付宝或微信客户端扫描二维码");
			return null;
		}

		// 计算订单应付金额
		for (int i = 0; i < orderedLockers.getLockers().size(); i++) {
			initial_amount = initial_amount + orderedLockers.getPrices(orderedLockers.getLockers()).get(i);
		}

		// 初始化订单数据
		orders.setCreated_at(new Date());
		orders.setDevice(devices.getDevice());
		orders.setUser(devices.getUser());
	    orders.setPayment_channel(payment_channel);
	    orders.setInitial_amount(initial_amount);
	    orders.setRate((short) 80);
	    orders.setDiscounted_amount(initial_amount*orders.getRate()/100);
	    orders.setCurrency("CNY");

		// 生成商户订单号
	    // 生成16位时间戳
		Long timeStamp = Calendar.getInstance().getTimeInMillis();
		timeStampStr = String.format("%016d", timeStamp);
		// 生成16位MD5加密
		String ordersInfo = orders.toString();
		try {
			ordersInfoMd5 = MD5Util.MD5Encode16(ordersInfo);
		} catch (NoSuchAlgorithmException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		String trade_no = timeStampStr + ordersInfoMd5;
		orders.setTrade_no(trade_no);
		
		// 返回数据
		return orders;
	}
}
