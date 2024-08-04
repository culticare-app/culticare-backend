package com.culticare.posts.entity;

import com.culticare.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class HashTag extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private Long id;

    private String name;

    private Long count;

    @OneToMany(mappedBy = "hashTag", cascade = CascadeType.ALL)
    private List<PostsHashtagMap> postsHashtagMaps = new ArrayList<>();

    @Builder
    public HashTag(String name, Long count) {
        this.name = name;
        this.count = count;
    }

    public void plusCount() {
        this.count += 1;
    }
}
