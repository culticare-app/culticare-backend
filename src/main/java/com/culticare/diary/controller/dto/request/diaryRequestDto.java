package com.culticare.diary.controller.dto.request;

import com.culticare.diary.entity.Diary;
import com.culticare.member.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class diaryRequestDto {
    private String content;
    private int depressionPercent;


    @Builder
    public diaryRequestDto(String content, int depressionPercent) {
        this.content = content;
        this.depressionPercent = depressionPercent;
    }


    public Diary toEntity(Member member){
        return Diary.builder()
                .content(content)
                .member(member)
                .depressionPercent(depressionPercent)
                .build();
    }
}
