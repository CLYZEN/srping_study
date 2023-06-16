package com.example.board2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BoardController {
	
	@RequestMapping(value = "/")
	public String index() {
		
		return "/index";
	}
	
	@RequestMapping(value = "/created", method = RequestMethod.GET) // Get 방식으로 request가 들어올 때 created() 실행
	public String created() {
		
		return "/bbs/created";
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET) 
	public String list() {
		
		return "/bbs/list";
	}
	
	@RequestMapping(value = "/article", method = RequestMethod.GET) 
	public String article() {
		
		return "/bbs/article";
	}
	
	@RequestMapping(value = "/updated", method = RequestMethod.GET) 
	public String updated() {
		
		return "/bbs/updated";
	}
}