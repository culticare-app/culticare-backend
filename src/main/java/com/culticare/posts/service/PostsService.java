package com.culticare.posts.service;

import com.culticare.common.exception.CustomException;
import com.culticare.common.exception.ErrorCode;
import com.culticare.posts.controller.dto.request.PostCreateRequestDto;
import com.culticare.posts.controller.dto.request.PostEditRequestDto;
import com.culticare.posts.controller.dto.response.MemberLikePostsResponseDto;
import com.culticare.posts.controller.dto.response.PostCreateResponseDto;
import com.culticare.posts.controller.dto.response.PostListResponseDto;
import com.culticare.posts.entity.*;
import com.culticare.posts.repository.*;
import lombok.RequiredArgsConstructor;
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
    private final TagRepository tagRepository;
    private final PostsHashtagMapRepository postsHashtagMapRepository;
    private final MemberLikePostsRepository memberLikePostsRepository;

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
                .writerName(dto.getWriterName())
                .build();

        List<HashTag> savedTags = dto.getTagList().stream()
                .map(t -> tagRepository.findByName(t.getName())
                        .orElseGet(() -> tagRepository.save(HashTag.builder().name(t.getName()).count(0L).build())))
                .collect(Collectors.toList());

        for(HashTag hashtag: savedTags) {
            PostsHashtagMap savedPostTagMap = PostsHashtagMap.builder()
                    .post(post)
                    .hashTag(hashtag)
                    .build();

            postsHashtagMapRepository.save(savedPostTagMap);
        }

        return post.getId();
    }


    public PostCreateResponseDto getPost(Long loginUserId, Long postId) {

        Posts findPost = postsRepository.findById(postId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_POST));

        boolean isLiked = false;

        if (memberLikePostsRepository.existsByMemberIdAndPost(loginUserId, findPost)) {
            isLiked = true;
        }

        return PostCreateResponseDto.builder()
                .title(findPost.getTitle())
                .content(findPost.getContent())
                .likeCount(findPost.getLikeCount())
                .view(findPost.getView())
                .category(findPost.getCategory().getName())
                .isLiked(isLiked)
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

    @Transactional
    public void editPost(Long loginMemberId, Long postId, PostEditRequestDto dto) {
        Posts findPost = postsRepository.findById(postId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_POST));

        checkEditPost(loginMemberId, findPost);

        findPost.updatePost(dto);
    }

    @Transactional
    public void deletePost(Long loginMemberId, Long postId) {
        Posts findPost = postsRepository.findById(postId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_POST));

        checkEditPost(loginMemberId, findPost);

        findPost.minusLikeCount();

        postsRepository.deleteById(postId);
    }

    private void checkEditPost(Long loginMemberId, Posts findPost) {

        if (!findPost.getLoginMemberId().equals(loginMemberId)) {
            throw new CustomException(ErrorCode.PERMISSION_DENIED);
        }
    }

    @Transactional
    public void likePost(Long loginUserId, Long postId) {

        Posts findPost = postsRepository.findById(postId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_POST));

        if (memberLikePostsRepository.existsByMemberIdAndPost(loginUserId, findPost)) {
            throw new CustomException(ErrorCode.EXIST_USER_LIKED_POST);
        }
        findPost.plusLikeCount();

        MemberLikePosts memberLikePosts = MemberLikePosts.builder()
                .memberId(loginUserId)
                .post(findPost)
                .build();

        memberLikePostsRepository.save(memberLikePosts);
    }

    public List<MemberLikePostsResponseDto> findLikeList(Long loginUserId) {

        List<MemberLikePostsResponseDto> result = new ArrayList<>();
        List<MemberLikePosts> findMemberLikePost = memberLikePostsRepository.findByMemberId(loginUserId);

        for (MemberLikePosts m : findMemberLikePost) {
            result.add(MemberLikePostsResponseDto.builder()
                    .id(m.getId())
                    .postId(m.getPost().getId())
                    .title(m.getPost().getTitle())
                    .content(m.getPost().getContent())
                    .category(m.getPost().getCategory().getName())
                    .view(m.getPost().getView())
                    .likeCount(m.getPost().getLikeCount())
                    .writerName(m.getPost().getWriterName())
                    .build());
        }

        return result;
    }

    @Transactional
    public void deleteLike(Long loginUserId, Long postId) {

        Posts findPost = postsRepository.findById(postId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_POST));

        if (memberLikePostsRepository.existsByMemberIdAndPost(loginUserId, findPost)) {
            throw new CustomException(ErrorCode.EXIST_USER_LIKED_POST);
        }

        MemberLikePosts findMemberLikePosts = memberLikePostsRepository.deleteByMemberIdAndPost(loginUserId, findPost);
        memberLikePostsRepository.delete(findMemberLikePosts);
    }
}
