package ru.itmo.wp.service;

import org.springframework.stereotype.Service;
import ru.itmo.wp.domain.Comment;
import ru.itmo.wp.domain.Post;
import ru.itmo.wp.controller.Page;
import ru.itmo.wp.repository.CommentRepository;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class CommentService {
    private CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<Comment> findAllByPost(Post post) {
        return commentRepository.findAllByPost(post);
    }
}
