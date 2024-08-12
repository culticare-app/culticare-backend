package com.culticare.comments.service;

import com.culticare.comments.controller.dto.request.CommentCreateRequestDto;
import com.culticare.comments.controller.dto.request.CommentEditRequestDto;
import com.culticare.comments.controller.dto.response.CommentCreateResponseDto;
import com.culticare.comments.controller.dto.response.CommentsListResponseDto;
import com.culticare.comments.entity.Comments;
import com.culticare.comments.repository.CommentsCustomRepository;
import com.culticare.comments.repository.CommentsRepository;
import com.culticare.common.exception.CustomException;
import com.culticare.common.exception.ErrorCode;
import com.culticare.member.entity.Member;
import com.culticare.posts.entity.Posts;
import com.culticare.posts.repository.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class CommentsService {

    private final CommentsRepository commentsRepository;
    private final CommentsCustomRepository commentsCustomRepository;
    private final PostsRepository postsRepository;

    @Transactional
    public Long saveComments(Member member, Long postId, CommentCreateRequestDto dto) {
        Posts findPost = postsRepository.findById(postId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_POST));

        Comments comment = Comments.builder()
                .content(dto.getContent())
                .likeCount(0L)
                .post(findPost)
                .member(member)
                .build();

        return commentsRepository.save(comment).getId();
    }

    public CommentsListResponseDto getCommentsByPost(Long postId, Pageable pageable) {

        Posts findPost = postsRepository.findById(postId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_POST));

        List<Comments> results = commentsCustomRepository.findCommentsByPostWithPaging(findPost, pageable);
        List<CommentsListResponseDto.CommentDto> commentDtoList = transferToCommentListDto(results);

        boolean hasNext = false;

        // 조회한 결과 개수가 요청한 페이지보다 클 경우, next = true
        if (commentDtoList.size() > pageable.getPageSize()) {
            hasNext = true;
            commentDtoList.remove(pageable.getPageSize());
        }

        CommentsListResponseDto commentsListResponseDto = CommentsListResponseDto
                .builder()
                .nextPage(hasNext)
                .comments(commentDtoList)
                .build();

        return commentsListResponseDto;
    }

    private List<CommentsListResponseDto.CommentDto> transferToCommentListDto(List<Comments> results) {

        List<CommentsListResponseDto.CommentDto> commentListResponseDtos = new ArrayList<>();

        for (Comments comment : results) {
            CommentsListResponseDto.CommentDto commentListResponseDto = CommentsListResponseDto.CommentDto.from(comment, comment.getPost());
            commentListResponseDtos.add(commentListResponseDto);
        }

        return commentListResponseDtos;
    }

    public CommentCreateResponseDto getCommentById(Long commentId) {
        Comments findComment = commentsRepository.findById(commentId).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_COMMENT));

        return CommentCreateResponseDto.builder()
                .id(findComment.getId())
                .createdAt(findComment.getCreatedAt())
                .modifiedAt(findComment.getModifiedAt())
                .content(findComment.getContent())
                .likeCount(findComment.getLikeCount())
                .build();
    }

    @Transactional
    public void editComment(Member member, Long commentId, CommentEditRequestDto dto) {

        Comments findComment = commentsRepository.findById(commentId).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_COMMENT));

        checkEditComment(member, findComment);

        findComment.updateComment(dto.getContent());
    }

    @Transactional
    public void deleteComment(Member member, Long commentId) {

        Comments findComment = commentsRepository.findById(commentId).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_COMMENT));

        checkEditComment(member, findComment);

        commentsRepository.delete(findComment);
    }

    private void checkEditComment(Member member, Comments findComment) {

        if (!findComment.getMember().equals(member)) {
            throw new CustomException(ErrorCode.PERMISSION_DENIED);
        }
    }
}
