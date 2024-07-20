package com.culticare.comments.repository;

import com.culticare.comments.entity.Comments;
import com.culticare.posts.entity.Posts;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentsRepository extends JpaRepository<Comments, Long> {

}
