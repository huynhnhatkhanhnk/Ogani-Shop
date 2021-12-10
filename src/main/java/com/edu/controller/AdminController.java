package com.edu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {
	@RequestMapping("/admin")
	public String admin() {
		return "redirect:/assets/admin/index.html";
	}
    @GetMapping("/admin/loi/error")
	public String Error() {
		return "admin/error-403/error-403";
	}
}
