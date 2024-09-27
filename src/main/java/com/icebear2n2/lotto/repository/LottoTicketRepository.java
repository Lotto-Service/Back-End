package com.icebear2n2.lotto.repository;

import com.icebear2n2.lotto.model.entity.LottoTicket;
import com.icebear2n2.lotto.model.entity.Round;
import com.icebear2n2.lotto.model.entity.User;
import com.icebear2n2.lotto.model.request.LottoTicketCreateRequest;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@RequiredArgsConstructor
public class LottoTicketRepository {
    private final LottoTicketJpaRepository lottoTicketJpaRepository;
    private final RoundRepository roundRepository;
    
    @Transactional
    public List<LottoTicket> createLottoTickets(List<LottoTicketCreateRequest> requests, User currentUser) {
    	List<LottoTicket> savedTickets = new ArrayList<>();
    	
    	for (LottoTicketCreateRequest request : requests) {
    		Round round = getRound(request.drawNo());
    		LottoTicket ticket = new LottoTicket(currentUser, round, request.numList(), request.isAuto());
    		savedTickets.add(lottoTicketJpaRepository.save(ticket));
    	}
    	
    	return savedTickets;
    }

    public Page<LottoTicket> findAllByUser(User currentUser, Pageable pageable) {
        return lottoTicketJpaRepository.findAllByUser(currentUser, pageable);
    }

    public Page<LottoTicket> findAllByRoundDrawNo(User currentUser, Long drawNo, Pageable pageable) {
        return lottoTicketJpaRepository.findAllByUserAndRoundDrawNo(currentUser, drawNo, pageable);
    }

    private Round getRound(Long drawNo) {
        return roundRepository.findByDrawNo(drawNo);
    }

}
