package com.edu.service;

import java.util.List;
import com.edu.entity.Order;
import com.fasterxml.jackson.databind.JsonNode;



public interface OrderService {

	Order crate(JsonNode orderData);
	
	Order findById(Long id);

	List<Order> findByUsername(String username);

	List<Order> findAll();

	void delete(Long id);	
	
	Order update(Order order);

	Order save(Order order);

}
