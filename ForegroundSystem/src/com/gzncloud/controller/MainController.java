package com.gzncloud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/index")
public class MainController {
	// 跳转到首页
	/**
	 * 主服务控制器
	 * 
	 * @return String
	 */
	@RequestMapping("/index")
	public String redirectIndex() {
		return "index";
	}
}
