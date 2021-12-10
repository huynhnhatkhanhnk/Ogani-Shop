package com.edu.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.entity.Product;
import com.edu.service.ProductService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/products")
public class ProductRestController {
	@Autowired
	ProductService productService;

	@GetMapping("{productid}")
	public Product getOne(@PathVariable("productid") Integer productid) {
		return productService.findById(productid);
	}

	@GetMapping()
	public List<Product> getAll() {
		return productService.findAll();
	}

	@PostMapping()
	public Product create(@RequestBody Product product) {
		return productService.create(product);
	}

	@PutMapping("{productid}")
	public Product update(@PathVariable("productid") Integer productid, @RequestBody Product product) {
		return productService.update(product);
	}
	
	@PutMapping("/delete/{productid}")
	public Product delete(@PathVariable("productid") Integer productid, @RequestBody Product product) {
		return productService.delete(product);
	}
}
