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
import com.gzncloud.domain.RequestLocker;
import com.gzncloud.service.LockersService;

@Controller
@RequestMapping("/lockers")
public class LockersController {

	@Resource
	private LockersService lockersService;

	/**
	 * 购物盒查询服务控制器
	 * 
	 * @param requestLocker
	 * @return HashMap<String, Object>
	 */
	@RequestMapping("/lockerinfo")
	public @ResponseBody HashMap<String, Object> showInfoByRequestLocker(@RequestBody RequestLocker requestLocker) {
		System.out.println("购物盒控制器已封装所获取的requestLocker-JSON");
		System.out.println("正在连接购物盒查询服务");
		HashMap<String, Object> map = lockersService.showInfoByRequestLocker(requestLocker);
		if (map == null) {
			System.out.println("未查询到该购物盒相关信息");
			System.out.println();
			return null;
		}
		System.out.println("已查询到所请求的购物盒相关信息");
		System.out.println();
		return map;
	}

	/**
	 * 购物盒开启控制器
	 * 
	 * @param httpServletRequest
	 * @return String
	 */
	@RequestMapping("/openlockers")
	public String openLockers(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		OrderedLockers lockersNeedOpen = (OrderedLockers) httpServletRequest.getAttribute("LockersNeedOpen");
		System.out.println("正在连接开锁服务");
		Boolean openLockers = lockersService.openLockers(lockersNeedOpen);
		if (openLockers == true) {
			System.out.println("开锁指令已发送");
			System.out.println();
			return null;
		} else {
			System.out.println("开锁指令发送出错");
			System.out.println();
			httpServletRequest.setAttribute("ErroredLockers", openLockers);
			return "forward:/rest/payment/errorinfo";
		}
	}
}
