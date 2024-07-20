package com.culticare.posts.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.aspectj.apache.bcel.generic.Tag;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class PostsHashtagMap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "posts_tag_map_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Posts post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_id")
    private HashTag hashTag;

    @Builder
    public PostsHashtagMap(Posts post, HashTag hashTag) {
        this.post = post;
        this.hashTag = hashTag;
    }
}
