package com.culticare.posts.repository;

import com.culticare.posts.entity.Posts;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.culticare.posts.entity.QPosts.posts;

@Repository
@RequiredArgsConstructor
public class PostsCustomRepositoryImpl implements PostsCustomRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Posts> findAll(Pageable pageable) {
        List<Posts> results = queryFactory
                .select(posts)
                .from(posts)
                .orderBy(posts.id.desc())
                .limit(pageable.getPageSize()+1)
                .fetch();

        return results;
    }

    @Override
    public List<Posts> findPostsByCategoryWithPaging(String category, Pageable pageable) {

        List<Posts> results = queryFactory
                .select(posts)
                .from(posts)
                .where(
                        posts.category.name.eq(category)
                )
                .orderBy(posts.id.desc())
                .limit(pageable.getPageSize()+1)
                .fetch();

        return results;
    }
}
