package com.culticare.posts.repository;

import com.culticare.member.entity.Member;
import com.culticare.posts.entity.MemberLikePosts;
import com.culticare.posts.entity.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberLikePostsRepository extends JpaRepository<MemberLikePosts, Long> {


    boolean existsByMemberAndPost(Member member, Posts post);

    List<MemberLikePosts> findByMember(Member member);

    Optional<MemberLikePosts> findByMemberAndPost(Member member, Posts findPost);

}
