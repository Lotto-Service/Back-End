package com.icebear2n2.lotto.controller;

import com.icebear2n2.lotto.model.dto.RoundDto;
import com.icebear2n2.lotto.model.response.Response;
import com.icebear2n2.lotto.service.RoundService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/rounds")
@RequiredArgsConstructor
public class RoundController {
    private final RoundService roundService;

    @GetMapping
    public Response<Page<RoundDto>> findAll(
            @RequestParam(name = "size", required = false, defaultValue = "0") Integer size,
            @RequestParam(name = "page", required = false, defaultValue = "5") Integer page
    ) {
        PageRequest pageRequest = PageRequest.of(size, page);
        return Response.success(roundService.findAll(pageRequest));
    }
}
