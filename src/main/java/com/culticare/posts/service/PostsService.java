package com.culticare.posts.service;

import com.culticare.common.exception.CustomException;
import com.culticare.common.exception.ErrorCode;
import com.culticare.posts.controller.dto.request.PostCreateRequestDto;
import com.culticare.posts.controller.dto.response.PostCreateResponseDto;
import com.culticare.posts.controller.dto.response.PostListResponseDto;
import com.culticare.posts.entity.Categories;
import com.culticare.posts.entity.Posts;
import com.culticare.posts.repository.CategoriesRepository;
import com.culticare.posts.repository.PostsCustomRepositoryImpl;
import com.culticare.posts.repository.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostsService {

    private final PostsRepository postsRepository;
    private final PostsCustomRepositoryImpl postsCustomRepositoryImpl;
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

    public PostListResponseDto getPostListByCategory(String category, Pageable pageable) {

        List<Posts> results = postsCustomRepositoryImpl.findPostsByCategoryWithPaging(category, pageable);
        List<PostListResponseDto.PostDto> postDtoList = transferToPostListDto(results);

        boolean hasNext = false;

        // 조회한 결과 개수가 요청한 페이지 사이즈보다 클 경우, next = true
        if (postDtoList.size() > pageable.getPageSize()) {
            hasNext = true;
            postDtoList.remove(pageable.getPageSize());
        }

        PostListResponseDto postListResponseDto = PostListResponseDto.builder()
                .nextPage(hasNext)
                .posts(postDtoList)
                .build();

        return postListResponseDto;
    }

    private List<PostListResponseDto.PostDto> transferToPostListDto(List<Posts> postsList) {
        List<PostListResponseDto.PostDto> postListResponseDtos = new ArrayList<>();

        for (Posts post : postsList) {
            PostListResponseDto.PostDto postListResponseDto = PostListResponseDto.PostDto.from(post, post.getCategory());
            postListResponseDtos.add(postListResponseDto);
        }

        return postListResponseDtos;
    }


}
