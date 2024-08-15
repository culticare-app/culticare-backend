package com.culticare.posts.repository;

import com.culticare.posts.entity.Posts;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostsCustomRepository {
    public List<Posts> findAll(Pageable pageable);
    public List<Posts> findPostsByCategoryWithPaging(String category, Pageable pageable);
}
