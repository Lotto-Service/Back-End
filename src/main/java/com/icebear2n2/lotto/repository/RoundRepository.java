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

    public Round updateByDrawNo(Long drawNo, Round round) {
        Round roundEntity = findByDrawNo(drawNo);
        roundEntity.setDrawDate(round.getDrawDate());
        roundEntity.setWinningNum1(round.getWinningNum1());
        roundEntity.setWinningNum2(round.getWinningNum2());
        roundEntity.setWinningNum3(round.getWinningNum3());
        roundEntity.setWinningNum4(round.getWinningNum4());
        roundEntity.setWinningNum5(round.getWinningNum5());
        roundEntity.setWinningNum6(round.getWinningNum6());
        roundEntity.setBonusNumber(round.getBonusNumber());

        return roundJpaRepository.save(roundEntity);
    }

}
