package com.edu.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.edu.entity.Account;
import com.edu.entity.Authority;


public interface AuthorityDAO extends JpaRepository<Authority,Integer> {

	void deleteById(Integer id);
	@Query("SELECT DISTINCT a FROM Authority a WHERE a.account IN ?1")
	List<Authority> authoritiesOf(List<Account> accounts);
	
}
