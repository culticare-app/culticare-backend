package com.culticare.information.entity;

import com.fasterxml.jackson.datatype.jsr310.deser.key.LocalDateKeyDeserializer;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("recruitment")
@Entity
public class Recruitment extends Information{

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
}
