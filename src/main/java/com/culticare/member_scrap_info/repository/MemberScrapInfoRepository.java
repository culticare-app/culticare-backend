package com.culticare.member_scrap_info.repository;

import com.culticare.information.entity.Information;
import com.culticare.member.entity.Member;
import com.culticare.member_scrap_info.entity.MemberScrapInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberScrapInfoRepository extends JpaRepository<MemberScrapInfo, Long> {


    boolean existsByMemberAndInformation(Member member, Information information);
    void deleteByMemberAndInformation(Member member, Information information);

    List<MemberScrapInfo> findByMember(Member member);
}
