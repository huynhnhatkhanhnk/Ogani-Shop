package com.edu.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.edu.entity.Product;
import com.edu.service.ProductService;

@Controller
public class CategoryController {
	@Autowired
	ProductService productService;
	
	@RequestMapping("/shop/pagecate")
	public String index(Model model, @RequestParam("cid") Optional<String> cid) {
		if(cid.isPresent()) {
			int currentPage = 1;
			String sortField = "price";
			String sortDir = "asc";
			String keyword = null;
			
			Page<Product> page = productService.findAllPage(currentPage, sortField, sortDir, keyword);
			
			List<Product> listProducts = productService.findByCategoryId(cid.get());
			model.addAttribute("listProducts",listProducts);
			
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("totalItems", page.getTotalElements());
			model.addAttribute("totalPages", page.getTotalPages());
			model.addAttribute("sortField", sortField);
			model.addAttribute("sortDir", sortDir);
			model.addAttribute("keyword", keyword);
			
			String reverseSortDir = sortDir.equals("asc") ? "desc" : "asc";
			model.addAttribute("reverseSortDir", reverseSortDir);
			
		}
		else {
			return "redirect:/shop/page";
		}
		return "product/shopcate";
	}
	
}
