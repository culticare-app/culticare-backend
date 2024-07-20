package com.culticare.comments.controller.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentEditRequestDto {
    private String content;
}
