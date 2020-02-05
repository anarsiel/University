package ru.itmo.wp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itmo.wp.domain.Notice;
import ru.itmo.wp.service.NoticeService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class NoticeCreationPage extends Page {

    @Autowired
    private NoticeService noticeService;

    @GetMapping("/createNotice")
    public String create(Model model) {
        model.addAttribute("noticeForm", new Notice());
        return "NoticePage";
    }

    @PostMapping("/createNotice")
    public String create(@Valid @ModelAttribute("noticeForm") Notice notice,
                        BindingResult bindingResult,
                        HttpSession httpSession) {

        if (bindingResult.hasErrors()) {
            return "NoticePage";
        }

        if (getUser(httpSession) == null) {
            return "redirect:/enter";
        }

        noticeService.putNotice(notice);
        putMessage(httpSession, "Notice successfully added");

        return "redirect:/";
    }
}
