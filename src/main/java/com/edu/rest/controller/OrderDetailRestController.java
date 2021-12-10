package com.edu.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.entity.OrderDetail;
import com.edu.service.OrderDetailService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/orderdetail")
public class OrderDetailRestController {
	@Autowired
	OrderDetailService orderdetailService;

	@GetMapping()
	public List<OrderDetail> findAll() {
		return orderdetailService.findAll();
	}
}
