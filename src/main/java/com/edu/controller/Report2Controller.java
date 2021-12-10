package com.edu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edu.entity.Report2DTO;
import com.edu.service.Report2Service;

@Controller
public class Report2Controller {

	@Autowired
	Report2Service service;
	
	@ResponseBody
	@RequestMapping("/api/thongKe")
	public List<Report2DTO> thongKe() {
		
		return service.findReport();
	}
	
	@ResponseBody
	@RequestMapping("/api/thongKe2")
	public List<Report2DTO> thongKe2() {
		
		return service.findReport();
	}
	
	@ResponseBody
	@RequestMapping("/api/thongKe3")
	public List<Report2DTO> thongKe3() {
		
		return service.findReport();
	}
}
