package com.shopmax.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.shopmax.dto.ItemFormDto;
import com.shopmax.service.ItemService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ItemController {
	
	private final ItemService itemService;
	
	// 상품전체 리스트
	@GetMapping(value = "/item/shop")
	public String itemShopList() {
		return "item/itemShopList";
	}
	
	// 관리자메뉴 -> 상품등록
	@GetMapping(value = "/admin/item/new")
	public String itemForm(Model model) {
		model.addAttribute("itemFormDto", new ItemFormDto());

		return "item/itemForm";
	}
	
	// 상품, 상품 이미지 등록 (insert)
	@PostMapping(value = "/admin/item/new")
	public String itemNew(@Valid ItemFormDto itemFormDto, BindingResult bindingResult, Model model, @RequestParam("itemImgFile") List<MultipartFile> itemImgFileList ) {
		
		if(bindingResult.hasErrors()) {
			return "item/itemForm";
		}
		
		// 상품 등록 전 첫번째 이미지 검사 (필수입력값 존재하는지)
		if(itemImgFileList.get(0).isEmpty()) {
			model.addAttribute("errorMessage", "첫번째 상품 이미지는 필수입니다.");
			return "item/itemForm";
		}
		
		try {
			itemService.saveItem(itemFormDto, itemImgFileList);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMessage", "상품 등록 중 에러가 발생했습니다");
			return "item/itemForm";
		}
		
		return "redirect:/";
	}
	
	// 관리자메뉴 -> 상품관리
	@GetMapping(value = "/admin/items")
	public String itemManage() {
		return "item/itemMng";
	}
	
}
