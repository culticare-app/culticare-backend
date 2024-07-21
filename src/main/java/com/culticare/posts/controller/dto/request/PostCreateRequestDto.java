package com.culticare.posts.controller.dto.request;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

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
    private String tags;

    public List<TagListRequestDto> getTagList() {
        List<TagListRequestDto> tagList = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(tags.replaceAll(" ",""), "#");

        while (st.hasMoreTokens()) {
            String token = st.nextToken();

            if (!token.isEmpty()) {
                tagList.add(TagListRequestDto.of(token));
            }
        }

        return tagList;
    }
}
