package com.icebear2n2.lotto.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.icebear2n2.lotto.model.request.PasswordRecoveryRequest;
import com.icebear2n2.lotto.model.response.Response;
import com.icebear2n2.lotto.service.PasswordRecoveryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/recovery")
public class PasswordRecoveryController {
	private final PasswordRecoveryService passwordRecoveryService;
	
	
	@PostMapping
	public Response<String> requestCode(@RequestParam("username") String username) {
		return Response.success(passwordRecoveryService.requestPasswordRecovery(username));
	}
	
	@PutMapping
	public Response<String> resetPassword(@RequestBody PasswordRecoveryRequest passwordRecoveryRequest) {
		return Response.success(passwordRecoveryService.verifyAuthCodeAndResetPassword(passwordRecoveryRequest));
	}
}
