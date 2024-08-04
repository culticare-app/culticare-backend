package com.culticare.posts.controller.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostCreateResponseDto {
    private String title;
    private String content;
    private Long likeCount;
    private Long view;
    private String category;
    private boolean isLiked;
    private String writerName;
    private String tags;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
