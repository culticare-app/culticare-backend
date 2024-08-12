package com.culticare.posts.entity;

import com.culticare.BaseTimeEntity;
import com.culticare.member.entity.Member;
import com.culticare.posts.controller.dto.request.PostEditRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.List;

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

    //private String loginMemberId;

    private String writerName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Categories category;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<PostsHashtagMap> postsHashtagMaps = new ArrayList<>();


    @Builder
    public Posts(String title, String content, Long likeCount, Long view, Member member, Categories category, String writerName) {
        this.title = title;
        this.content = content;
        this.likeCount = likeCount;
        this.view = view;
        //this.loginMemberId = loginMemberId;
        this.member = member;
        this.category = category;
        this.writerName = writerName;
    }

    public void updatePost(PostEditRequestDto dto) {
        this.title = dto.getTitle();
        this.content = dto.getContent();
    }

    public void plusLikeCount() {
        this.likeCount += 1;
    }

    public void minusLikeCount() {
        this.likeCount -= 1;
    }

    public void countUpView() {
        this.view += 1;
    }
}
