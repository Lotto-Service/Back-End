package com.icebear2n2.lotto.model.dto;

import com.icebear2n2.lotto.model.entity.Round;
import lombok.Builder;
import lombok.Getter;

import java.time.ZonedDateTime;
import java.util.Date;

@Getter
@Builder
public class RoundDto {
    private final Long drawNo;
    private final Date drawDate;
    private final Integer winningNum1;
    private final Integer winningNum2;
    private final Integer winningNum3;
    private final Integer winningNum4;
    private final Integer winningNum5;
    private final Integer winningNum6;
    private final Integer bonusNumber;
    private final ZonedDateTime createdAt;

    public RoundDto(Long drawNo, Date drawDate, Integer winningNum1, Integer winningNum2, Integer winningNum3, Integer winningNum4, Integer winningNum5, Integer winningNum6, Integer bonusNumber, ZonedDateTime createdAt) {
        this.drawNo = drawNo;
        this.drawDate = drawDate;
        this.winningNum1 = winningNum1;
        this.winningNum2 = winningNum2;
        this.winningNum3 = winningNum3;
        this.winningNum4 = winningNum4;
        this.winningNum5 = winningNum5;
        this.winningNum6 = winningNum6;
        this.bonusNumber = bonusNumber;
        this.createdAt = createdAt;
    }

    public static RoundDto of(Round round) { return new RoundDto(round.getDrawNo(), round.getDrawDate(), round.getWinningNum1(), round.getWinningNum2(), round.getWinningNum3(), round.getWinningNum4(), round.getWinningNum5(), round.getWinningNum6(), round.getBonusNumber(), ZonedDateTime.now()); }
}
