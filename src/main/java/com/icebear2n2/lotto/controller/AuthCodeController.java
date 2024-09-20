package com.icebear2n2.lotto.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.icebear2n2.lotto.model.dto.AuthCodeDto;
import com.icebear2n2.lotto.model.entity.User;
import com.icebear2n2.lotto.service.AuthCodeService;

import lombok.RequiredArgsConstructor;
import net.nurigo.sdk.message.model.Balance;
import retrofit2.Response;

@RestController
@RequestMapping("/api/v1/verification")
@RequiredArgsConstructor
public class AuthCodeController {
	
	private final AuthCodeService authCodeService;
	
	@PostMapping
	public Response<AuthCodeDto> sendAuthCode(@RequestParam("phoneNumber") String phoneNumber, Authentication authentication) {
		return Response.success(authCodeService.sendAuthCode((User) authentication.getPrincipal(), phoneNumber));
	}

	
	@GetMapping
	public Response<AuthCodeDto> checkAuthCode(@RequestParam("phoneNumber") String phoneNumber, Authentication authentication, String code) {
		return Response.success(authCodeService.checkAuthCode(phoneNumber,(User) authentication.getPrincipal(), code));
	}
	
	@GetMapping("/balance")
	public Response<Balance> getBalance() {
		 Balance balance = authCodeService.getBalance();
		 return Response.success(balance);
	}
}
