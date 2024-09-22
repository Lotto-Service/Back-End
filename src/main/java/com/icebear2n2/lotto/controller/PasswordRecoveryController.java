package com.icebear2n2.lotto.controller;

import com.icebear2n2.lotto.model.request.PasswordRecoveryRequest;
import com.icebear2n2.lotto.model.response.Response;
import com.icebear2n2.lotto.service.PasswordRecoveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/password-recovery")
@RequiredArgsConstructor
public class PasswordRecoveryController {
    private final PasswordRecoveryService passwordRecoveryService;

    @PostMapping("/{userId}")
    public Response<String> requestCode(@PathVariable Long userId) {
        return Response.success(passwordRecoveryService.requestPasswordRecovery(userId));
    }

    @PutMapping("/update")
    public Response<String> resetPassword(@RequestBody PasswordRecoveryRequest request) {
        return Response.success(passwordRecoveryService.verifyAuthCodAndResetPassword(request));
    }
}
