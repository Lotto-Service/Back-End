package com.icebear2n2.lotto.model.request;

public record LottoTicketCreateRequest(Long roundId, int num1, int num2, int num3, int num4, int num5, int num6, boolean isAuto) {
}
