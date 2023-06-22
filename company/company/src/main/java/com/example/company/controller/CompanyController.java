package com.example.company.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.company.dto.CommonData;
import com.example.company.dto.Commute;
import com.example.company.dto.Emp;
import com.example.company.service.CompanyService;
import com.example.company.util.MyUtil;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class CompanyController {

	@Autowired
	private CompanyService companyService;

	@Autowired
	private MyUtil myUtil;

	@RequestMapping(value = "/")
	public String index() {
		return "index";
	}

	@RequestMapping(value = "/common", method = RequestMethod.GET)
	public String common() {
		return "html/commonIndex";
	}

	@RequestMapping(value = "/common", method = RequestMethod.POST)
	public String commonData(HttpServletRequest request, Model model) {
		int empno = Integer.parseInt(request.getParameter("empno"));

		Emp emp = companyService.findCommon(empno);
		Commute commute = companyService.findComeOut(empno);

		model.addAttribute("emp", emp);
		model.addAttribute("commute", commute);

		return "html/commonIndex";
	}

	@RequestMapping(value = "/admin")
	public String admin() {
		return "html/adminIndex";
	}

	@RequestMapping(value = "/checkIn", method = RequestMethod.POST)
	public String checkIn(HttpServletRequest request, Model model) {
		int empno = Integer.parseInt(request.getParameter("empno"));

		companyService.checkIn(empno);

		return "redirect:/";
	}

	@RequestMapping(value = "/checkOut", method = RequestMethod.POST)
	public String checkOut(HttpServletRequest request, Model model) {
		int empno = Integer.parseInt(request.getParameter("empno"));

		companyService.checkOut(empno);

		return "redirect:/";
	}
	
	@RequestMapping(value = "/commonList", method = RequestMethod.GET)
	public String commonView() {
		
		return "html/commonList";
	}
	
	@RequestMapping(value = "/commonList", method = RequestMethod.POST)
	public String commonList(HttpServletRequest request, Model model) {
		String pageNum = request.getParameter("pageNum"); // 바뀌는 페이지 번호
		int currentPage = 1; // 현재 페이지 번호 (default)
		int empno = Integer.parseInt(request.getParameter("empno"));
		int dataCount = companyService.getDataCount(empno);

		if (pageNum != null) {
			currentPage = Integer.parseInt(pageNum);
		}

		// 2. 페이징 처리를 함. (준비)
		int numPerPage = 5; // 페이지당 보여줄 데이터의 갯수
		int totalPage = myUtil.getPageCount(numPerPage, dataCount); // 페이지의 전체 갯수를 구한다

		if (currentPage > totalPage) { // totalPage 보다 크면 안됨.
			currentPage = totalPage;
		}

		int start = (currentPage - 1) * numPerPage + 1; // 1 6 11 16 ...
		int end = currentPage * numPerPage; // 5 10 15 20 ...

		List<CommonData> commonList = companyService.getCommonLists(empno, start, end);
		
		String param = "";

		String listUrl = "/commonList?empno=" + empno;

		// list?searchKey=name&searchValue=어피치
		if (!param.equals("")) {
			listUrl += "?" + param;
		}

		// ◀이전 1 2 3 4 5 다음▶
		String pageIndexList = myUtil.pageIndexList(currentPage, totalPage, listUrl);
		

		model.addAttribute("empno", empno);
		model.addAttribute("commonList", commonList);
		model.addAttribute("pageIndexList", pageIndexList);
		model.addAttribute("dataCount", dataCount);

		return "html/commonList";
	}
}
