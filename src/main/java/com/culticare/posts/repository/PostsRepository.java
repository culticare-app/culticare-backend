package com.culticare.posts.repository;

import com.culticare.posts.entity.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<Posts, Long> {
}
