package com.culticare.posts.controller.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostEditRequestDto {

    private String title;
    private String content;
}
