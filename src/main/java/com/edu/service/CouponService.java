package com.edu.service;

import java.util.List;

import com.edu.entity.Coupon;
import com.edu.entity.Product;

public interface CouponService {
	List<Coupon> findAll();
	
	Coupon save(Coupon coupon);

	Coupon update(Coupon coupon);

	Coupon delete(Coupon coupon);

	Coupon findById(String code);

}
