package com.edu.service;

import java.util.List;
import org.springframework.data.domain.Page;
import com.edu.entity.Product;

public interface ProductService {
	
	//Lấy ra danh sách sản phẩm
	List<Product> findAll();
	
	//Lấy danh sách sản phẩm theo danh mục
	List<Product> findByCategoryId(String cid);
	
	//Lấy sản phẩm theo id
	Product findById(Integer productid);
	
	Page<Product> findAllPage(int currentPage, String sortField, String sortDir, String keyword);

	List<Product> filerBetweenPrice(Double priceMin, Double priceMax);

	Product create(Product product);

	Product update(Product product);
	
	Product delete(Product product);	
}
