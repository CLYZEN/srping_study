package com.sns.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sns.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long>{
	
}
