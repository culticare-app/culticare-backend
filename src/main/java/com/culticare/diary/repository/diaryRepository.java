package com.culticare.diary.repository;

import com.culticare.diary.entity.Diary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface diaryRepository extends JpaRepository<Diary,Long> {
    @Query("select d from Diary d " +
            "where FUNCTION('YEAR', d.createdAt) = :year " +
            "and FUNCTION('MONTH', d.createdAt) = :month " +
            "and FUNCTION('DAY', d.createdAt) = :day " +
            "and d.memberId = :memberId")
    List<Diary> findByDate(@Param("year") int year,
                           @Param("month") int month,
                           @Param("day") int day,
                           @Param("memberId") Long memberId);

    @Query("select d from Diary d where d.memberId = :memberId")
    List<Diary> findByMemberId(@Param("memberId") Long memberId);
}
