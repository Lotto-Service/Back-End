package com.icebear2n2.lotto.repository;

import com.icebear2n2.lotto.model.entity.Prize;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class PrizeRepository {
    private final PrizeJpaRepository prizeJpaRepository;

    @Transactional
    public Prize create(Prize prize) {
        return prizeJpaRepository.save(prize);
    }

}
