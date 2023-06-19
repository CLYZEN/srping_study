package com.example.board2.controller;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.board2.dto.Board;
import com.example.board2.service.BoardService;
import com.example.board2.util.MyUtil;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	@Autowired
	MyUtil myUtil;
	
	
	
	@RequestMapping(value = "/")
	public String index() {
		
		return "/index";
	}
	
	@RequestMapping(value = "/created", method = RequestMethod.GET) // Get 방식으로 request가 들어올 때 created() 실행
	public String created() {
		
		return "/bbs/created";
	}
	
	// 게시물 등록
	@RequestMapping(value = "/created", method = RequestMethod.POST) // Get 방식으로 request가 들어올 때 created() 실행
	public String createdOK(Board board, HttpServletRequest request, Model model) {
		
		try {
			int maxNum = boardService.maxNum();
			board.setNum(maxNum + 1);
			board.setIpAddr(request.getRemoteAddr());
			
			boardService.insertData(board);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/list";
	}
	
	// 리스트 페이지 보여줌(Get , Post 전부 받음.)
	@RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST}) 
	public String list(Board board, HttpServletRequest request, Model model) {
		
		try {
			String pageNum = request.getParameter("pageNum"); // 바뀌는 페이지 번호
			int currentPage = 1; // 현재 페이지 번호 (default)
			String searchKey = request.getParameter("searchKey"); // 검색 키워드
			String searchValue = request.getParameter("searchValue"); // 검색어
			
			if(pageNum != null) {
				currentPage = Integer.parseInt(pageNum);
			}
			
			if(searchValue == null) {
				searchKey = "subject"; // 검색 키워드의 default는 subject
				searchValue = ""; // 검색어는 빈문자열
			} else {
				if(request.getMethod().equalsIgnoreCase("GET")) {
					// GET 방식으로 request가 왔다면
					// 쿼리 파라미터의 값을 디코딩 해줌.
					searchValue = URLDecoder.decode(searchValue, "UTF-8");
				}
			}
			
			// 1. 전체 게시물의 갯수 가져온다. (페이징 처리를 위해)
			int dataCount = boardService.getDataCount(searchKey, searchValue);
			
			// 2. 페이징 처리를 함. (준비)
			int numPerPage = 5; // 페이지당 보여줄 데이터의 갯수
			int totalPage = myUtil.getPageCount(numPerPage, dataCount); // 페이지의 전체 갯수를 구한다
			
			if(currentPage > totalPage) { // totalPage 보다 크면 안됨.
				currentPage = totalPage;
			}
			
			int start = (currentPage -1) * numPerPage + 1; // 1 6 11 16 ...
			int end = currentPage * numPerPage; // 5 10 15 20 ...
			
			// 3. 전체 게시물 리스트를 가져온다.
			List<Board> lists = boardService.getLists(searchKey, searchValue, start, end);
			
			// 4. 페이징 처리
			String param = "";
			
			if (searchValue != null && searchValue.equals("")) {
				// searchValue에 검색어가 있다면...
				param = "searchKey=" + searchKey;
				param += "&searchValue" + URLEncoder.encode(searchValue, "UTF-8"); // 인코딩.
			}
			
			String listUrl = "/list";
			
			// list?searchKey=name&searchValue=어피치
			if (!param.equals("")) {
				listUrl += "?" + param;
			}
			
			String pageIndexList = myUtil.pageIndexList(currentPage, totalPage, listUrl);
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		return "/bbs/list";
	}
	
	
	@RequestMapping(value = "/article", method = RequestMethod.GET) 
	public String article() {
		
		return "/bbs/article";
	}
	
	
	// 수정 페이지 보여주기
	@RequestMapping(value = "/updated", method = RequestMethod.GET) 
	public String updated(HttpServletRequest request, Model model) {
		
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		String searchKey = request.getParameter("searchKey");
		String searchValue = request.getParameter("searchValue");
		
		if (searchValue != null) {
			
		}
		
		return "/bbs/updated";
	}
	
	// 수정하는 기능
	@RequestMapping(value = "/updated_ok", method = RequestMethod.POST) 
	public String updatedOK() {
		
		return "/bbs/updated";
	}
}