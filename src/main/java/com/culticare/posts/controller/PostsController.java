package com.culticare.posts.controller;

import com.culticare.posts.controller.dto.request.PostCreateRequestDto;
import com.culticare.posts.controller.dto.response.PostCreateResponseDto;
import com.culticare.posts.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostsController {

    private final PostsService postsService;

    // 게시글 등록
    @PostMapping
    public ResponseEntity<PostCreateResponseDto> savePosts(Long loginMemberId, PostCreateRequestDto dto) {

        Long savedPostId = postsService.savePost(loginMemberId, dto);

        PostCreateResponseDto postCreateResponseDto = postsService.getPost(savedPostId);

        return ResponseEntity.status(HttpStatus.OK).body(postCreateResponseDto);
    }
}
