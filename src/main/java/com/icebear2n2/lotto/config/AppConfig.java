package com.icebear2n2.lotto.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;

import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.service.DefaultMessageService;

import java.util.Random;

@Configuration
public class AppConfig {
    @Value("${nurigo.api.key}")
    private String nurigoApiKey;

    @Value("${nurigo.api.secret}")
    private String nurigoApiSecret;
    
    @Bean
    public Random random() {
        return new Random();
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        return new ProviderManager(provider);
    }

    @Bean
    public DefaultMessageService defaultMessageService() {
        // NurigoApp.INSTANCE.initialize를 통해 초기화
        return NurigoApp.INSTANCE.initialize(nurigoApiKey, nurigoApiSecret, "https://api.coolsms.co.kr");
    }
}