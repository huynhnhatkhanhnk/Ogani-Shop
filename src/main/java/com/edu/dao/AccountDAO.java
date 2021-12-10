package com.edu.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.edu.entity.Account;

public interface AccountDAO extends JpaRepository<Account, String> {
	@Query("SELECT DISTINCT ar.account FROM Authority ar WHERE ar.role.id IN ('DIRE','STAF')")
	List<Account> getAdministrators();
	
	@Query("SELECT a FROM Account a WHERE a.deletehiden= 'false' ")
	List<Account> findAll();
	
	@Query(value = "select * from accounts where username = ?", nativeQuery = true)
    Optional<Account> findByUsername(String username);

	@Query(value ="select top 1 * from accounts where email = ?", nativeQuery = true)
    public Account findbyemail(String email);

	Account findByResetToken(String token);

    Optional<Account> findByEmail(String email);

    @Query("SELECT ac FROM Account ac WHERE ac.username = ?1")
	Account findbyname(String remoteUser);
}
