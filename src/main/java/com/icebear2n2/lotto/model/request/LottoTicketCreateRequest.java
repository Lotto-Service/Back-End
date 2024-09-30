package com.icebear2n2.lotto.model.request;

import java.util.List;

import com.icebear2n2.lotto.model.entity.IsAuto;


public record LottoTicketCreateRequest(Long drawNo, List<Integer> numList, IsAuto isAuto) {
}
