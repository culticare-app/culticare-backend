package com.culticare.posts.controller.dto.request;

import com.culticare.posts.entity.Categories;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostCreateRequestDto {
    private String title;
    private String content;
    private Long likeCount;
    private Long view;
    private String memberId;
    private String category;
    private String writerName;
}
