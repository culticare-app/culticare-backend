package com.culticare.posts.service;

import com.culticare.comments.repository.CommentsRepository;
import com.culticare.posts.controller.dto.request.PostCreateRequestDto;
import com.culticare.posts.controller.dto.request.TagListRequestDto;
import com.culticare.posts.entity.Categories;
import com.culticare.posts.entity.HashTag;
import com.culticare.posts.entity.Posts;
import com.culticare.posts.repository.*;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringJUnitConfig
@DataJpaTest
public class PostsServiceIntegrationTest {

    @InjectMocks
    private PostsService postsService;

    @Mock
    private PostsRepository postsRepository;

    @Mock
    private PostsCustomRepositoryImpl postsCustomRepositoryImpl;

    @Autowired
    private CategoriesRepository categoriesRepository;

    @Mock
    private TagRepository tagRepository;

    @Mock
    private PostsHashtagMapRepository postsHashtagMapRepository;

    @Mock
    private MemberLikePostsRepository memberLikePostsRepository;

    @Autowired
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void savePost_Success() {
        // Arrange
        Long loginMemberId = 1L;
        PostCreateRequestDto dto = new PostCreateRequestDto("title", "content", "고민", "writer", "#tag1#tag2");
        Categories category = new Categories(dto.getCategory());
        Posts post = Posts.builder().build();

        when(categoriesRepository.findByName(dto.getCategory())).thenReturn(Optional.of(category));
        //when(categoriesRepository.save(any(Categories.class))).thenReturn(category);
        when(postsRepository.save(any(Posts.class))).thenReturn(post);
        when(tagRepository.findByName(anyString())).thenReturn(Optional.empty());
        when(tagRepository.save(any(HashTag.class))).thenReturn(new HashTag());

        // Act

        Long postId = postsService.savePost(loginMemberId, dto);

        // Assert
        assertNotNull(postId);
        verify(postsRepository).save(any(Posts.class));
        verify(tagRepository).save(any(HashTag.class));
    }




}
