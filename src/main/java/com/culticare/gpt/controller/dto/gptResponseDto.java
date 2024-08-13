package com.culticare.gpt.controller.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class gptResponseDto {

    private String answer;

    public gptResponseDto(String content) {
        this.answer = content;
    }
}
