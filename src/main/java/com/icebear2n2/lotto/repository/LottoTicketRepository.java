package com.icebear2n2.lotto.repository;

import com.icebear2n2.lotto.model.entity.LottoTicket;
import com.icebear2n2.lotto.model.entity.Round;
import com.icebear2n2.lotto.model.entity.User;
import com.icebear2n2.lotto.model.request.LottoTicketCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class LottoTicketRepository {
    private final LottoTicketJpaRepository lottoTicketJpaRepository;
    private final RoundRepository roundRepository;

    @Transactional
    public LottoTicket createByManual(LottoTicketCreateRequest request, User currentUser) {
        Round round = getByDrawNo(request.drawNo());
        return lottoTicketJpaRepository.save(new LottoTicket(currentUser, round, request.num1(), request.num2(), request.num3(), request.num4(), request.num5(), request.num6(), request.isAuto()));
    }

    private Round getByDrawNo(Long drawNo) {
        return roundRepository.findByDrawNo(drawNo);
    }

    @Transactional
    public LottoTicket crateByAutomatic(User currentUser, Long drawNo) {
        Round round = getByDrawNo(drawNo);
        return lottoTicketJpaRepository.save(LottoTicket.createByAutomatic(currentUser, round));
    }
}
