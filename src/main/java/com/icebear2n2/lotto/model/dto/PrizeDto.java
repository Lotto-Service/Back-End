package com.icebear2n2.lotto.model.dto;

import com.icebear2n2.lotto.model.entity.Prize;
import com.icebear2n2.lotto.model.entity.Round;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class PrizeDto {
    private RoundDto round;
    private Long totSellamnt;
    private Long firstAccumamnt;
    private Integer firstPrzwnerCo;
    private Long firstWinamnt;

    public PrizeDto(Round round, Long totSellamnt, Long firstAccumamnt, Integer firstPrzwnerCo, Long firstWinamnt) {
        this.round = new RoundDto(round.getDrawNo(), round.getDrawDate(), round.getWinningNum1(), round.getWinningNum2(), round.getWinningNum3(), round.getWinningNum4(), round.getWinningNum5(), round.getWinningNum6(), round.getBonusNumber(), round.getCreatedAt());
        this.totSellamnt = totSellamnt;
        this.firstAccumamnt = firstAccumamnt;
        this.firstPrzwnerCo = firstPrzwnerCo;
        this.firstWinamnt = firstWinamnt;
    }

    public static PrizeDto of(Prize prize) { return new PrizeDto(prize.getRound(), prize.getTotSellamnt(), prize.getFirstAccumamnt(), prize.getFirstPrzwnerCo(), prize.getFirstWinamnt()); }
}
