package com.icebear2n2.lotto.service;

import com.icebear2n2.lotto.model.dto.RoundDto;
import com.icebear2n2.lotto.model.entity.Round;
import com.icebear2n2.lotto.repository.RoundRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoundService {
    private final RoundRepository roundRepository;

    public Page<RoundDto> findAll(PageRequest pageRequest) {
        Page<Round> all = roundRepository.findAll(pageRequest);
        return all.map(RoundDto::of);
    }
}
