package com.icebear2n2.lotto.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.icebear2n2.lotto.exception.ClientErrorException;

import lombok.RequiredArgsConstructor;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.service.DefaultMessageService;

@Service
@RequiredArgsConstructor
public class MessagingService {
    private final DefaultMessageService defaultMessageService;

    public void sendMessage(Message message) {
        try {
            defaultMessageService.sendOne(new SingleMessageSendingRequest(message));
        } catch (Exception e) {
            throw new ClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "메시지 전송 실패");
        }
    }
}