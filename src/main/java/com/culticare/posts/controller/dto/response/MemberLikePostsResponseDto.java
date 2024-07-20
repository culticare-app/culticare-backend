package com.culticare.posts.controller.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberLikePostsResponseDto {

    private Long id;
    private Long postId;
    private String title;
    private String content;
    private String category;
    private Long view;
    private Long likeCount;
    private String writerName;

}
