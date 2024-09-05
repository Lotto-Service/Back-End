package com.icebear2n2.lotto.model.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "LOTTO_TICKETS")
public class LottoTicket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TICKET_ID")
    private Long ticketId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROUND_ID")
    private Round round;

    @Column(name = "NUM1", nullable = false)
    private int num1;
    @Column(name = "NUM2", nullable = false)
    private int num2;
    @Column(name = "NUM3", nullable = false)
    private int num3;
    @Column(name = "NUM4", nullable = false)
    private int num4;
    @Column(name = "NUM5", nullable = false)
    private int num5;
    @Column(name = "NUM6", nullable = false)
    private int num6;
    @Column(name = "IS_AUTO", nullable = false)
    private boolean isAuto;
    @Column(name = "CREATED_AT")
    private ZonedDateTime createdAt;

    public LottoTicket(User user, Round round, int num1, int num2, int num3, int num4, int num5, int num6, boolean isAuto) {
        this.user = user;
        this.round = round;
        this.num1 = num1;
        this.num2 = num2;
        this.num3 = num3;
        this.num4 = num4;
        this.num5 = num5;
        this.num6 = num6;
        this.isAuto = isAuto;
    }

    public static LottoTicket createByAutomatic(User user, Round round) {
        Random random = new Random();
        Set<Integer> numberSet = new HashSet<>();

        // 1. 6개의 중복되지 않는 숫자 생성
        while (numberSet.size() < 6) {
            numberSet.add(random.nextInt(45) + 1);
        }

        // 2. Set 을 List 로 변환하고 정렬
        List<Integer> numberList = new ArrayList<>(numberSet);
        Collections.sort(numberList);

        // 3. LottoTicket 객체 생성
        return new LottoTicket(
                user,
                round,
                numberList.get(0),
                numberList.get(1),
                numberList.get(2),
                numberList.get(3),
                numberList.get(4),
                numberList.get(5),
                true
        );
    }

    @PrePersist
    private void prePersist() {
        this.createdAt = ZonedDateTime.now();
    }
}
