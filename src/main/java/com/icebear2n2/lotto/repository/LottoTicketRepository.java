package com.icebear2n2.lotto.repository;

import com.icebear2n2.lotto.model.entity.LottoTicket;
import com.icebear2n2.lotto.model.entity.Round;
import com.icebear2n2.lotto.model.entity.User;
import com.icebear2n2.lotto.model.request.LottoTicketCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class LottoTicketRepository {
    private final LottoTicketJpaRepository lottoTicketJpaRepository;
    private final RoundRepository roundRepository;

    public LottoTicket createByManual(LottoTicketCreateRequest request, User currentUser) {
        Round round = getByRoundId(request.roundId());
        return lottoTicketJpaRepository.save(new LottoTicket(currentUser, round, request.num1(), request.num2(), request.num3(), request.num4(), request.num5(), request.num6(), request.isAuto()));
    }

    private Round getByRoundId(Long roundId) {
        return roundRepository.findById(roundId);
    }

    public LottoTicket crateByAutomatic(User currentUser, Long roundId) {
        Round round = getByRoundId(roundId);
        return lottoTicketJpaRepository.save(LottoTicket.createByAutomatic(currentUser, round));
    }
}
