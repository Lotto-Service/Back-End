package com.icebear2n2.lotto.repository;

import com.icebear2n2.lotto.exception.round.RoundNotFoundException;
import com.icebear2n2.lotto.model.entity.Round;
import com.icebear2n2.lotto.model.request.RoundCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RoundRepository {
    private final RoundJpaRepository roundJpaRepository;

    public Round create(RoundCreateRequest request) {
        return roundJpaRepository.save(new Round(request.drawNo(), request.drawDate(), request.winningNum1(), request.winningNum2(), request.winningNum3(), request.winningNum4(), request.winningNum5(), request.winningNum6(), request.bonusNumber()));
    }

    public Round findById(Long id) {
        return roundJpaRepository.findById(id).orElseThrow(RoundNotFoundException::new);
    }

    public Round findByDrawNo(Long drawNo) { return roundJpaRepository.findByDrawNo(drawNo).orElseThrow(RoundNotFoundException::new); }

}
