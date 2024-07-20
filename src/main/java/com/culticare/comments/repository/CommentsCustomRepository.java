package com.culticare.comments.repository;

import com.culticare.comments.entity.Comments;
import com.culticare.posts.entity.Posts;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CommentsCustomRepository {
    public List<Comments> findCommentsByPostWithPaging(Posts post, Pageable pageable);
}
