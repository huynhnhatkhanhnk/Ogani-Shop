package com.edu.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.edu.entity.Order;
import com.edu.service.OrderService;

@Controller
public class OrderController {
	@Autowired
	OrderService orderService;

	@RequestMapping("/order/order1")
	public String order1() {
		return "order/Order1";
	}

	@RequestMapping("/order/list")
	public String list(Model model, HttpServletRequest request) {
		String username = request.getRemoteUser();
		model.addAttribute("orders", orderService.findByUsername(username));
		return "order/list";
	}

	@RequestMapping("/order/detail/{id}")
	public String detail(@PathVariable("id") Long id, Model model) {
		model.addAttribute("order", orderService.findById(id));
		return "order/detail";
	}
	
	@RequestMapping("/order/trangthai/{id}")
	public String trangthai(@PathVariable("id") Long id, Model model) {
		Order order = orderService.findById(id);
		return  updatetrangthai(order);
	}
	
	@PostMapping("/order/trangthai")
	public String updatetrangthai(Order order) {
		order.setStatuss("DAHUY");
		orderService.save(order);
		return "redirect:/order/list";
	}

}
