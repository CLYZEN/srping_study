package com.shopmax.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.shopmax.constant.ItemSellStatus;
import com.shopmax.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Long>{ // <해당 repo에서 사용할 엔티티클래스명 , PK 타입>
	// select * from item where item_no = ?
	List<Item> findByItemNm(String itemNm);
	
	// select * from item where item_nm ? and item_sell_status = ?
	// 퀴즈 1-1
	List<Item> findByItemNmAndItemSellStatus(String itemNm, ItemSellStatus itemSellStatus);
	
	// 퀴즈 1-2
	List<Item> findByPriceBetween(int a, int b);
	
	// 퀴즈 1-3
	List<Item> findByRegTimeAfter(LocalDateTime date);
	
	// 퀴즈 1-4
	List<Item> findByItemSellStatusNotNull();
	
	// 퀴즈 1-5
	List<Item> findByItemDetailEndingWith(String param);
	
	// 퀴즈 1-6
	List<Item> findByItemNmOrItemDetail(String itemNm, String itemDetail);
	
	// 퀴즈 1-7
	List<Item> findByPriceBeforeOrderByPriceDesc(int price);
	
	// JPQL(non native query) -> 컬럼명, 테이블명은 엔티티클래스 기준
	@Query(value = "select i from Item i where i.itemDetail like %:itemDetail% order by i.price desc")
	List<Item> findByItemDetail(@Param("itemDetail") String itemDetail);
	
	// JPQL(native query) -> h2 DB 기준 쿼리작성
	@Query(value = "select * from item where item_detail like %:itemDetail% order by price desc", nativeQuery = true)
	List<Item> findByItemDetailByNative(@Param("itemDetail") String itemDetail);
	
	// 퀴즈 2-1
	@Query("select i from Item i where i.price >= :price")
	List<Item> findByPirceOver(@Param("price") int price);
	
	// 퀴즈 2-2
	@Query("select i from Item i where i.itemNm = :itemName and i.itemSellStatus = :itemSellStatus")
	List<Item> findItemNmStatus(@Param("itemName") String itemName, @Param("itemSellStatus") ItemSellStatus itemSellStatus);
}
