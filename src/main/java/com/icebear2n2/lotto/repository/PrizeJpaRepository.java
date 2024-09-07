package com.icebear2n2.lotto.repository;

import com.icebear2n2.lotto.model.entity.Prize;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface PrizeJpaRepository extends JpaRepository<Prize, Long> {
    Page<Prize> findAll(Pageable pageable);
    Optional<Prize> findByRoundDrawNo(Long drawNo);

    @Modifying
    @Transactional
    @Query("UPDATE Prize p SET p.totSellamnt = :totSellamnt, p.firstAccumamnt = :firstAccumamnt, p.firstPrzwnerCo = :firstPrzwnerCo, p.firstWinamnt = :firstWinamnt WHERE p.round.drawNo = :drawNo")
    void updatePrize(
            @Param("drawNo") Long drawNo,
            @Param("totSellamnt") Long totSellamnt,
            @Param("firstAccumamnt") Long firstAccumamnt,
            @Param("firstPrzwnerCo") Integer firstPrzwnerCo,
            @Param("firstWinamnt") Long firstWinamnt
    );
}
