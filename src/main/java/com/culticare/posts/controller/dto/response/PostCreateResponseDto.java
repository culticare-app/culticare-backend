package com.culticare.posts.controller.dto.response;

import lombok.*;

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
}
