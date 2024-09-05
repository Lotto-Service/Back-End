package com.icebear2n2.lotto.model.dto;

import com.icebear2n2.lotto.model.entity.LottoTicket;
import com.icebear2n2.lotto.model.entity.Round;
import com.icebear2n2.lotto.model.entity.User;
import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
public class LottoTicketDto {
    private final UserDto user;
    private final RoundDto round;
    private final int num1;
    private final int num2;
    private final int num3;
    private final int num4;
    private final int num5;
    private final int num6;
    private final boolean isAuto;
    private final ZonedDateTime createdAt;

    public LottoTicketDto(User user, Round round, int num1, int num2, int num3, int num4, int num5, int num6, boolean isAuto, ZonedDateTime createdAt) {
        this.user = new UserDto(user.getUsername(), user.getEmail(), user.getBirth(), user.getPhoneNumber(), user.getCreatedAt(), user.getUpdatedAt(), user.getDeletedAt());
        this.round = new RoundDto(round.getDrawNo(), round.getDrawDate(), round.getWinningNum1(), round.getWinningNum2(), round.getWinningNum3(), round.getWinningNum4(), round.getWinningNum5(), round.getWinningNum6(), round.getBonusNumber(), round.getCreatedAt());
        this.num1 = num1;
        this.num2 = num2;
        this.num3 = num3;
        this.num4 = num4;
        this.num5 = num5;
        this.num6 = num6;
        this.isAuto = isAuto;
        this.createdAt = createdAt;
    }

    public static LottoTicketDto of(LottoTicket ticket) { return new LottoTicketDto(ticket.getUser(), ticket.getRound(), ticket.getNum1(), ticket.getNum2(), ticket.getNum3(), ticket.getNum4(), ticket.getNum5(), ticket.getNum6(), ticket.isAuto(), ticket.getCreatedAt()); }
}
