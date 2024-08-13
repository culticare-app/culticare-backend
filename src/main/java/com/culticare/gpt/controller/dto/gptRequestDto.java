package com.culticare.gpt.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Data
public class gptRequestDto {
    private String msg;

    public gptRequestDto(String msg) {
        this.msg = msg;
    }
}
