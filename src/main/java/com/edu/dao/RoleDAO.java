package com.edu.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edu.entity.Role;



public interface RoleDAO  extends JpaRepository<Role, String>{

}
