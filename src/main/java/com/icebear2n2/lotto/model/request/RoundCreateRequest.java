package com.icebear2n2.lotto.model.request;

import java.util.Date;

public record RoundCreateRequest(Long drawNo, Date drawDate, Integer winningNum1, Integer winningNum2, Integer winningNum3, Integer winningNum4, Integer winningNum5, Integer winningNum6, Integer bonusNumber) {
}
