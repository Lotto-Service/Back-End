package com.icebear2n2.lotto.repository;

import com.icebear2n2.lotto.model.entity.Prize;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrizeJpaRepository extends JpaRepository<Prize, Long> {
    Page<Prize> findAll(Pageable pageable);
}
