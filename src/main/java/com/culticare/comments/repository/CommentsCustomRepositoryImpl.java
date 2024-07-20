package com.culticare.comments.repository;

import com.culticare.comments.entity.Comments;
import com.culticare.posts.entity.Posts;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.culticare.comments.entity.QComments.comments;
import static com.culticare.posts.entity.QPosts.posts;

@Repository
@RequiredArgsConstructor
public class CommentsCustomRepositoryImpl implements CommentsCustomRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Comments> findCommentsByPostWithPaging(Posts post, Pageable pageable) {

        List<Comments> results = queryFactory
                .select(comments)
                .from(comments)
                .where(
                        comments.post.eq(post)
                )
                .orderBy(comments.id.desc())
                .limit(pageable.getPageSize()+1)
                .fetch();

        return results;
    }
}
