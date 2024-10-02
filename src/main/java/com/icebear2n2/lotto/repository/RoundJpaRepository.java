package com.icebear2n2.lotto.repository;

import com.icebear2n2.lotto.model.entity.Round;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

public interface RoundJpaRepository extends JpaRepository<Round, Long> {
	
	
	Optional<Round> findByDrawNo(Long drawNo);
    Optional<Round> findByDrawNoAndDrawDate(Long drawNo, Date drawDate);
    Page<Round> findAll(Pageable pageable);
    Page<Round> findAllByDrawNo(Long drawNo, Pageable pageable);

    @Modifying
    @Transactional
    @Query("UPDATE Round r SET r.drawDate = :drawDate, r.winningNum1 = :winningNum1, r.winningNum2 = :winningNum2, r.winningNum3 = :winningNum3, r.winningNum4 = :winningNum4, r.winningNum5 = :winningNum5, r.winningNum6 = :winningNum6, r.bonusNumber = :bonusNumber WHERE r.drawNo = :drawNo")
    void updateRound(@Param("drawNo") Long drawNo, @Param("drawDate") Date drawDate, @Param("winningNum1") int winningNum1, @Param("winningNum2") int winningNum2, @Param("winningNum3") int winningNum3, @Param("winningNum4") int winningNum4, @Param("winningNum5") int winningNum5, @Param("winningNum6") int winningNum6, @Param("bonusNumber") int bonusNumber);

}
