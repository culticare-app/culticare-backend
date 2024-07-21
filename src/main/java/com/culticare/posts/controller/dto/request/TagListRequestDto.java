package com.culticare.posts.controller.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TagListRequestDto {
    private String name;

    public static TagListRequestDto of(String name) {
        return new TagListRequestDto(name);
    }
}
