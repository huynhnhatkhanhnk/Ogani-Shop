package com.edu.service;

import java.util.List;

import com.edu.entity.Category;

public interface CategoryService {
	List<Category> findAll();
	
	Category save(Category category);

	Category update(Category category);

	void delete(String categoryid);

	Category findById(String categoryid);
}
