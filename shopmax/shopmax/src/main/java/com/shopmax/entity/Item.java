package com.shopmax.entity;

import java.time.LocalDateTime;

import com.shopmax.constant.ItemSellStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity // 엔티티 클래스로 정의
@Table(name="item") // 테이블 이름 지정
@ToString
@Getter
@Setter
public class Item {
	
	@Id
	@Column(name = "item_id") // 테이블로 생성될 때 컬럼이름을 지정해준다.
	@GeneratedValue(strategy = GenerationType.AUTO) // mysql의 autoincrement 기본키 자동생성 전략
	private Long id; // 상품코드
	@Column(nullable = false, length = 50) // not null , varchar(50)
	private String itemNm; // 상품명
	@Column(nullable = false)
	private Integer price; // 상품가격
	@Column(nullable = false)
	private Integer stockNumber; // 재고수량
	@Lob // String 인데 긴 글이 들어가야 할 때 (longtext)
	@Column(nullable = false)
	private String itemDetail; // 상품상세설명
	@Enumerated(EnumType.STRING) // DB에 enum의 값 자체를 저장
	private ItemSellStatus itemSellStatus; // 판매상태 : SELL, SOLD_OUT
	private LocalDateTime regTime; // 등록시간
	private LocalDateTime updateTime; // 수정시간
	
}
