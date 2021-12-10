package com.edu.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.edu.dao.AccountDAO;
import com.edu.entity.Account;
import com.edu.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService{
	@Autowired
	AccountDAO dao;
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public Account findById(String username) {
		return dao.findById(username).get();
	}

	
	public List<Account> getAdministrators() {
		return dao.getAdministrators();
	}

	public List<Account> findAll() {
		return dao.findAll();
	}
	
	@Override
	public void save(Account entity) {
		dao.save(entity);
	}


	
	public void updateResetToken(String token, String email) throws ClassNotFoundException {
        Account account = dao.findbyemail(email);
        if (account != null) {
            account.setResetToken(token);
            dao.save(account);
        } else {
            throw new ClassNotFoundException("Could not find any Account with the email " + email);
        }
    }
	
	public void updatePassword(Account account, String newPassword) {		
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(newPassword);                //mã hóa password
        account.setPassword(encodedPassword);	
//		account.setPassword(newPassword);											//ko mã hóa
        account.setResetToken(null);
        dao.save(account);
    }
	
	@Override
	public Account findByResetToken(String resetToken) {
        return dao.findByResetToken(resetToken);
	}


	@Override
	public Account getByResetToken(String token) {
        return dao.findByResetToken(token);
	}

	@Override
	public Optional<Account> findByEmail(String email) {
        return dao.findByEmail(email);
    }



//	@Override
//	public void delete(String username) {
//		dao.deleteById(username);
//	}


	@Override
	public Account create(Account account) {
		return dao.save(account);
	}
	
	@Override
	public Account update(Account account) {
		return dao.save(account);
	}
	
	@Override
	public Account delete(Account account) {
		account.setDeletehiden(true);
		return dao.save(account);
	}
	
	public void profileupdate(Account account, String name, String address, String phone, String image) {
		account.setName(name);
		account.setAddress(address);
		account.setPhone(phone);
		account.setImage(image);
		dao.save(account);
	}

	public void changepassword(Account account, String password) {
		account.setPassword(password);
		dao.save(account);
	}


	@Override
	public Account findByname(String remoteUser) {
		return dao.findbyname(remoteUser);
	}
}