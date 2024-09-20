package com.icebear2n2.lotto.model.request;

public record CheckAuthCodeRequest(Long userId, String phoneNumber, String code) {

}
