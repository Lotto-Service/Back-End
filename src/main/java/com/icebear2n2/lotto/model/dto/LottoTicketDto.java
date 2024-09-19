package com.icebear2n2.lotto.model.dto;

import com.icebear2n2.lotto.model.IsAuto;
import com.icebear2n2.lotto.model.entity.LottoTicket;
import com.icebear2n2.lotto.model.entity.Round;
import com.icebear2n2.lotto.model.entity.User;
import lombok.Getter;

import java.time.ZonedDateTime;
import java.util.List;

@Getter
public class LottoTicketDto {
    private final UserDto user;
    private final RoundDto round;
    private final List<Integer> numList;
    private final IsAuto isAuto;
    private final ZonedDateTime createdAt;

    public LottoTicketDto(User user, Round round, List<Integer> numList, IsAuto isAuto, ZonedDateTime createdAt) {
        this.user = new UserDto(user.getUsername(), user.getEmail(), user.getBirth(), user.getPhoneNumber(), user.getCreatedAt(), user.getUpdatedAt(), user.getDeletedAt());
        this.round = new RoundDto(round.getDrawNo(), round.getDrawDate(), round.getWinningNum1(), round.getWinningNum2(), round.getWinningNum3(), round.getWinningNum4(), round.getWinningNum5(), round.getWinningNum6(), round.getBonusNumber(), round.getCreatedAt());
        this.numList = numList;
        this.isAuto = isAuto;
        this.createdAt = createdAt;
    }

    public static LottoTicketDto of(LottoTicket ticket) { return new LottoTicketDto(ticket.getUser(), ticket.getRound(), ticket.getNumList(), ticket.getIsAuto(), ticket.getCreatedAt()); }
}
