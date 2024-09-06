package com.icebear2n2.lotto.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PrizeRepository {
    private final PrizeJpaRepository prizeJpaRepository;


}
