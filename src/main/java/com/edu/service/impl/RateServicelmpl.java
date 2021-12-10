package com.edu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.dao.RateDAO;
import com.edu.entity.Rate;
import com.edu.service.RateService;

@Service
public class RateServicelmpl implements RateService{

	@Autowired
	RateDAO rateDao;
	
	@Override
	public Rate save(Rate rate) {
		return rateDao.save(rate);
	}

}
