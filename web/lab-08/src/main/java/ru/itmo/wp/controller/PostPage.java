package ru.itmo.wp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itmo.wp.domain.Comment;
import ru.itmo.wp.domain.Post;
import ru.itmo.wp.domain.Role;
import ru.itmo.wp.form.UserCredentials;
import ru.itmo.wp.security.AnyRole;
import ru.itmo.wp.security.Guest;
import ru.itmo.wp.service.CommentService;
import ru.itmo.wp.service.PostService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class PostPage extends Page {
    private PostService postService;

    public PostPage(PostService postService) {
        this.postService = postService;
    }

    @Guest
    @AnyRole
    @GetMapping("/post/{id}")
    public String post(@PathVariable Long id, Model model) {
        model.addAttribute("post", postService.findById(id).orElse(null));
        return "PostPage";
    }

    @AnyRole({Role.Name.WRITER})
    @PostMapping("/post/{id}")
    public String writeCommentPost(@PathVariable Long id,
                                @Valid @ModelAttribute("comment") Comment comment,
                                BindingResult bindingResult,
                                HttpSession httpSession) {
        if (bindingResult.hasErrors()) {
            return "PostPage";
        }

        postService.writeComment(postService.findById(id).orElse(null), comment);
        putMessage(httpSession, "You published new comment");

        return "/post/" + id;
    }
}
