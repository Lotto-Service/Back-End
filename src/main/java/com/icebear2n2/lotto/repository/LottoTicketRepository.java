package com.icebear2n2.lotto.repository;

import com.icebear2n2.lotto.model.entity.LottoTicket;
import com.icebear2n2.lotto.model.entity.Prize;
import com.icebear2n2.lotto.model.entity.Round;
import com.icebear2n2.lotto.model.entity.User;
import com.icebear2n2.lotto.model.request.LottoTicketCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Repository
@RequiredArgsConstructor
public class LottoTicketRepository {
    private final LottoTicketJpaRepository lottoTicketJpaRepository;
    private final RoundRepository roundRepository;
    private final PrizeRepository prizeRepository;

    @Transactional
    public LottoTicket createByManual(LottoTicketCreateRequest request, User currentUser) {
        Round round = getRound(request.drawNo(), request.drawDate());

        return lottoTicketJpaRepository.save(new LottoTicket(currentUser, round, request.num1(), request.num2(), request.num3(), request.num4(), request.num5(), request.num6(), request.isAuto()));
    }


    @Transactional
    public LottoTicket crateByAutomatic(User currentUser, Long drawNo, Date drawDate) {
        Round round = getRound(drawNo, drawDate);
        return lottoTicketJpaRepository.save(LottoTicket.createByAutomatic(currentUser, round));
    }

    private Round getRound(Long drawNo, Date drawDate) {
        return roundRepository.findByDrawNoAndDrawDate(drawNo, drawDate).orElse(createRoundAndPrize(drawNo, drawDate));
    }

    private Round createRoundAndPrize(Long drawNo, Date drawDate) {
        // 새로운 Round 생성 및 저장
        Round round = roundRepository.emptyCreate(drawNo, drawDate);

        // 새로운 Prize 생성 및 저장
        prizeRepository.emptyCreate(round);

        return round;
    }

}
