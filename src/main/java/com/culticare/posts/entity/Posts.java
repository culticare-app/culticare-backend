package com.culticare.posts.entity;

import com.culticare.BaseTimeEntity;
import com.culticare.posts.controller.dto.request.PostEditRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Posts extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    private String title;

    private String content;

    private Long likeCount;

    private Long view;

    private Long loginMemberId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Categories category;


    @Builder
    public Posts(String title, String content, Long likeCount, Long view, Long loginMemberId, Categories category) {
        this.title = title;
        this.content = content;
        this.likeCount = likeCount;
        this.view = view;
        this.loginMemberId = loginMemberId;
        this.category = category;
    }

    public void updatePost(PostEditRequestDto dto) {
        this.title = dto.getTitle();
        this.content = dto.getContent();
    }
}
