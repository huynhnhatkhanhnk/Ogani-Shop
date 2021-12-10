package com.edu.service;

import java.util.List;
import java.util.Optional;

import com.edu.entity.Account;




public interface AccountService {
	Account findById(String username);

	public	List<Account> getAdministrators();

	public	List<Account> findAll();
	
	public void save(Account entity);
	
	Account findByResetToken(String resetToken);

	Account getByResetToken(String token);

	Optional<Account> findByEmail(String email);

//	void delete(String username);
	
	Account create(Account account);
	
	Account update(Account account);
	
	Account delete(Account account);

	Account findByname(String remoteUser);

}