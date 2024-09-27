package com.icebear2n2.lotto.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.service.DefaultMessageService;

@Configuration
public class MessageServiceConfig {
    @Value("${nurigo.api.key}")
    private String nurigoApiKey;

    @Value("${nurigo.api.secret}")
    private String nurigoApiSecret;
    
    @Bean
    public DefaultMessageService defaultMessageService() {
        // NurigoApp.INSTANCE.initialize를 통해 초기화
        return NurigoApp.INSTANCE.initialize(nurigoApiKey, nurigoApiSecret, "https://api.coolsms.co.kr");
    }
}
