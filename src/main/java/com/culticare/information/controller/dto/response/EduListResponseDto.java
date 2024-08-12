package com.culticare.information.controller.dto.response;

import com.culticare.information.entity.Education;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EduListResponseDto {

    private boolean nextPage;
    private List<EducationDto> educations;

    @Data
    @Builder
    public static class EducationDto {
        private Long id;
        private String typeName; //정보종류명
        private String title; // 프로그램명
        private String content; // 내용
        private String language; // 언어
        private LocalDateTime applyStartDate; // 신청시작일
        private LocalDateTime applyEndDate; // 신청마감일
        private LocalDateTime eduStartDate; // 교육시작일
        private LocalDateTime eduEndDate; // 교육마감일
        private String qualification; // 신청자격
        private int personnel; // 정원

        public static EducationDto from(Education education) {
            return EducationDto.builder()
                    .id(education.getId())
                    .typeName(education.getTypeName())
                    .title(education.getTitle())
                    .content(education.getContent())
                    .language(education.getLanguage())
                    .applyStartDate(education.getApplyStartDate())
                    .applyEndDate(education.getApplyEndDate())
                    .eduStartDate(education.getEduStartDate())
                    .eduEndDate(education.getEduEndDate())
                    .qualification(education.getQualification())
                    .personnel(education.getPersonnel())
                    .build();
        }
    }
}
