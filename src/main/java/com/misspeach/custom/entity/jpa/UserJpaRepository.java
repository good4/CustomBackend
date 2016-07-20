package com.misspeach.custom.entity.jpa;

import com.misspeach.custom.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by jiangtengfei on 16/7/20.
 */
public interface UserJpaRepository extends JpaRepository<User, Long> {
}
