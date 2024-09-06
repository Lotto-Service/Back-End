package com.icebear2n2.lotto.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.icebear2n2.lotto.model.dto.DhlotteryRoundDto;
import com.icebear2n2.lotto.model.response.Response;
import com.icebear2n2.lotto.service.DhlotteryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/dhlottery")
@RequiredArgsConstructor
public class DhlotteryController {
    private final DhlotteryService dhlotteryService;

    @PostMapping("/{drwNo}")
    public Response<DhlotteryRoundDto> saveByLottoNumberAndPrize(@PathVariable Long drwNo) throws JsonProcessingException {
        return Response.success(dhlotteryService.saveByLottoNumberAndPrize(drwNo));
    }
}
