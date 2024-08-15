package com.culticare.comments.controller;

import com.culticare.comments.controller.dto.request.CommentCreateRequestDto;
import com.culticare.comments.controller.dto.request.CommentEditRequestDto;
import com.culticare.comments.controller.dto.response.CommentCreateResponseDto;
import com.culticare.comments.controller.dto.response.CommentsListResponseDto;
import com.culticare.comments.service.CommentsService;
import com.culticare.jwt.service.dto.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentsController {

    private final CommentsService commentsService;

    // 댓글 생성
    @PostMapping("/auth/new/{postId}")
    public ResponseEntity<CommentCreateResponseDto> saveComments(@AuthenticationPrincipal CustomUserDetails userDetails, @PathVariable("postId") Long postId, @RequestBody CommentCreateRequestDto commentCreateRequestDto) {

        Long savedCommentId = commentsService.saveComments(userDetails.getMember(), postId, commentCreateRequestDto);
        CommentCreateResponseDto commentCreateResponseDto = commentsService.getCommentById(savedCommentId);

        return ResponseEntity.status(HttpStatus.OK).body(commentCreateResponseDto);
    }

    // 게시글의 댓글 목록 조회
    @GetMapping("/list/{postId}")
    public ResponseEntity<CommentsListResponseDto> findByPostId(@PathVariable("postId") Long postId, @PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {

        CommentsListResponseDto commentsListResponseDto = commentsService.getCommentsByPost(postId, pageable);

        return ResponseEntity.status(HttpStatus.OK).body(commentsListResponseDto);
    }

    // 댓글 수정
    @PatchMapping("/auth/{commentId}")
    public ResponseEntity<Void> edit(@AuthenticationPrincipal CustomUserDetails userDetails, @PathVariable("commentId") Long commentId, @RequestBody CommentEditRequestDto commentEditRequestDto) {

        commentsService.editComment(userDetails.getMember(), commentId, commentEditRequestDto);

        return ResponseEntity.ok().build();
    }

    // 댓글 삭제
    @DeleteMapping("/auth/{commentId}")
    public ResponseEntity<Void> deleteComment(@AuthenticationPrincipal CustomUserDetails userDetails, @PathVariable("commentId") Long commentId) {

        commentsService.deleteComment(userDetails.getMember(), commentId);

        return ResponseEntity.ok().build();
    }
}
