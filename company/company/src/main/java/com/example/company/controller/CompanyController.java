package com.example.company.controller;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

	@RequestMapping(value = "/commonListView", method = {RequestMethod.GET, RequestMethod.POST })
	public String commonList(HttpServletRequest request, Model model, CommonData commonData) {
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

		String listUrl = "/commonListView?empno=" + empno;

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

	@RequestMapping(value = "/commonDrop", method = RequestMethod.GET)
	public String commonDrop() {

		return "html/commonDrop";
	}

	@RequestMapping(value = "/commonDrop", method = RequestMethod.POST)
	public String commonDropOK(HttpServletRequest request, Model model) {
		int empno = Integer.parseInt(request.getParameter("empno"));

		Emp emp = companyService.findCommon(empno);

		if (emp != null) {
			companyService.deleteCommute(empno);
			companyService.deleteEmp(empno);
			model.addAttribute("message", "퇴사 완료! 행복해지셨습니다.");
		} else {
			model.addAttribute("message", "존재하지 않는 사번입니다.");
		}

		return "html/commonDrop";
	}

	@RequestMapping(value = "addEmployee", method = RequestMethod.GET)
	public String moveAddEmployee() {

		return "html/adminIndex";
	}

	@RequestMapping(value = "addEmployee", method = RequestMethod.POST)
	public String addEmployee(HttpServletRequest request, Model model) {
		String name = request.getParameter("name");
		companyService.insertEmployee(name);
		int empno = companyService.getNowEmpno();

		Emp emp = companyService.findCommon(empno);

		model.addAttribute("emp", emp);

		return "html/adminIndex";
	}

	@RequestMapping(value = "adminList", method = RequestMethod.GET)
	public String adminList() {
		return "html/adminList";
	}
	
	@RequestMapping(value = "adminListView", method = { RequestMethod.GET, RequestMethod.POST })
	public String adminListView(HttpServletRequest request, Model model,CommonData commonData) {

		try {
			String pageNum = request.getParameter("pageNum"); // 바뀌는 페이지 번호
			int currentPage = 1; // 현재 페이지 번호 (default)
			String date = request.getParameter("date"); // 검색어
			LocalDateTime localDateTime = LocalDateTime.now();
			
			if (pageNum != null) {
				currentPage = Integer.parseInt(pageNum);
			}

			if (date == null || date == "") {
				date = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

			} else {
				if (request.getMethod().equalsIgnoreCase("GET")) {
					// GET 방식으로 request가 왔다면
					// 쿼리 파라미터의 값을 디코딩 해줌.
					date = URLDecoder.decode(date, "UTF-8");
				}
			}

			// 1. 전체 게시물의 갯수 가져온다. (페이징 처리를 위해)
			int dataCount = companyService.getDataCountForAdmin(date);

			// 2. 페이징 처리를 함. (준비)
			int numPerPage = 5; // 페이지당 보여줄 데이터의 갯수
			int totalPage = myUtil.getPageCount(numPerPage, dataCount); // 페이지의 전체 갯수를 구한다

			if (currentPage > totalPage) { // totalPage 보다 크면 안됨.
				currentPage = totalPage;
			}

			int start = (currentPage - 1) * numPerPage + 1; // 1 6 11 16 ...
			int end = currentPage * numPerPage; // 5 10 15 20 ...

			// 3. 전체 게시물 리스트를 가져온다.
			List<CommonData> lists = companyService.getListForAdmin(start, end, date);

			// 4. 페이징 처리
			String param = "";

			if (date != null && !date.equals("")) {
				// searchValue에 검색어가 있다면...
				param += "date=" + URLEncoder.encode(date, "UTF-8"); // 인코딩.
			}

			String listUrl = "/adminList";

			// list?searchKey=name&searchValue=어피치
			if (!param.equals("")) {
				listUrl += "?" + param;
			}

			// ◀이전 1 2 3 4 5 다음▶
			String pageIndexList = myUtil.pageIndexList(currentPage, totalPage, listUrl);

			model.addAttribute("lists", lists); // DB에서 가져온 전체 게시물
			model.addAttribute("pageIndexList", pageIndexList); // 하단 페이징 버튼
			model.addAttribute("dataCount", dataCount); // 게시물의 총 갯수

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "html/adminList";
	}
	
	@RequestMapping(value="adminDelete", method = {RequestMethod.GET, RequestMethod.POST})
	public String adminDelete(HttpServletRequest request, Model model) {
		
		try {
			String pageNum = request.getParameter("pageNum"); // 바뀌는 페이지 번호
			int currentPage = 1; // 현재 페이지 번호 (default)

			
			if (pageNum != null) {
				currentPage = Integer.parseInt(pageNum);
			}

			

			// 1. 전체 게시물의 갯수 가져온다. (페이징 처리를 위해)
			int dataCount = companyService.getDataCountForFire();

			// 2. 페이징 처리를 함. (준비)
			int numPerPage = 5; // 페이지당 보여줄 데이터의 갯수
			int totalPage = myUtil.getPageCount(numPerPage, dataCount); // 페이지의 전체 갯수를 구한다

			if (currentPage > totalPage) { // totalPage 보다 크면 안됨.
				currentPage = totalPage;
			}

			int start = (currentPage - 1) * numPerPage + 1; // 1 6 11 16 ...
			int end = currentPage * numPerPage; // 5 10 15 20 ...

			// 3. 전체 게시물 리스트를 가져온다.
			List<Emp> lists = companyService.getListForFire(start, end);

			// 4. 페이징 처리


			String listUrl = "/adminDelete";


			// ◀이전 1 2 3 4 5 다음▶
			String pageIndexList = myUtil.pageIndexList(currentPage, totalPage, listUrl);

			model.addAttribute("lists", lists); // DB에서 가져온 전체 게시물
			model.addAttribute("pageIndexList", pageIndexList); // 하단 페이징 버튼
			model.addAttribute("dataCount", dataCount); // 게시물의 총 갯수

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "html/adminDelete";
	}
	
	@RequestMapping(value="fire", method = RequestMethod.GET)
	public String fireEmployee(HttpServletRequest request, Model model) {
		int empno = Integer.parseInt(request.getParameter("empno"));
		
		companyService.deleteCommute(empno);
		companyService.deleteEmp(empno);
		
		model.addAttribute("message", "아아 그는 갔습니다.");
		
		
		return "redirect:/adminDelete";
	}
}
