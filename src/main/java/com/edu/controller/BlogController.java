package com.edu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.edu.entity.Blog;
import com.edu.service.BlogService;

@Controller
public class BlogController {
	@Autowired
	BlogService blogService;
	@RequestMapping("/blog")
	public String blog(Model model)
	{
		List<Blog> blog = blogService.findAll();
		model.addAttribute("blog",blog);
		return "blog/Blog";
	}
	
	@GetMapping("/blogdetails/{id}")
	public String blogdetail(Model model, @PathVariable("id") Integer id) {
		Blog item = blogService.findById(id);
		model.addAttribute("item", item);
		List<Blog> blog = blogService.findAll();
		model.addAttribute("blog",blog);
		return "blog/blog_details";
	}
	
}