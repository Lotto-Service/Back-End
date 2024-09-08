package com.icebear2n2.lotto.repository;

import com.icebear2n2.lotto.exception.round.RoundNotFoundException;
import com.icebear2n2.lotto.model.entity.Prize;
import com.icebear2n2.lotto.model.entity.Round;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Repository
@RequiredArgsConstructor
public class PrizeRepository {
    private final PrizeJpaRepository prizeJpaRepository;
    private final RoundRepository roundRepository;


    @Transactional
    public Prize create(Prize prize) {
        return prizeJpaRepository.save(prize);
    }

    public void emptyCreate(Round round) {
        prizeJpaRepository.save(new Prize(round));
    }

    public Page<Prize> findAll(PageRequest pageRequest) {
        return prizeJpaRepository.findAll(pageRequest);
    }

    public Prize findByRoundDrawNoAndDrawDate(Long drawNo, Date drawDate) {
        Round round = roundRepository.findByDrawNoAndDrawDate(drawNo, drawDate).orElseThrow(RoundNotFoundException::new);
        return prizeJpaRepository.findByRoundDrawNoAndRoundDrawDate(drawNo, drawDate).orElse(new Prize(round));
    }

    public void update(Long drawNo, Long totSellamnt, Long firstAccumamnt, Integer firstPrzwnerCo, Long firstWinamnt) {
        prizeJpaRepository.updatePrize(drawNo, totSellamnt, firstAccumamnt, firstPrzwnerCo, firstWinamnt);
    }
}
