package com.icebear2n2.lotto.repository;

import com.icebear2n2.lotto.model.entity.LottoTicket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LottoTicketJpaRepository extends JpaRepository<LottoTicket, Long> {

}
