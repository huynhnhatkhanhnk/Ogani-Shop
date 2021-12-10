package com.edu.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edu.entity.Category;



public interface CategoryDAO extends JpaRepository<Category, String> {

}
