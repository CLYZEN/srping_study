package com.sns.id;

import java.io.Serializable;

import com.sns.entity.Member;
import com.sns.entity.MemberInterests;
import groovy.transform.EqualsAndHashCode;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@EqualsAndHashCode
@Getter
@Setter
@Embeddable
public class MemberInterestsId implements Serializable{

	private long memberId;

	@Column(name = "member_id")
	private Long id;

}
