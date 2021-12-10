package com.edu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.dao.Report2DAO;
import com.edu.entity.Report2DTO;
import com.edu.service.Report2Service;

@Service
public class Report2Servicelmpl implements Report2Service{

	@Autowired
	Report2DAO rdao;

	@Override
	public List<Report2DTO> findReport() {
		return rdao.findAllByCount();
	}
	
	
}
