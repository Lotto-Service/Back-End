package com.icebear2n2.lotto.repository;

import com.icebear2n2.lotto.exception.ClientErrorException;
import com.icebear2n2.lotto.model.entity.Round;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Repository
@RequiredArgsConstructor
public class RoundRepository {
    private final RoundJpaRepository roundJpaRepository;
    @Transactional
    public Round create(Round round) {
        return roundJpaRepository.save(round);
    }

    public Round emptyCreate(Long drawNo) {
       return roundJpaRepository.save(new Round(drawNo));
    }
    
    public Round findByDrawNo(Long drawNo) {
        return roundJpaRepository.findByDrawNo(drawNo).orElseThrow(() -> new ClientErrorException(HttpStatus.OK, "해당 추첨 번호에 대한 회차 정보를 찾을 수 없습니다."));
    }

    public Round findByDrawNoAndDrawDate(Long drawNo, Date drawDate) {
        return roundJpaRepository.findByDrawNoAndDrawDate(drawNo, drawDate).orElseThrow(() -> new ClientErrorException(HttpStatus.OK, "해당 추첨 번호에 대한 회차 정보를 찾을 수 없습니다."));
    }

    public Page<Round> findAll(Pageable pageable) { return roundJpaRepository.findAll(pageable); }

    public void update(Long drawNo, Date drawDate, int winningNum1, int winningNum2, int winningNum3, int winningNum4, int winningNum5, int winningNum6, int bonusNumber) {
        roundJpaRepository.updateRound(drawNo, drawDate, winningNum1, winningNum2, winningNum3, winningNum4, winningNum5, winningNum6, bonusNumber);
    }
}
