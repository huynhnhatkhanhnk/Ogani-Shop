package com.edu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ChartController {
	@RequestMapping("/chart")
	public String charts() {
		return "chart/chart";
	}
	
	@RequestMapping("/chart2")
	public String chart2() {
		return "chart/chart2";
	}
	
	@RequestMapping("/chart3")
	public String chart3() {
		return "chart/chart3";
	}
}
