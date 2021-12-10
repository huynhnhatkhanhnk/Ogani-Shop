package com.edu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.edu.dao.ProductDAO;
import com.edu.entity.Product;
import com.edu.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	ProductDAO pdao;

	@Override
	public List<Product> findAll() {
		return pdao.findAll();
	}

	@Override
	public List<Product> findByCategoryId(String cid) {
		return pdao.findByCategoryId(cid);
	}

	@Override
	public Product findById(Integer productid) {
		return pdao.findById(productid).get();
	}

	@Override
	public Page<Product> findAllPage(int pageNumber, String sortField, String sortDir, String keyword	) {

		Sort sort = Sort.by(sortField);
		sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
		
		Pageable pageable = PageRequest.of(pageNumber - 1, 9, sort);

		if (keyword != null) {
			return pdao.findAll(keyword, pageable);
		}

		return pdao.findAll(pageable);
	}
	

	@Override
	public List<Product> filerBetweenPrice(Double priceMin, Double priceMax) {
		return pdao.findAllProductBetweenPrice(priceMin, priceMax);
	}

	@Override
	public Product update(Product product) {
		return pdao.save(product);
	}
	
	@Override
	public Product delete(Product product) {
		product.setDeletehiden(true);
		return pdao.save(product);
	}

//	@Override
//	public void delete(Integer productid) {
//		pdao.deleteById(productid);
//	}
	
//	@Override
//	public List<Product> findCatePage(String cid, int currentPage, String sortField, String sortDir, String keyword) {
//		return pdao.findCatePage(cid);
//	}

	@Override
	public Product create(Product product) {
		return pdao.save(product);
	}

}
