package com.culticare.comments.controller.dto.response;

import com.culticare.comments.entity.Comments;
import com.culticare.posts.controller.dto.response.PostListResponseDto;
import com.culticare.posts.entity.Categories;
import com.culticare.posts.entity.Posts;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentsListResponseDto {

    private boolean nextPage;
    private List<CommentsListResponseDto.CommentDto> comments;

    @Data
    @Builder
    public static class CommentDto {
        private Long id;
        private String content;
        private Long likeCount;

        public static CommentsListResponseDto.CommentDto from(Comments comment, Posts post) {
            return CommentDto.builder()
                    .id(comment.getId())
                    .content(comment.getContent())
                    .likeCount(comment.getLikeCount())
                    .build();
        }
    }
}
