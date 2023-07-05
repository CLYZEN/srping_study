package com.sns.repository;

<<<<<<< HEAD
public interface MemberInterestsRepository {
=======
import com.sns.entity.Member;
import com.sns.entity.MemberInterests;
import com.sns.id.MemberInterestsId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberInterestsRepository extends JpaRepository<MemberInterests, Member> {
>>>>>>> 7e067f9231306e52db63786bfe35e0ca9900f777

}
