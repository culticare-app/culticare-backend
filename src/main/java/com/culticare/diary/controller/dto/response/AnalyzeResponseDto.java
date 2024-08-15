package com.culticare.diary.controller.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AnalyzeResponseDto {

    private int averageDepressionPercent;


    @Builder
    public AnalyzeResponseDto(int averageDepressionPercent) {
        this.averageDepressionPercent = averageDepressionPercent;
    }
}
