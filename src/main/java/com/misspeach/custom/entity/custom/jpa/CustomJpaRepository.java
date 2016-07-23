package com.misspeach.custom.entity.custom.jpa;

import com.misspeach.custom.entity.custom.Custom;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by shizhan on 16/7/22.
 */
public interface CustomJpaRepository extends JpaRepository<Custom, Long> {
}
