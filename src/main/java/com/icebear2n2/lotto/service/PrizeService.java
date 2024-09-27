package com.icebear2n2.lotto.service;

import com.icebear2n2.lotto.model.dto.PrizeDto;
import com.icebear2n2.lotto.model.entity.Prize;
import com.icebear2n2.lotto.model.entity.Round;
import com.icebear2n2.lotto.repository.PrizeRepository;
import com.icebear2n2.lotto.repository.RoundRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrizeService {
    private final PrizeRepository prizeRepository;
    private final RoundRepository roundRepository;
    
    public PrizeDto emptyCreate(Long drawNo) {
    	Round round = roundRepository.findByDrawNo(drawNo);
    	return PrizeDto.of(prizeRepository.emptyCreate(round));
    }

    public Page<PrizeDto> findAll(PageRequest pageRequest) {
        Page<Prize> all = prizeRepository.findAll(pageRequest);

        return all.map(PrizeDto::of);
    }

}
