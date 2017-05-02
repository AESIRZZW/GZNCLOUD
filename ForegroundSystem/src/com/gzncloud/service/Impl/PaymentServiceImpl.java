package com.gzncloud.service.Impl;

import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gzncloud.domain.Devices;
import com.gzncloud.domain.OrderedLockers;
import com.gzncloud.service.DevicesService;
import com.gzncloud.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Resource
	private DevicesService devicesService;

	/**
	 * 根据{前台请求购物盒数据}返回{客服信息数据}
	 * 
	 * @param orderedLockers
	 * @return HashMap<String, Object>
	 */
	@Override
	public HashMap<String, Object> showErrorInfo(OrderedLockers orderedLockers) {

		// TODO 自动生成的方法存根
		// 声明变量
		Devices devices = new Devices();

		// 调用查询服务获取所需数据
		devices = devicesService.selectDevicesByDevice(orderedLockers.getDevice());
		// 如果查询成功，构建回传数据
		if (devices != null) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			// 填充数据
			map.put("device_status", devices.getStatus());
			map.put("error_message", "很抱歉您的交易出错，请联系客服");
			map.put("customer_service_hotline", "13912345678");
			map.put("customer_service_wechat", "er.png");
			return map;
		} else {
			System.out.println("     系统未查询到该设备，请检查");
			return null;
		}
	}

	/**
	 * 根据{前台请求购物盒数据}返回{校验结果数据}
	 * 
	 * @param orderedLockers
	 * @return boolean
	 */
	@Override
	public boolean paymentCompleted(OrderedLockers orderedLockers) {
		// TODO 自动生成的方法存根
		// 声明变量
		Devices devices = new Devices();
		// 调用查询服务获取所需数据
		devices = devicesService.selectDevicesByDevice(orderedLockers.getDevice());
		if (devices.getStatus().equals("在线")) {
			System.out.println(devices.toString());
			return true;
		}
		if (devices.getStatus().equals("离线")) {
			System.out.println("     当前设备已离线");
			return false;
		} else {
			System.out.println("     设备信息有误");
			return false;
		}
	}
	 
}
