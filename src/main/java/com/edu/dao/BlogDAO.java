package com.edu.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edu.entity.Blog;

public interface BlogDAO extends JpaRepository<Blog, Integer>{

}
