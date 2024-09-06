package com.icebear2n2.lotto.model.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.Date;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "ROUNDS")
public class Round {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROUND_ID")
    private Long roundId;
    @Column(name = "DRAW_NO")
    private Long drawNo;
    @Column(name = "DRAW_DATE")
    private Date drawDate;
    @Column(name = "WINNING_NUM_1")
    private Integer winningNum1;
    @Column(name = "WINNING_NUM_2")
    private Integer winningNum2;
    @Column(name = "WINNING_NUM_3")
    private Integer winningNum3;
    @Column(name = "WINNING_NUM_4")
    private Integer winningNum4;
    @Column(name = "WINNING_NUM_5")
    private Integer winningNum5;
    @Column(name = "WINNING_NUM_6")
    private Integer winningNum6;
    @Column(name = "BONUS_NUMBER")
    private Integer bonusNumber;
    @Column(name = "CREATED_AT")
    private ZonedDateTime createdAt;

    public Round(Long drawNo, Date drawDate, Integer winningNum1, Integer winningNum2, Integer winningNum3, Integer winningNum4, Integer winningNum5, Integer winningNum6, Integer bonusNumber) {
        this.drawNo = drawNo;
        this.drawDate = drawDate;
        this.winningNum1 = winningNum1;
        this.winningNum2 = winningNum2;
        this.winningNum3 = winningNum3;
        this.winningNum4 = winningNum4;
        this.winningNum5 = winningNum5;
        this.winningNum6 = winningNum6;
        this.bonusNumber = bonusNumber;
    }



    public void setDrawDate(Date drawDate) {
        this.drawDate = drawDate;
    }

    public void setWinningNum1(Integer winningNum1) {
        this.winningNum1 = winningNum1;
    }

    public void setWinningNum2(Integer winningNum2) {
        this.winningNum2 = winningNum2;
    }

    public void setWinningNum3(Integer winningNum3) {
        this.winningNum3 = winningNum3;
    }

    public void setWinningNum4(Integer winningNum4) {
        this.winningNum4 = winningNum4;
    }

    public void setWinningNum5(Integer winningNum5) {
        this.winningNum5 = winningNum5;
    }

    public void setWinningNum6(Integer winningNum6) {
        this.winningNum6 = winningNum6;
    }

    public void setBonusNumber(Integer bonusNumber) {
        this.bonusNumber = bonusNumber;
    }

    @PrePersist
    private void prePersist() {
        this.createdAt = ZonedDateTime.now();
    }
}
