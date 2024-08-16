package com.culticare.diary.controller.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AnalyzeResponseDto {

    private int average_depression_percent;


    @Builder
    public AnalyzeResponseDto(int averageDepressionPercent) {
        this.average_depression_percent = averageDepressionPercent;
    }
}
