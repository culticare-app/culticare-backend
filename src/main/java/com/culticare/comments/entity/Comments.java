package com.culticare.comments.entity;

import com.culticare.BaseTimeEntity;
import com.culticare.member.entity.Member;
import com.culticare.posts.entity.Posts;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Comments extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    private String content;

    private Long likeCount;

//    private Long loginMemberId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Posts post;

    @Builder
    public Comments(String content, Long likeCount, Member member, Posts post) {

        this.content = content;
        this.likeCount = likeCount;
        this.member = member;
        this.post = post;
    }

    public void updateComment(String content) {
        this.content = content;
    }
}
