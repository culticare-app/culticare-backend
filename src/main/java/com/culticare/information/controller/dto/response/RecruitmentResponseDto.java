package com.culticare.information.controller.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecruitmentResponseDto {

    private Long id;
    private String title; //제목
    private String facility; //작성시설
    private String language; //언어
    private String countryOfOrigin; //출신국가
    private String workingArea; //근무지역
    private String workingDays; //근무일수
    private String startTime; //근무시간 시작
    private String endTime; //근무시간 종료
    private String weekendWorkStatus; //주말근무여부
    private String wage; //임금
    private String recruitNumber; //모집인원
    private LocalDateTime postDate; //등록일
    private String workType; // 고용형태
    private String workWelfare; //복리후생
    private boolean isScrapped; // 스크랩 여부
}
