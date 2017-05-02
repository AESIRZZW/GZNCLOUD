package com.gzncloud.controller;

import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gzncloud.domain.Devices;
import com.gzncloud.service.DevicesService;

@Controller
@RequestMapping("/devices")
public class DevicesController {

	@Resource
	private DevicesService devicesService;

	/**
	 * 设备信息查询服务控制器
	 * 
	 * @param requestLocker
	 * @return HashMap<String, Object>
	 */
	@RequestMapping("/deviceinfo")
	@ResponseBody
	public HashMap<String, Object> showAllInfoByDevice(@RequestBody Devices devices, HttpServletResponse response) {
		System.out.println("设备控制器已封装所获取的Devices-JSON");
		System.out.println("正在连接设备信息查询服务");
		HashMap<String, Object> map = devicesService.showAllInfoByDevice(devices.getDevice());
		if (map == null) {
			System.out.println("未查询到该设备相关信息或该设备下没有购物柜");
			System.out.println();
			return null;
		}
		System.out.println("已查询到设备相关信息");
		System.out.println();
		return map;
	}

}
