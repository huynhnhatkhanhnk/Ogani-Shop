package com.edu.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.edu.dao.AccountDAO;
import com.edu.dao.AuthorityDAO;
import com.edu.dao.RoleDAO;
import com.edu.entity.Account;
import com.edu.service.AccountService;




@Controller
@RequestMapping("account")
public class AccountController {
	   @Autowired
		HttpSession session;
	    @Autowired
	    AccountService accountService;
	    @Autowired
	    AccountDAO accountDAO;
	    @Autowired
	    AuthorityDAO authorityDAO;
	    @Autowired
		BCryptPasswordEncoder bCryptPasswordEncoder;
	    @Autowired
	    RoleDAO roleDAO;

	    @GetMapping("dk")
	    public String add(Model model) {
	        model.addAttribute("account", new Account());
	        return "security/Sign_up";
	    }

	    @PostMapping("saveOrUpdate")
	    public String saveOrUpdate(ModelMap model, 
	    @Valid @ModelAttribute("account") Account dto, BindingResult result) {
	    	 if(result.hasErrors()){
	             return "security/Sign_up";
	         }
	        Account entity = new Account();
	        BeanUtils.copyProperties(dto, entity);
	        accountService.save(entity);
	        model.addAttribute("message", "Register Success!");
	        return "redirect:/security/login/form";
	    }

	    
}
