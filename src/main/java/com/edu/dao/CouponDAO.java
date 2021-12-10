package com.edu.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.edu.entity.Coupon;

public interface CouponDAO extends JpaRepository<Coupon, String> {
	@Query("SELECT c FROM Coupon c WHERE c.deletehiden= 'false' ")
	List<Coupon> findAll();
	
}
