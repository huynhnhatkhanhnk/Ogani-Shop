package com.edu.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edu.entity.Rate;

public interface RateDAO extends JpaRepository<Rate, Integer>{

}
