package com.icebear2n2.lotto.service;

import com.icebear2n2.lotto.model.dto.RoundDto;
import com.icebear2n2.lotto.repository.RoundRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoundService {
    private final RoundRepository roundRepository;

}
