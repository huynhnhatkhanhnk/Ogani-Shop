package com.edu.service;

import java.util.List;
import com.edu.entity.OrderDetail;

public interface OrderDetailService {
	OrderDetail findById(Long id);
	List<OrderDetail> findAll();
}
