package com.example.board2.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.board2.dto.itemDto;


@Controller // 1. controller역할 클래스 , 2. bean객체가 된다
@RequestMapping(value = "/thymeleaf")
public class thymeleafExController {
	
	@GetMapping(value = "/ex01")
	public String thymeleafExample01(Model model) {
		// request.setAttribute("key", value);
		model.addAttribute("data", "타임리프 예제입니다.");
		
		return "thymeleafEx/thymeleafEx01";
	}
	
	@GetMapping(value = "/ex02")
	public String thymeleafExample02(Model model) {
		itemDto itemDto = new itemDto();
		itemDto.setItemDetail("상품 상세 설명");
		itemDto.setItemNm("테스트 상품1");
		itemDto.setPrice(10000);
		itemDto.setRegTime(LocalDateTime.now());
		
		model.addAttribute("itemDto", itemDto);
		
		return "thymeleafEx/thymeleafEx02";
	}
	
	@GetMapping(value = "/ex03")
	public String thymeleafExample03(Model model) {
		List<itemDto> itemDtoList = new ArrayList<>();
		
		for(int i=0; i<10; i++) {
			itemDto itemDto = new itemDto();
			itemDto.setItemDetail("상품 상세 설명" + i);
			itemDto.setItemNm("테스트 상품" + i);
			itemDto.setPrice(10000 * i);
			itemDto.setRegTime(LocalDateTime.now());
			
			itemDtoList.add(itemDto);
		}
		model.addAttribute("itemDtoList", itemDtoList);
		
		return "thymeleafEx/thymeleafEx03";
	}
	
	@GetMapping(value = "/ex04")
	public String thymeleafExample04(Model model) {
		List<itemDto> itemDtoList = new ArrayList<>();
		
		for(int i=1; i<=10; i++) {
			itemDto itemDto = new itemDto();
			itemDto.setItemDetail("상품 상세 설명" + i);
			itemDto.setItemNm("테스트 상품" + i);
			itemDto.setPrice(10000 * i);
			itemDto.setRegTime(LocalDateTime.now());
			
			itemDtoList.add(itemDto);
		}
		model.addAttribute("itemDtoList", itemDtoList);
		
		return "thymeleafEx/thymeleafEx04";
	}
	
	@GetMapping(value = "/ex05")
	public String thymeleafExample05() {
		
		return "thymeleafEx/thymeleafEx05";
	}
	
	@GetMapping(value = "/ex06")
	public String thymeleafExample06(String param1, String param2, Model model) {
		model.addAttribute("param1", param1);
		model.addAttribute("param2", param2);
		
		return "thymeleafEx/thymeleafEx06";
	}
	
	@GetMapping(value = "/ex07")
	public String thymeleafExample07() {
		
		return "thymeleafEx/thymeleafEx07";
	}
}