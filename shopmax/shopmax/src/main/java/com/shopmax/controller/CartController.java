package com.shopmax.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CartController {
	
	// 장바구니 리스트
	@GetMapping(value = "/cart")
	public String cart() {
		return "cart/cartList";
	}
}
