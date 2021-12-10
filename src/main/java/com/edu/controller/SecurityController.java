package com.edu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.edu.service.UserService;

@Controller
public class SecurityController {

	@RequestMapping("/security/login/form")
	public String loginForm(Model model) {
		model.addAttribute("message", "Vui lòng đăng nhập!");
		return "security/Login";
	}

	@RequestMapping("/security/login/success")
	public String loginSuccess(Model model) {
		model.addAttribute("message", "Đăng nhập thành công!");
		return "redirect:/home";
	}

	@RequestMapping("/security/login/error")
	public String loginError(Model model) {
		model.addAttribute("message", "Sai thông tin đăng nhập!");
		return "security/Login";
	}

	@RequestMapping("/security/login/unauthoried")
	public String unauthoried(Model model) {
		model.addAttribute("message", "Bạn không có quyền truy xuất!");
		return "security/Login";
	}

	@RequestMapping("/security/logoff/success")
	public String logoffSuccess(Model model) {
		model.addAttribute("message", "Bạn đã đăng xuất!");
		return "security/Login";
	}

	@Autowired
	UserService userService;

	@RequestMapping("/oauth2/login/success")
	public String success(OAuth2AuthenticationToken oauth2) {
		userService.loginFromOAuth2(oauth2);
		return "forward:/security/login/success";
	}
}
