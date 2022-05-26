package com.careerjumpstart.admin_ms;

import com.careerjumpstart.admin_ms.models.Post;
import com.careerjumpstart.admin_ms.models.PostType;
import com.careerjumpstart.admin_ms.repository.PostsRepo;
import com.careerjumpstart.admin_ms.service.PostServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PostsTest {
    @InjectMocks
    private PostServiceImpl service;

    @Mock
    private PostsRepo repository;

    @Test
    void serviceCreated(){
        assertThat(service).isNotNull();
    }

    @Test
    void findById(){
        Post post = createPost();
        when(repository.findById(post.getId())).thenReturn(Optional.of(post));
        Optional<Post> existingPost = service.findById(post.getId());
        assertThat(existingPost).isEqualTo(Optional.of(post));
    }

    @Test
    void findAllPosts(){
        Post post = createPost();
        when(repository.findAll()).thenReturn(List.of(post));
        List<Post> existingPosts = service.findAll();
        assertThat(existingPosts).isEqualTo(List.of(post));
    }

    @Test
    void findPostsByNickname(){
        Post post = createPost();
        when(repository.findPostsByUsername(post.getUsername())).thenReturn(List.of(post));
        List<Post> existingPosts = service.findByUsername(post.getUsername());
        assertThat(existingPosts).isEqualTo(List.of(post));
    }


    private Post createPost(){
        Post post = new Post();
        post.setTitle("Test");
        post.setUsername("Test");
        post.setContent("Test");
        post.setPostType(PostType.OPEN);
        return post;
    }
}
