package com.icebear2n2.lotto.controller;

import com.icebear2n2.lotto.model.dto.PrizeDto;
import com.icebear2n2.lotto.model.response.Response;
import com.icebear2n2.lotto.service.PrizeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/prizes")
@RequiredArgsConstructor
public class PrizeController {
    private final PrizeService prizeService;

    @PostMapping("/{drawNo}")
    public Response<PrizeDto> emptyCreate(@PathVariable Long drawNo) {
    	return Response.success(prizeService.emptyCreate(drawNo));
    }
    
    @GetMapping
    public Response<Page<PrizeDto>> findAll(
            @RequestParam(name = "size", required = false, defaultValue = "0") Integer size,
            @RequestParam(name = "page", required = false, defaultValue = "5") Integer page
    ) {
        PageRequest pageRequest = PageRequest.of(size, page);
        return Response.success(prizeService.findAll(pageRequest));
    }
}
