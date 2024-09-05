package com.icebear2n2.lotto.controller;

import com.icebear2n2.lotto.model.dto.UserDto;
import com.icebear2n2.lotto.model.request.UserLoginRequest;
import com.icebear2n2.lotto.model.request.UserSignUpRequest;
import com.icebear2n2.lotto.model.response.Response;
import com.icebear2n2.lotto.model.response.UserAuthenticationResponse;
import com.icebear2n2.lotto.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public Response<UserDto> signUp(@RequestBody UserSignUpRequest request) {
        return Response.success(userService.signUp(request));
    }

    @PostMapping("/authenticate")
    public Response<UserAuthenticationResponse> authenticate(@Valid @RequestBody UserLoginRequest request) {
        return Response.success(userService.authenticate(request));
    }
}
