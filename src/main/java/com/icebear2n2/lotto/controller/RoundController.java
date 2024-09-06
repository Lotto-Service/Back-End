package com.icebear2n2.lotto.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.icebear2n2.lotto.model.dto.RoundDto;
import com.icebear2n2.lotto.model.request.RoundCreateRequest;
import com.icebear2n2.lotto.model.response.Response;
import com.icebear2n2.lotto.service.RoundService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rounds")
@RequiredArgsConstructor
public class RoundController {
    private final RoundService roundService;

    @PostMapping
    public Response<RoundDto> create(@RequestBody RoundCreateRequest request) {
        return Response.success(roundService.create(request));
    }
}
