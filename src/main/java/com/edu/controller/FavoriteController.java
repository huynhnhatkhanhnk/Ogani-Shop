package com.edu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FavoriteController {
	@RequestMapping("/favoriteproduct")
	public String shopingcart() {
		return "favorite_product/favorite_product";
	}
}
