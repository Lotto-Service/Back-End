package com.icebear2n2.lotto.model.request;

import java.util.Date;

public record LottoTicketCreateRequest(Long drawNo, Date drawDate, int num1, int num2, int num3, int num4, int num5, int num6, boolean isAuto) {
}
