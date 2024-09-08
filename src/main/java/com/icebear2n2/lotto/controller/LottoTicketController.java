package com.icebear2n2.lotto.controller;

import com.icebear2n2.lotto.model.dto.LottoTicketDto;
import com.icebear2n2.lotto.model.entity.User;
import com.icebear2n2.lotto.model.request.LottoTicketCreateRequest;
import com.icebear2n2.lotto.model.response.Response;
import com.icebear2n2.lotto.service.LottoTicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/v1/lotto-tickets")
@RequiredArgsConstructor
public class LottoTicketController {
    private final LottoTicketService lottoTicketService;

    @PostMapping
    public Response<LottoTicketDto> createByManual(@RequestBody LottoTicketCreateRequest request, Authentication authentication) {
        return Response.success(lottoTicketService.createByManual(request, (User) authentication.getPrincipal()));
    }

    @PostMapping("/auto")
    public Response<LottoTicketDto> createByAutomatic(Authentication authentication, @RequestParam Long drawNo, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date drawDate) {
        return Response.success(lottoTicketService.createByAutomatic((User) authentication.getPrincipal(), drawNo, drawDate));
    }
}
