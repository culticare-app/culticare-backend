package com.culticare.diary.controller.dto.response;

import com.culticare.diary.entity.Diary;
import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class diaryResponseDto {
    private Long id;
    private String content;
    private Long memberId;
    private LocalDateTime createdAt;

    private int depressionPercent;

    @Builder
    public diaryResponseDto(Diary entity) {
        this.id = entity.getId();
        this.content = entity.getContent();
        this.memberId = entity.getMember().getId();
        this.createdAt = entity.getCreatedAt();
        this.depressionPercent = entity.getDepressionPercent();
    }
}
