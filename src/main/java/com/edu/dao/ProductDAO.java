package com.edu.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.edu.entity.Product;



public interface ProductDAO extends JpaRepository<Product, Integer> {
//	@Query("SELECT p FROM Product p WHERE p.category.id=?1")
//	public Page<Product> findAllcate(String cid, Pageable pageable);
	
	@Query("SELECT p FROM Product p WHERE p.deletehiden= 'false' ")
	List<Product> findAll();
	
	@Query("SELECT p FROM Product p WHERE p.deletehiden= 'false' ")
	Page<Product> findAllPage(int currentPage, String sortField, String sortDir, String keyword, Pageable pageable);
	
	@Query("SELECT p FROM Product p WHERE p.category.id=?1 and p.deletehiden= 'false' ")
	public List<Product> findByCategoryId(String cid);
	
	//SERCH
//	@Query("SELECT p FROM Product p WHERE CONCAT(p.name, '', p.price) LIKE %?1%")
	
	@Query("SELECT p FROM Product p WHERE " 
			+ "CONCAT(p.name)"
			+ " LIKE %?1%")
	public Page<Product> findAll(String keyword, Pageable pageable);

	@Query("SELECT p FROM Product p WHERE p.price BETWEEN ?1  and ?2")
	List<Product> findAllProductBetweenPrice(Double Min, Double Max);

	


}
