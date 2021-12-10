package com.edu.dao;
import org.springframework.data.jpa.repository.JpaRepository;

import com.edu.entity.Contact;

public interface ContactDAO extends JpaRepository<Contact, String>{

}
