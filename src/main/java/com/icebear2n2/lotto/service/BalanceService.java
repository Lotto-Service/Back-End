package com.icebear2n2.lotto.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import net.nurigo.sdk.message.model.Balance;
import net.nurigo.sdk.message.service.DefaultMessageService;

@Service
@RequiredArgsConstructor
public class BalanceService {
    private final DefaultMessageService defaultMessageService;

    public Balance getBalance() {
        return defaultMessageService.getBalance();
    }
}
