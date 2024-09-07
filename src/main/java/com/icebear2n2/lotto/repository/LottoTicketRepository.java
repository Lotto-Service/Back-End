package com.icebear2n2.lotto.repository;

import com.icebear2n2.lotto.model.entity.LottoTicket;
import com.icebear2n2.lotto.model.entity.Prize;
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
    private final PrizeRepository prizeRepository;

    @Transactional
    public LottoTicket createByManual(LottoTicketCreateRequest request, User currentUser) {
        Round round = getRound(request.drawNo());

        return lottoTicketJpaRepository.save(new LottoTicket(currentUser, round, request.num1(), request.num2(), request.num3(), request.num4(), request.num5(), request.num6(), request.isAuto()));
    }


    @Transactional
    public LottoTicket crateByAutomatic(User currentUser, Long drawNo) {
        Round round = getRound(drawNo);
        return lottoTicketJpaRepository.save(LottoTicket.createByAutomatic(currentUser, round));
    }

    private Round getByDrawNo(Long drawNo) {
        return roundRepository.findByDrawNo(drawNo);
    }

    private Prize getByRoundDrawNo(Long drawNo, Round round) {
        return prizeRepository.findByRoundDrawNo(drawNo, round);
    }

    private Round getRound(Long drawNo) {
        Round round = getByDrawNo(drawNo);
        Prize prize = getByRoundDrawNo(drawNo, round);
        prizeRepository.create(prize);
        return round;
    }
}
