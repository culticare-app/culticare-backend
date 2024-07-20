package com.culticare.comments.controller.dto.request;

import com.culticare.comments.entity.Comments;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentCreateRequestDto {

    private String content;
}
