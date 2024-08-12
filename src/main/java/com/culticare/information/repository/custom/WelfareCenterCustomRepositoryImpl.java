package com.culticare.information.repository.custom;

import com.culticare.information.entity.Education;
import com.culticare.information.entity.WelfareCenter;
import com.culticare.member.entity.Member;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.criteria.JpaQueryableCriteria;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.culticare.information.entity.QEducation.education;
import static com.culticare.information.entity.QWelfareCenter.welfareCenter;
import static com.culticare.member_scrap_info.entity.QMemberScrapInfo.memberScrapInfo;

@Repository
@RequiredArgsConstructor
public class WelfareCenterCustomRepositoryImpl implements WelfareCenterCustomRepository{

    private final JPAQueryFactory queryFactory;

    @Override
    public List<WelfareCenter> findAll(Pageable pageable) {

        List<WelfareCenter> results = queryFactory
                .select(welfareCenter)
                .from(welfareCenter)
                .orderBy(welfareCenter.id.desc())
                .limit(pageable.getPageSize()+1)
                .fetch();

        return results;
    }

    @Override
    public List<WelfareCenter> findScrappedWelfareCenter(Member member, Pageable pageable) {

        List<WelfareCenter> results = queryFactory
                .select(welfareCenter)
                .from(welfareCenter)
                .join(memberScrapInfo)
                .on(welfareCenter.id.eq(memberScrapInfo.information.id))
                .where(memberScrapInfo.member.eq(member))
                .orderBy(welfareCenter.id.desc())
                .limit(pageable.getPageSize()+1)
                .fetch();

        return results;
    }
}
