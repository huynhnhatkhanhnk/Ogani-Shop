package com.edu.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edu.entity.OrderDetail;


public interface OrderDetailDAO extends JpaRepository<OrderDetail, Long> {

}
