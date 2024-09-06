package com.icebear2n2.lotto.repository;

import com.icebear2n2.lotto.model.entity.Round;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoundJpaRepository extends JpaRepository<Round, Long> {
    Optional<Round> findByDrawNo(Long drawNo);

}
