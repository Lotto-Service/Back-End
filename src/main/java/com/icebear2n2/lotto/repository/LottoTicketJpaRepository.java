package com.icebear2n2.lotto.repository;

import com.icebear2n2.lotto.model.entity.LottoTicket;
import com.icebear2n2.lotto.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LottoTicketJpaRepository extends JpaRepository<LottoTicket, Long> {

    Page<LottoTicket> findAllByUser(User user, Pageable pageable);
    Page<LottoTicket> findAllByUserAndRoundDrawNo(User user, Long drawNo, Pageable pageable);
}
