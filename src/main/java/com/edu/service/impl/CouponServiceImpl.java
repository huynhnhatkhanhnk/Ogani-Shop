package com.edu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.dao.CouponDAO;
import com.edu.entity.Coupon;
import com.edu.service.CouponService;

@Service
public class CouponServiceImpl implements CouponService {
	@Autowired
	CouponDAO cpdao;

	@Override
	public List<Coupon> findAll() {
		return cpdao.findAll();
	}

	@Override
	public Coupon save(Coupon coupon) {
		// TODO Auto-generated method stub
		return cpdao.save(coupon);
	}

	@Override
	public Coupon update(Coupon coupon) {
		return cpdao.save(coupon);
	}

	@Override
	public Coupon delete(Coupon coupon) {
		coupon.setDeletehiden(true);
		return cpdao.save(coupon);
	}

	@Override
	public Coupon findById(String code) {
		return cpdao.findById(code).get();
	}

}
