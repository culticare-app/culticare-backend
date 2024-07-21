package com.culticare.posts.controller.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostResponseDto {
    private Long id;
    private String title;
    private String content;
    private Long likeCount;
    private Long view;
    private String category;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private String writerName;
}
