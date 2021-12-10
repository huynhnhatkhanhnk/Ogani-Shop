package com.edu.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.entity.Category;
import com.edu.service.CategoryService;



@CrossOrigin("*")
@RestController
@RequestMapping("/rest/categories")
public class CategoryRestController {
	@Autowired
	CategoryService categoryService;
	
	@GetMapping()
	public List<Category> getAll() {
	return categoryService.findAll();
	}
	@PostMapping()
	public Category create(@RequestBody Category category) {
		return categoryService.save(category);
	}
	
	@PutMapping("{categoryid}")
	public Category update(@PathVariable("categoryid") String categoryid,
			@RequestBody Category category) {
		return categoryService.update(category);
	}
	
	@DeleteMapping("{categoryid}")
	public void delete(@PathVariable("categoryid") String categoryid) {
		categoryService.delete(categoryid);
	}
	
}
