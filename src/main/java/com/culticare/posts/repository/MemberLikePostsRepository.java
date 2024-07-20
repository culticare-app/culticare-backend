package com.culticare.posts.repository;

import com.culticare.posts.entity.MemberLikePosts;
import com.culticare.posts.entity.Posts;
import org.aspectj.weaver.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberLikePostsRepository extends JpaRepository<MemberLikePosts, Long> {


    boolean existsByMemberIdAndPost(Long memberId, Posts post);

    List<MemberLikePosts> findByMemberId(Long loginUserId);

    MemberLikePosts deleteByMemberIdAndPost(Long loginUserId, Posts findPost);
}
