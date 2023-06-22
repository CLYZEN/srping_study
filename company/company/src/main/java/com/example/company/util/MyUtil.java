package com.example.company.util;

import org.springframework.stereotype.Service;

@Service
public class MyUtil {
	// 전체 페이지 갯수 구하기.
	public int getPageCount(int numPerPage, int dataCount) {
		int pageCount = 0;
		pageCount = dataCount / numPerPage;

		if (dataCount % numPerPage != 0) {
			pageCount++;
		}
		return pageCount;
	}

	public String pageIndexList(int currentPage, int totalPage, String listUrl) {
		StringBuffer sb = new StringBuffer(); // 문자열 데이터의 수정이 빈번한 경우 메모리 낭비 방지
		int numPerBlock = 5; // 이전 1 2 3 4 5 다음 (이전 다음 사이 숫자 표시 갯수)
		int currentPageSetup; // 이전에 들어갈 값
		int page; // 일반적인 페이지 숫자를 클릭했을때 들어갈 값

		if (currentPage == 0 || totalPage == 0) { // 데이터가 없다
			return "";
		}

		// 검색어가 있을 때 : list?searchKey=name&searchValue=어피치
		if (listUrl.indexOf("?") != -1) {
			// '?' 가 들어있다면 (쿼리스트링이 있을 때)
			listUrl += "&";
		} else {
			// 쿼리스트링이 없을 때
			listUrl += "?";
		}

		// 1. 이전 버튼 만들기

		// currentPage 가(1~4), (5~9), (10~14), (15~19) ... 일 때 currentPageSetup : 0 5 10
		// 15 ...
		currentPageSetup = (currentPage / numPerBlock) * numPerBlock;

		// currentPage 가 5의 배수일 때 currentPageSetup 0, 5, 10, 15...
		if (currentPage % numPerBlock == 0) {
			currentPageSetup = currentPageSetup - numPerBlock;
		}

		if (totalPage > numPerBlock && currentPageSetup > 0) {
			sb.append("<a href=\"" + listUrl + "pageNum=" + currentPageSetup + "\">◀이전</a>&nbsp;");
		}

		// 2. 페이지 (1 2 3 4 5) 버튼 만들기
		page = currentPageSetup + 1; // 1 6 11 16 ...

		while (page <= totalPage && page <= (currentPageSetup + numPerBlock)) {
			if (page == currentPage) {
				// 현재 내가 선택한 페이지
				sb.append("<font color=\"red\">" + page + "</font>&nbsp;");
				// <font color = "red"> 9 </font>
			} else {
				// 현재 내가 선택한 페이지가 아닐 때
				sb.append("<a href=\"" + listUrl + "pageNum=" + page + "\">" + page + "</a>&nbsp;");
				// <a href="list?pageNum=7">7</a>&nbsp;
			}
			page++;
		}
		// 3. 다음 버튼 만들기
		if (totalPage - currentPageSetup > numPerBlock) {
			sb.append("<a href=\"" + listUrl + "pageNum=" + currentPageSetup + "\">다음▶</a>&nbsp;");
		}

		// 4. 버튼 합쳐서 문자열로 리턴
		// System.out.println(sb.toString());
		return sb.toString();
	}
}
