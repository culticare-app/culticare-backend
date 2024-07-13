package com.culticare.posts.service;

import com.culticare.common.exception.CustomException;
import com.culticare.common.exception.ErrorCode;
import com.culticare.posts.controller.dto.request.PostCreateRequestDto;
import com.culticare.posts.controller.dto.response.PostCreateResponseDto;
import com.culticare.posts.entity.Categories;
import com.culticare.posts.entity.Posts;
import com.culticare.posts.repository.CategoriesRepository;
import com.culticare.posts.repository.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostsService {

    private final PostsRepository postsRepository;
    private final CategoriesRepository categoriesRepository;

    @Transactional
    public Long savePost(Long loginMemberId, PostCreateRequestDto dto) {

        Categories category = categoriesRepository.findByName(dto.getCategory()).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_CATEGORY));

        Posts post = Posts.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .likeCount(0L)
                .view(0L)
                .category(category)
                .loginMemberId(loginMemberId)
                .build();

        return post.getId();
    }


    public PostCreateResponseDto getPost(Long postId) {

        Posts findPost = postsRepository.findById(postId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_POST));

        return PostCreateResponseDto.builder()
                .title(findPost.getTitle())
                .content(findPost.getContent())
                .likeCount(findPost.getLikeCount())
                .view(findPost.getView())
                .category(findPost.getCategory().getName())
                .build();
    }
}
