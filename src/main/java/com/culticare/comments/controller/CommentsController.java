package com.culticare.comments.controller;

import com.culticare.comments.controller.dto.request.CommentCreateRequestDto;
import com.culticare.comments.controller.dto.response.CommentCreateResponseDto;
import com.culticare.comments.controller.dto.response.CommentsListResponseDto;
import com.culticare.comments.service.CommentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentsController {

    private final CommentsService commentsService;

    @PostMapping("/new/{postId}")
    public ResponseEntity<CommentCreateResponseDto> saveComments(Long loginMemberId, @PathVariable("postId") Long postId, CommentCreateRequestDto commentCreateRequestDto) {

        Long savedCommentId = commentsService.saveComments(loginMemberId, postId, commentCreateRequestDto);
        CommentCreateResponseDto commentCreateResponseDto = commentsService.getCommentById(savedCommentId);

        return ResponseEntity.status(HttpStatus.OK).body(commentCreateResponseDto);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<CommentsListResponseDto> findByPostId(@PathVariable("postId") Long postId, @PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {

        CommentsListResponseDto commentsListResponseDto = commentsService.getCommentsByPost(postId, pageable);

        return ResponseEntity.status(HttpStatus.OK).body(commentsListResponseDto);
    }

    
}
