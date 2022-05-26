package com.careerjumpstart.admin_ms;

import com.careerjumpstart.admin_ms.models.Comment;
import com.careerjumpstart.admin_ms.models.Post;
import com.careerjumpstart.admin_ms.models.PostType;
import com.careerjumpstart.admin_ms.repository.CommentsRepo;
import com.careerjumpstart.admin_ms.repository.PostsRepo;
import com.careerjumpstart.admin_ms.service.CommentServiceImpl;
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
public class CommentsTest {
    @InjectMocks
    private CommentServiceImpl service;

    @Mock
    private CommentsRepo repository;

    @Test
    void serviceCreated(){
        assertThat(service).isNotNull();
    }

    @Test
    void getAll(){
        Comment comment = createComment();
        when(repository.findAll()).thenReturn(List.of(comment));
        List<Comment> result = service.findAll();
        assertThat(result).isEqualTo(List.of(comment));
    }

    @Test
    void getById(){
        Comment comment = createComment();
        when(repository.findById(comment.getId())).thenReturn(Optional.of(comment));
        Optional<Comment> result = service.findById(comment.getId());
        assertThat(result).isEqualTo(Optional.of(comment));
    }

    @Test
    void getCommentsByUsername(){
        Comment comment = createComment();
        when(repository.findCommentsByUsername(comment.getUsername())).thenReturn(List.of(comment));
        List<Comment> result = service.findCommentsByUsername(comment.getUsername());
        assertThat(result).isEqualTo(List.of(comment));
    }

    @Test
    void getCommentsByPostId(){
        Comment comment = createComment();
        when(repository.findCommentsByPost_Id(comment.getPost().getId())).thenReturn(List.of(comment));
        List<Comment> result = service.findCommentsByPost_Id(comment.getPost().getId());
        assertThat(result).isEqualTo(List.of(comment));
    }

    private Comment createComment(){
        Comment comment = new Comment();
        comment.setContent("Test");
        comment.setUsername("Test");
        Post post = new Post();
        post.setTitle("Test");
        post.setUsername("Test");
        post.setContent("Test");
        post.setPostType(PostType.OPEN);
        comment.setPost(post);
        return comment;
    }
}
