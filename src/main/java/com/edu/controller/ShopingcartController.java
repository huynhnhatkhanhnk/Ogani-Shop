package com.edu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.edu.entity.Coupon;
import com.edu.service.CouponService;

@Controller
public class ShopingcartController {
	@Autowired
	CouponService couponService;
	
	@RequestMapping("/shopingcart")
	public String shopingcart(Model model) {
		List<Coupon> coupon = couponService.findAll();
		model.addAttribute("coupon", coupon);
		return "cart/Shoping-cart";
	}
	
}
