package com.liziczh.erp.repository;

import com.liziczh.erp.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @Description 用户
 * @Author czh
 * @Date 2019/4/28
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsernameAndPassword(String username, String password);

    Optional<User> findOneByUsername(String username);

    Optional<User> findOneByEmailIgnoreCase(String email);

    Page<User> findByNameContaining(String search, Pageable pageable);

    Page<User> findByRole_IdAndNameContaining(Integer roleId, String search, Pageable pageable);
}
