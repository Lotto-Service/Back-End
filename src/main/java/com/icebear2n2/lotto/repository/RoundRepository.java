package com.icebear2n2.lotto.repository;

import com.icebear2n2.lotto.exception.round.RoundNotFoundException;
import com.icebear2n2.lotto.model.entity.Round;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Repository
@RequiredArgsConstructor
public class RoundRepository {
    private final RoundJpaRepository roundJpaRepository;
    @Transactional
    public Round create(Round round) {
        return roundJpaRepository.save(round);
    }

public Round findByDrawNo(Long drawNo) {
    // 현재 날짜를 가져옴 (LocalDate)
    LocalDate currentDate = LocalDate.now();

    // 이번 주 토요일과 다음 주 토요일을 계산
    LocalDate nextSaturday = currentDate.with(DayOfWeek.SATURDAY);
    LocalDate followingSaturday = nextSaturday.plusWeeks(1);

    // 현재 날짜와 이번 주 및 다음 주 토요일 사이의 일 수를 계산
    long daysUntilNextSaturday = ChronoUnit.DAYS.between(currentDate, nextSaturday);
    long daysUntilFollowingSaturday = ChronoUnit.DAYS.between(currentDate, followingSaturday);

    // 이번 주 토요일 또는 다음 주 토요일 이내라면 새로운 Round 객체 생성
    if (daysUntilNextSaturday >= 0 && daysUntilNextSaturday <= 7 ||
            daysUntilFollowingSaturday >= 0 && daysUntilFollowingSaturday <= 14) {
        return new Round(drawNo);
    }

    // 일주일 이상 경과했거나 토요일이 아닌 경우 예외를 던짐
    throw new RoundNotFoundException();
}

    public Page<Round> findAll(Pageable pageable) { return roundJpaRepository.findAll(pageable); }

    public void update(Long drawNo, Date drawDate, int winningNum1, int winningNum2, int winningNum3, int winningNum4, int winningNum5, int winningNum6, int bonusNumber) {
        roundJpaRepository.updateRound(drawNo, drawDate, winningNum1, winningNum2, winningNum3, winningNum4, winningNum5, winningNum6, bonusNumber);
    }
}
