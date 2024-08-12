package com.culticare.information.controller.dto.response;

import com.culticare.information.entity.Recruitment;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecruitmentListResponseDto {

    private boolean nextPage;
    private List<RecruitmentDto> recruitments;

    @Data
    @Builder
    public static class RecruitmentDto {

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

        public static RecruitmentDto from(Recruitment recruitment) {

            return RecruitmentDto.builder()
                    .id(recruitment.getId())
                    .title(recruitment.getTitle())
                    .facility(recruitment.getFacility())
                    .language(recruitment.getLanguage())
                    .countryOfOrigin(recruitment.getCountryOfOrigin())
                    .workingArea(recruitment.getWorkingArea())
                    .workingDays(recruitment.getWorkingDays())
                    .startTime(recruitment.getStartTime())
                    .endTime(recruitment.getEndTime())
                    .weekendWorkStatus(recruitment.getWeekendWorkStatus())
                    .wage(recruitment.getWage())
                    .recruitNumber(recruitment.getRecruitNumber())
                    .postDate(recruitment.getPostDate())
                    .workType(recruitment.getWorkType())
                    .workWelfare(recruitment.getWorkWelfare())
                    .build();
        }
    }
}
