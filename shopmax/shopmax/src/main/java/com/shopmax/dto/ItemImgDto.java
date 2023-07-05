package com.shopmax.dto;

import org.modelmapper.ModelMapper;

import com.shopmax.entity.ItemImg;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemImgDto {
	private Long id;
	
	private String imgName; // 바뀐 이미지 파일명 (중복방지)
	
	private String oriImgName; // 원본 이미지 파일명
	
	private String imgUrl; // 이미지 조회 경로
	
	private String repImgYn;
	
	private static ModelMapper modelMapper = new ModelMapper();
	
	// entity -> dto로 변환
	public static ItemImgDto of(ItemImg itemImg) {
		return modelMapper.map(itemImg, ItemImgDto.class);
	}
}
