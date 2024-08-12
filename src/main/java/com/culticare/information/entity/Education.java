package com.culticare.information.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("education")
@Entity
public class Education extends Information {

    private String title; // 프로그램명
    private String content; // 내용
    private String language; // 언어
    private LocalDateTime applyStartDate; // 신청시작일
    private LocalDateTime applyEndDate; // 신청마감일
    private LocalDateTime eduStartDate; // 교육시작일
    private LocalDateTime eduEndDate; // 교육마감일
    private String qualification; // 신청자격
    private int personnel; // 정원


}
