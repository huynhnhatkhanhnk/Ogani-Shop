package com.edu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.dao.OrderDAO;
import com.edu.dao.OrderDetailDAO;
import com.edu.dao.ProductDAO;
import com.edu.entity.OrderDetail;
import com.edu.service.OrderDetailService;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {
	@Autowired
	OrderDAO dao;
	
	@Autowired
	ProductDAO pdao;
	
	@Autowired
	OrderDetailDAO ddao;

	@Override
	public OrderDetail findById(Long id) {
		return ddao.findById(id).get();
	}

	@Override
	public List<OrderDetail> findAll() {
		return ddao.findAll();
	}
}
