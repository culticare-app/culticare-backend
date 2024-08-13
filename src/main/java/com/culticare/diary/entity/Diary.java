package com.culticare.diary.entity;

import com.culticare.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Diary extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "diary_id")
    private Long id;

    @Column(name = "content",columnDefinition = "TEXT",nullable = false)
    private String content;

    private Long memberId;

    @Builder
    public Diary(String content, Long memberId) {
        this.content = content;
        this.memberId = memberId;
    }

}
