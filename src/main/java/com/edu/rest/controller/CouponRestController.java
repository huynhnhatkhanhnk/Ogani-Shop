package com.edu.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.entity.Coupon;
import com.edu.entity.Product;
import com.edu.service.CouponService;



@CrossOrigin("*")
@RestController
@RequestMapping("/rest/coupons")
public class CouponRestController {
	@Autowired
	CouponService couponService;
	
	@GetMapping()
	public List<Coupon> getAll() {
	return couponService.findAll();
	}
	
	@GetMapping("{code}")
	public Coupon getOne(@PathVariable("code") String code) {
		return couponService.findById(code);
	}
	
	@PostMapping()
	public Coupon create(@RequestBody Coupon coupon) {
		return couponService.save(coupon);
	}
	
	@PutMapping("{code}")
	public Coupon update(@PathVariable("code") String code,
			@RequestBody Coupon coupon) {
		return couponService.update(coupon);
	}
	
	@PutMapping("/delete/{code}")
	public Coupon delete(@PathVariable("code") String code, @RequestBody Coupon coupon) {
		return couponService.delete(coupon);
	}
	
}
