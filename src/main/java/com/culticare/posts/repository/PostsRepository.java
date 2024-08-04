package com.culticare.posts.repository;

import com.culticare.posts.entity.Categories;
import com.culticare.posts.entity.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts, Long> {
    List<Posts> findByCategory(Categories category);
}
