package com.icebear2n2.lotto.controller;

import com.icebear2n2.lotto.model.dto.LottoTicketDto;
import com.icebear2n2.lotto.model.entity.User;
import com.icebear2n2.lotto.model.request.LottoTicketCreateRequest;
import com.icebear2n2.lotto.model.response.Response;
import com.icebear2n2.lotto.service.LottoTicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/lotto-tickets")
@RequiredArgsConstructor
public class LottoTicketController {
    private final LottoTicketService lottoTicketService;
    
    @PostMapping
    public Response<List<LottoTicketDto>> createLottoTickets(@RequestBody List<LottoTicketCreateRequest> requests, Authentication authentication) {
    	return Response.success(lottoTicketService.createLottoTickets(requests, (User) authentication.getPrincipal()));
    }

    @GetMapping
    public Response<Page<LottoTicketDto>> findAllByUser(Authentication authentication,
                                                     @RequestParam(name = "size", required = false, defaultValue = "0") Integer size,
                                                     @RequestParam(name = "page", required = false, defaultValue = "5") Integer page) {
        PageRequest pageRequest = PageRequest.of(size, page);

        return Response.success(lottoTicketService.findAllByUser((User) authentication.getPrincipal(), pageRequest));
    }

    @GetMapping("/{drawNo}")
    public Response<Page<LottoTicketDto>> findAllByRoundDrawNo(@PathVariable Long drawNo,
                                                               Authentication authentication,
                                                               @RequestParam(name = "size", required = false, defaultValue = "0") Integer size,
                                                               @RequestParam(name = "page", required = false, defaultValue = "5") Integer page) {
        PageRequest pageRequest = PageRequest.of(size, page);

        return Response.success(lottoTicketService.findAllByRoundDrawNo((User) authentication.getPrincipal(), drawNo, pageRequest));
    }
}
