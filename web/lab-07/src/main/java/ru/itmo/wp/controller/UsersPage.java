package ru.itmo.wp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.itmo.wp.domain.User;
import ru.itmo.wp.service.UserService;

import javax.servlet.http.HttpSession;

@Controller
public class UsersPage extends Page {
    private final UserService userService;

    public UsersPage(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/all")
    public String users(Model model) {
        model.addAttribute("users", userService.findAll());
        return "UsersPage";
    }

    @GetMapping("/user/{id}")
    public String user(@PathVariable String id, Model model, HttpSession httpSession) {

        try {
            Long parsedId = Long.parseLong(id);
            User user = userService.findById(Long.parseLong(id));
            model.addAttribute("userWatched", user);
        } catch (Exception e) {
            putMessage(httpSession, "Error, user ID is invalid");
            model.addAttribute("userWatched", null);
        }
        return "UserPage";
    }
}
