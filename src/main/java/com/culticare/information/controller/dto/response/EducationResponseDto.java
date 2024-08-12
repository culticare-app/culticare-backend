package com.culticare.information.controller.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EducationResponseDto {

    private Long id;
    private String title; // 프로그램명
    private String content; // 내용
    private String language; // 언어
    private LocalDateTime applyStartDate; // 신청시작일
    private LocalDateTime applyEndDate; // 신청마감일
    private LocalDateTime eduStartDate; // 교육시작일
    private LocalDateTime eduEndDate; // 교육마감일
    private String qualification; // 신청자격
    private int personnel; // 정원
    private boolean isScrapped; // 스크랩여부
}
