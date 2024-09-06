package com.icebear2n2.lotto.service;

import com.icebear2n2.lotto.model.dto.LottoTicketDto;
import com.icebear2n2.lotto.model.entity.User;
import com.icebear2n2.lotto.model.request.LottoTicketCreateRequest;
import com.icebear2n2.lotto.repository.LottoTicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LottoTicketService {
    private final LottoTicketRepository lottoTicketRepository;

    public LottoTicketDto createByManual(LottoTicketCreateRequest request, User currentUser) {
        return LottoTicketDto.of(lottoTicketRepository.createByManual(request, currentUser));
    }

    public LottoTicketDto createByAutomatic(User currentUser, Long drawNo) {
        return LottoTicketDto.of(lottoTicketRepository.crateByAutomatic(currentUser, drawNo));
    }
}
