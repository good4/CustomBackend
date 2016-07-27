package com.misspeach.custom.entity.custom.jpa;

import com.misspeach.custom.entity.custom.Custom;
import com.misspeach.custom.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.HashMap;
import java.util.List;

/**
 * Created by shizhan on 16/7/22.
 */

public interface CustomJpaRepository extends JpaRepository<Custom, Long> {


}

