package com.icebear2n2.lotto.model.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "PRIZES")
public class Prize {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRIZE_ID")
    private Long prizeId;

    @ManyToOne
    @JoinColumn(name = "DRAW_NO")
    private Round round;

    @Column(name = "TOT_SELLAMNT")
    private BigDecimal totSellamnt;

    @Column(name = "FIRST_ACCUMAMNT")
    private BigDecimal firstAccumamnt;

    @Column(name = "FIRST_PRZWNER_CO")
    private Integer firstPrzwnerCo;

    @Column(name = "FIRST_WINAMNT")
    private BigDecimal firstWinamnt;


    public Prize(Round round, BigDecimal totSellamnt, BigDecimal firstAccumamnt, Integer firstPrzwnerCo, BigDecimal firstWinamnt) {
        this.round = round;
        this.totSellamnt = totSellamnt;
        this.firstAccumamnt = firstAccumamnt;
        this.firstPrzwnerCo = firstPrzwnerCo;
        this.firstWinamnt = firstWinamnt;
    }
}
