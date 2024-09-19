package com.icebear2n2.lotto.model.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.*;

import com.icebear2n2.lotto.model.IsAuto;

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

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "DRAW_NO", referencedColumnName = "DRAW_NO")
    private Round round;

    @Convert(converter = IntegerListConverter.class)
    @Column(name = "NUM_LIST", nullable = false)
    private List<Integer> numList;
    
    @Enumerated
    @Column(name = "IS_AUTO", nullable = false)
    private IsAuto isAuto;
    
    @Column(name = "CREATED_AT")
    private ZonedDateTime createdAt;

    public LottoTicket(User user, Round round, List<Integer> numList, IsAuto isAuto) {
        this.user = user;
        this.round = round;
        this.numList = numList;
        this.isAuto = isAuto;
    }

    @PrePersist
    private void prePersist() {
        this.createdAt = ZonedDateTime.now();
    }
}
