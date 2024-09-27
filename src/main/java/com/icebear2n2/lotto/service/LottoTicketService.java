package com.icebear2n2.lotto.service;

import com.icebear2n2.lotto.model.dto.LottoTicketDto;
import com.icebear2n2.lotto.model.entity.User;
import com.icebear2n2.lotto.model.request.LottoTicketCreateRequest;
import com.icebear2n2.lotto.repository.LottoTicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LottoTicketService {
    private final LottoTicketRepository lottoTicketRepository;
    
    public List<LottoTicketDto> createLottoTickets(List<LottoTicketCreateRequest> requests, User currentUser) {
    	return lottoTicketRepository.createLottoTickets(requests, currentUser).stream()
    			.map(LottoTicketDto::of).toList();
    }

    public Page<LottoTicketDto> findAllByUser(User currentUser, Pageable pageable) {
        return lottoTicketRepository.findAllByUser(currentUser, pageable).map(LottoTicketDto::of);
    }

    public Page<LottoTicketDto> findAllByRoundDrawNo(User currentUser, Long roundDrawNo, Pageable pageable) {
        return lottoTicketRepository.findAllByRoundDrawNo(currentUser, roundDrawNo, pageable).map(LottoTicketDto::of);
    }
}
