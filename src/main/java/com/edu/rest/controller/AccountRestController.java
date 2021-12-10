package com.edu.rest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edu.entity.Account;
import com.edu.service.AccountService;


@CrossOrigin("*")
@RestController
@RequestMapping("/rest/accounts")
public class AccountRestController {

	@Autowired
	AccountService accountService;
	
	@GetMapping
	public List<Account> getAccounts(@RequestParam("admin")Optional<Boolean> admin){
		if(admin.orElse(true)) {
			return accountService.getAdministrators();
		}
		return accountService.findAll();
	}
	
	@GetMapping("{username}")
	public Account getOne(@PathVariable("username") String username) {
		return accountService.findById(username);
	}
	@PostMapping()
	public Account create(@RequestBody Account account) {
		return accountService.create(account);
	}
	
	@PutMapping("{username}")
	public Account update(@PathVariable("username") String username,
			@RequestBody Account account) {
		return accountService.update(account);
	}
	
	@PutMapping("delete/{username}")
	public Account delete(@PathVariable("username") String username,
			@RequestBody Account account) {
		return accountService.delete(account);
	}
}
