package com.misspeach.custom.entity.help.jpa;

import com.misspeach.custom.entity.help.Help;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by shizhan on 16/7/23.
 */
public interface HelpJpaRepository extends JpaRepository<Help,Long> {

}
