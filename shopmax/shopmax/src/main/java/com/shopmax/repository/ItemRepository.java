package com.shopmax.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopmax.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Long>{ // <해당 repo에서 사용할 엔티티클래스명 , PK 타입>

}
