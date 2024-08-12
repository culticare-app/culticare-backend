package com.culticare.information.repository.custom;

import com.culticare.information.entity.WelfareCenter;
import com.culticare.member.entity.Member;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface WelfareCenterCustomRepository {

    public List<WelfareCenter> findAll(Pageable pageable);
    public List<WelfareCenter> findScrappedWelfareCenter(Member member, Pageable pageable);
}
