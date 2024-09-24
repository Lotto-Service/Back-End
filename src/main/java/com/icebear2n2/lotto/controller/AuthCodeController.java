package com.icebear2n2.lotto.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.icebear2n2.lotto.model.entity.User;
import com.icebear2n2.lotto.model.response.Response;
import com.icebear2n2.lotto.service.AuthCodeService;

import lombok.RequiredArgsConstructor;
import net.nurigo.sdk.message.model.Balance;

@RestController
@RequestMapping("/api/v1/verification")
@RequiredArgsConstructor
public class AuthCodeController {
	private final AuthCodeService authCodeService;
	
	 @PostMapping
	    public Response<String> sendAuthCode(Authentication authentication) {
	        return Response.success(authCodeService.sendAuthCode((User) authentication.getPrincipal()));
	    }

    @GetMapping
    public Response<String> checkAuthCode(Authentication authentication, @RequestParam("code") String code) {
    	return Response.success(authCodeService.checkAuthCode((User) authentication.getPrincipal(), code));
        
    }

    @GetMapping("/balance")
    public Response<Balance> getBalance() {
        Balance balance = authCodeService.getBalance();
        return Response.success(balance);
    }
}
