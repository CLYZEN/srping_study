package com.shopmax.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shopmax.entity.Member;
import com.shopmax.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional // 쿼리문 수행 시 에러가 발생하면 rollback 수행
@RequiredArgsConstructor // @Autowired 사용하지 않고 의존성 주입
public class MemberService implements UserDetailsService {
	
	private final MemberRepository memberRepository;
	
	// 회원가입 데이터를 DB에 저장한다.
	public Member saveMember(Member member) {
		validateDuplicateMember(member);
		
		Member savedMember = memberRepository.save(member); // insert
		return savedMember; // 회원가입된 데이터를 리턴
	}
	
	// 이메일 중복체크
	private void validateDuplicateMember(Member member) {
		Member findMember = memberRepository.findByEmail(member.getEmail());
		
		if(findMember != null) {
			throw new IllegalStateException("이미 가입된 회원입니다.");
		}
		
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// 사용자가 입력한 email로 DB에 있는지 쿼리문 사용
		Member member = memberRepository.findByEmail(email);
		
		if(member == null) { // 사용자가 없다면
			throw new UsernameNotFoundException(email);
		}
		
		// 사용자가 있다면 DB에서 가져온 값으로 userDetails 객체를 만들어서 반환
		return User.builder()
				.username(member.getEmail())
				.password(member.getPassword())
				.roles(member.getRole().toString())
				.build();
	}
	
}
