package ru.itmo.wp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmo.wp.domain.Notice;
import ru.itmo.wp.repository.NoticeRepository;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class NoticeService {

    @Autowired
    private NoticeRepository noticeRepository;

    public Notice findById(Long id) {
        return id == null ? null : noticeRepository.findById(id).orElse(null);
    }

    public List<Notice> findAll() {
        return noticeRepository.findAllByOrderByIdDesc();
    }

    public void putNotice(Notice notice) {
        noticeRepository.save(notice);
    }
}
