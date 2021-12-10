package com.edu.service;

import java.util.List;

import com.edu.entity.Blog;

public interface BlogService {
	List<Blog> findAll();

	Blog findById(Integer id);

	Blog save(Blog blog);

	Blog create(Blog blog);

	Blog update(Blog blog);

	void delete(Integer id);
}
