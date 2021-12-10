package com.edu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.dao.CategoryDAO;
import com.edu.entity.Category;
import com.edu.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	CategoryDAO cdao;

	@Override
	public List<Category> findAll() {
		return cdao.findAll();
	}

	@Override
	public Category save(Category category) {
		// TODO Auto-generated method stub
		return cdao.save(category);
	}

	@Override
	public Category update(Category category) {
		// TODO Auto-generated method stub
		return cdao.save(category);
	}

	@Override
	public void delete(String categoryid) {
		// TODO Auto-generated method stub
		cdao.deleteById(categoryid);
	}

	@Override
	public Category findById(String categoryid) {
		return cdao.findById(categoryid).get();
	}
}
