package com.shopmax.entity;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.shopmax.constant.Role;
import com.shopmax.dto.MemberFormDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name= "member")
@Getter
@Setter
@ToString
public class Member {
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;
	
	@Column(unique = true)
	private String email;
	
	private String password;
	
	private String address;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	public static Member createMember(MemberFormDto memberFromDto, PasswordEncoder passwordEncoder) {
		// 패스워드 암호화
		String password = passwordEncoder.encode(memberFromDto.getPassword());
		
		// MemberFromDto 를 Member 엔티티 객체로 변환
		Member member = new Member();
		member.setName(memberFromDto.getName());
		member.setEmail(memberFromDto.getEmail());
		member.setAddress(memberFromDto.getAddress());
		member.setPassword(password);
		// member.setRole(Role.ADMIN); // 관리자로 가입
		member.setRole(Role.USER); // 일반 사용자
				
		return member;
	}
	
}
