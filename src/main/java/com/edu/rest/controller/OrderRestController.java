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

import com.edu.entity.Order;
import com.edu.service.OrderService;
import com.fasterxml.jackson.databind.JsonNode;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/orders")
public class OrderRestController {
	@Autowired
	OrderService orderService;

	@PostMapping()
	public Order create(@RequestBody JsonNode orderData) {
		return orderService.crate(orderData);
	}

	@GetMapping()
	public List<Order> findAll() {
		return orderService.findAll();
	}

	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Long id) {
		orderService.delete(id);
	}

	@PutMapping("{id}")
	public Order update(@PathVariable("id") Integer id, @RequestBody Order order) {
		return orderService.update(order);
	}
	
}
