package com.sns.entity;

import com.sns.converter.BooleanToYNConverter;

import com.sns.id.MemberInterestsId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "member_interests")
public class MemberInterests extends BaseTimeEntity {

	@EmbeddedId
	private MemberInterestsId memberId;

	@MapsId("memberId")
	@ManyToOne
	@JoinColumn(name = "member_id", insertable = false, updatable = false)
	public Member member;

	@Convert(converter = BooleanToYNConverter.class)
	@Column(nullable = false)
	private boolean develop; // 개발

	@Convert(converter = BooleanToYNConverter.class)
	@Column(nullable = false)
	private boolean travel; // 여행

	@Convert(converter = BooleanToYNConverter.class)
	@Column(nullable = false)
	private boolean animal; // 동물

	@Convert(converter = BooleanToYNConverter.class)
	@Column(nullable = false)
	private boolean life; // 일상

	@Convert(converter = BooleanToYNConverter.class)
	@Column(nullable = false)
	private boolean food; // 음식
}