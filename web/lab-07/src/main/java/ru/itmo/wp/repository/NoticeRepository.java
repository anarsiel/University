package ru.itmo.wp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.itmo.wp.domain.Notice;
import ru.itmo.wp.domain.User;

import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, Long> {

    List<Notice> findAllByOrderByIdDesc();
}
