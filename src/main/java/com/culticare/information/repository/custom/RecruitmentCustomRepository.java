package com.culticare.information.repository.custom;

import com.culticare.information.entity.Recruitment;
import com.culticare.member.entity.Member;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RecruitmentCustomRepository {

    public List<Recruitment> findAll(Pageable pageable);
    public List<Recruitment> findScrappedRecruitment(Member member, Pageable pageable);
}
