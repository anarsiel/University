package ru.itmo.wp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.itmo.wp.domain.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT * FROM user WHERE login=?1 AND passwordSha=SHA1(CONCAT('1be3db47a7684152', ?1, ?2))", nativeQuery = true)
    User findByLoginAndPassword(String login, String password);

    List<User> findAllByOrderByCreationTimeDesc();
}
