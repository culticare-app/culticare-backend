package com.culticare.posts.controller;

import com.culticare.jwt.service.dto.CustomUserDetails;
import com.culticare.posts.controller.dto.request.PostCreateRequestDto;
import com.culticare.posts.controller.dto.request.PostEditRequestDto;
import com.culticare.posts.controller.dto.response.MemberLikePostsResponseDto;
import com.culticare.posts.controller.dto.response.PostCreateResponseDto;
import com.culticare.posts.controller.dto.response.PostListResponseDto;
import com.culticare.posts.service.PostsService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Member;
import java.util.List;

@RequestMapping("/posts")
@RequiredArgsConstructor
@RestController
public class PostsController {

    private final PostsService postsService;

    // 카테고리 등록
    @PostMapping("/save-category")
    public ResponseEntity<Void> saveCategories(@RequestParam("category") String category) {
        postsService.saveCategories(category);
        return ResponseEntity.ok().build();
    }

    // 게시글 등록
    @PostMapping("/auth/new")
    public ResponseEntity<PostCreateResponseDto> savePosts(@AuthenticationPrincipal CustomUserDetails userDetails, @RequestBody PostCreateRequestDto postCreateRequestDto) {

        Long savedPostId = postsService.savePost(userDetails.getMember(), postCreateRequestDto);
        PostCreateResponseDto postCreateResponseDto = postsService.getPost(userDetails.getMember(), savedPostId);

        return ResponseEntity.status(HttpStatus.OK).body(postCreateResponseDto);
    }

    // 카테고리별 게시글 목록 조회
    @GetMapping("/list")
    public ResponseEntity<PostListResponseDto> findByCategory(@RequestParam(value = "category") String category, @PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(postsService.getPostListByCategory(category, pageable));
    }

    // 게시글 개별 조회
    @GetMapping("/auth/{postId}")
    public ResponseEntity<PostCreateResponseDto> findById(@AuthenticationPrincipal CustomUserDetails userDetails, @PathVariable("postId") Long postId, HttpServletRequest req, HttpServletResponse res) {

        countUpView(postId, req, res);
        PostCreateResponseDto postResponseDto = postsService.getPost(userDetails.getMember(), postId);

        return ResponseEntity.status(HttpStatus.OK).body(postResponseDto);
    }

    // 게시글 수정
    @PatchMapping("/auth/edit/{postId}")
    public ResponseEntity<Void> editPost(@AuthenticationPrincipal CustomUserDetails userDetails, @PathVariable("postId") Long postId, @RequestBody PostEditRequestDto postEditRequestDto) {

        postsService.editPost(userDetails.getMember(), postId, postEditRequestDto);

        return ResponseEntity.ok().build();
    }

    // 게시글 삭제
    @DeleteMapping("/auth/delete/{postId}")
    public ResponseEntity<Void> deletePost(@AuthenticationPrincipal CustomUserDetails userDetails, @PathVariable("postId") Long postId) {

        postsService.deletePost(userDetails.getMember(), postId);

        return ResponseEntity.ok().build();
    }

    // 게시글 좋아요
    @PostMapping("/auth/like/{postId}")
    public ResponseEntity<Void> likePost(@AuthenticationPrincipal CustomUserDetails userDetails, @PathVariable("postId") Long postId) {

        postsService.likePost(userDetails.getMember(), postId);

        return ResponseEntity.ok().build();
    }

    // 회원의 게시글 좋아요 목록 조회
    @GetMapping("/auth/like-list")
    public ResponseEntity<List<MemberLikePostsResponseDto>> findLikeList(@AuthenticationPrincipal CustomUserDetails userDetails) {

        List<MemberLikePostsResponseDto> likeList = postsService.findLikeList(userDetails.getMember());

        return ResponseEntity.status(HttpStatus.OK).body(likeList);
    }

    // 회원의 게시글 좋아요 삭제
    @DeleteMapping("/auth/like/{postId}")
    public ResponseEntity<Void> deleteLikePost(@AuthenticationPrincipal CustomUserDetails userDetails, @PathVariable("postId") Long postId) {

        postsService.deleteLike(userDetails.getMember(), postId);

        return ResponseEntity.ok().build();
    }
    
    //========

    // 게시글 조회수 증가 쿠키 사용해 구현
    private void countUpView(Long postId, HttpServletRequest req, HttpServletResponse res) {

        Cookie oldCookie = null;

        Cookie[] cookies = req.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("postView")) {
                    oldCookie = cookie;
                }
            }
        }

        if (oldCookie != null) {
            if (!oldCookie.getValue().contains("[" + postId.toString() + "]")) {
                postsService.countUpView(postId);
                oldCookie.setValue(oldCookie.getValue() + "_[" + postId + "]");
                oldCookie.setPath("/");
                oldCookie.setMaxAge(60 * 60 * 24);
                res.addCookie(oldCookie);
            }
        } else {
            postsService.countUpView(postId);
            Cookie newCookie = new Cookie("boardView","[" + postId + "]");
            newCookie.setPath("/");
            newCookie.setMaxAge(60 * 60 * 24);
            res.addCookie(newCookie);
        }
    }

}
