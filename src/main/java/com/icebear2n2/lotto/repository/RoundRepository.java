package com.icebear2n2.lotto.repository;

import com.icebear2n2.lotto.exception.round.RoundNotFoundException;
import com.icebear2n2.lotto.model.entity.Round;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class RoundRepository {
    private final RoundJpaRepository roundJpaRepository;
    @Transactional
    public Round create(Round round) {
        return roundJpaRepository.save(round);
    }

    public Round findByDrawNo(Long drawNo) { return roundJpaRepository.findByDrawNo(drawNo).orElseThrow(RoundNotFoundException::new); }

    public Page<Round> findAll(Pageable pageable) { return roundJpaRepository.findAll(pageable); }
}
