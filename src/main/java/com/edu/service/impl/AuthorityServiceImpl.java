package com.edu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.dao.AccountDAO;
import com.edu.dao.AuthorityDAO;
import com.edu.entity.Account;
import com.edu.entity.Authority;
import com.edu.service.AuthorityService;


@Service
public class AuthorityServiceImpl implements AuthorityService{
	
	@Autowired
	AuthorityDAO authorityDAO;
	
	@Autowired
	AccountDAO accountDAO;
	
	@Override
	public List<Authority> findAuthoritiesOfAdministrators() {
		List<Account> accounts = accountDAO.getAdministrators();
		return authorityDAO.authoritiesOf(accounts);
	}

	@Override
	public List<Authority> findAll() {
		return authorityDAO.findAll();
	}

	@Override
	public Authority create(Authority auth) {
		
		return authorityDAO.save(auth);
	}

	@Override
	public void delete(Integer id) {
		authorityDAO.deleteById(id);
	}

}
