package com.culticare.posts.entity;

import com.culticare.BaseTimeEntity;
import com.fasterxml.jackson.databind.ser.Serializers;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Tags extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private Long id;

    private String name;

    private Long count;

    @Builder
    public Tags(String name, Long count) {
        this.name = name;
        this.count = count;
    }

    public void plusCount() {
        this.count += 1;
    }
}
