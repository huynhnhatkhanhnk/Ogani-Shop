package com.edu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.dao.RoleDAO;
import com.edu.entity.Role;
import com.edu.service.RoleService;




@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleDAO dao;
	
	@Override
	public List<Role> findAll() {
		return dao.findAll();
	}

}
