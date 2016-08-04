package com.misspeach.custom.entity.record.jpa;

import com.misspeach.custom.entity.record.Record;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by shizhan on 16/8/4.
 */
public interface RecordJpaRepository extends JpaRepository<Record,Long> {
}
