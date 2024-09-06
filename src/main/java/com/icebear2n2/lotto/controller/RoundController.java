package com.icebear2n2.lotto.controller;

import com.icebear2n2.lotto.model.dto.RoundDto;
import com.icebear2n2.lotto.model.response.Response;
import com.icebear2n2.lotto.service.RoundService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/rounds")
@RequiredArgsConstructor
public class RoundController {
    private final RoundService roundService;

}
