package com.culticare.posts.controller.dto.response;

import com.culticare.posts.entity.Categories;
import com.culticare.posts.entity.Posts;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostListResponseDto {

    private boolean nextPage;
    private List<PostDto> posts;

    @Data
    @Builder
    public static class PostDto {
        private Long id;
        private String title;
        private String content;
        private Long likeCount;
        private Long view;
        private String category;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;
        private String writerName;
        private String tags;

        public static PostDto from(Posts post, Categories category) {
            return PostDto.builder()
                    .id(post.getId())
                    .title(post.getTitle())
                    .content(post.getContent())
                    .likeCount(post.getLikeCount())
                    .view(post.getView())
                    .category(post.getCategory().getName())
                    .createdAt(post.getCreatedAt())
                    .modifiedAt(post.getModifiedAt())
                    .writerName(post.getWriterName())
                    .build();
        }
    }
}
