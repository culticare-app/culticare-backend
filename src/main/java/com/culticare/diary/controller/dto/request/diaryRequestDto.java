package com.culticare.diary.controller.dto.request;

import com.culticare.diary.entity.Diary;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class diaryRequestDto {
    private String content;


    @Builder
    public diaryRequestDto(String content) {
        this.content = content;
    }


    public Diary toEntity(Long memberId){
        return Diary.builder()
                .content(content)
                .memberId(memberId)
                .build();
    }
}
