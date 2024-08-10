package com.culticare.posts.repository;

import com.culticare.posts.entity.MemberLikePosts;
import com.culticare.posts.entity.Posts;
import org.aspectj.weaver.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberLikePostsRepository extends JpaRepository<MemberLikePosts, Long> {


    boolean existsByMemberIdAndPost(String memberId, Posts post);

    List<MemberLikePosts> findByMemberId(String loginUserId);

    Optional<MemberLikePosts> findByMemberIdAndPost(String loginUserId, Posts findPost);

}
