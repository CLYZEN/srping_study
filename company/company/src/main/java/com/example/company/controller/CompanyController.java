package com.example.company.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.company.dto.Commute;
import com.example.company.dto.Emp;
import com.example.company.service.CompanyService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class CompanyController {
	
	@Autowired
	private CompanyService companyService;
	
	@RequestMapping(value = "/")
	public String index() {
		return "index";
	}
	
	@RequestMapping(value = "/common" , method = RequestMethod.GET)
	public String common() {
		return "html/commonIndex";
	}
	
	@RequestMapping(value = "/common" , method = RequestMethod.POST)
	public String commonData(HttpServletRequest request, Model model) {
		int empno = Integer.parseInt(request.getParameter("empno"));
		
		Emp emp = companyService.findCommon(empno);
		Commute commute = companyService.findComeOut(empno);
		
		model.addAttribute("emp", emp);
		model.addAttribute("commute",commute);
		
		return "html/commonIndex";
	}
	
	@RequestMapping(value = "/admin")
	public String admin() {
		return "html/adminIndex";
	}
	
	@RequestMapping(value = "/checkIn" , method = RequestMethod.POST)
	public String checkIn(HttpServletRequest request, Model model) {
		int empno = Integer.parseInt(request.getParameter("empno"));
		
		companyService.checkIn(empno);
		
		return "redirect:/";
	}
	
	@RequestMapping(value = "/checkOut" , method = RequestMethod.POST)
	public String checkOut(HttpServletRequest request, Model model) {
		int empno = Integer.parseInt(request.getParameter("empno"));
		
		companyService.checkOut(empno);
		
		return "redirect:/";
	}
	
	@RequestMapping(value = "/commonList")
	public String commonManage() {
		
		return "html/commonList";
	}
}
