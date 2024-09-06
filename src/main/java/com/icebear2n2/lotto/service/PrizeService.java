package com.icebear2n2.lotto.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.icebear2n2.lotto.http.HttpClient;
import com.icebear2n2.lotto.model.dto.DhlotteryRoundDto;
import com.icebear2n2.lotto.model.dto.PrizeDto;
import com.icebear2n2.lotto.model.entity.Round;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PrizeService {
    private final HttpClient httpClient;

    public List<PrizeDto> getDhlotteryPrize(Long drwNo) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);

        // uri
        String uri = UriComponentsBuilder.fromUriString("https://www.dhlottery.co.kr")
                .path("/common.do")
                .queryParam("method", "getLottoNumber")
                .queryParam("drwNo", drwNo)
                .build()
                .toUriString();

        String url = "https://www.dhlottery.co.kr/common.do";

        // header
        Map<String, String> headers = new HashMap<>();
        headers.put("Accept", "application/json");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAll(headers);

        // call
        String result = httpClient.getData(uri, HttpMethod.GET, httpHeaders);

        List<DhlotteryRoundDto> dhlotteryRounds = objectMapper.readValue(result, new TypeReference<List<DhlotteryRoundDto>>() {});


        return dhlotteryRounds.stream().map(it -> new PrizeDto(
                new Round(it.getDrwNo(), it.getDrwNoDate(), it.getDrwtNo1(), it.getDrwtNo2(), it.getDrwtNo3(), it.getDrwtNo4(), it.getDrwtNo5(), it.getDrwtNo6(), it.getBnusNo())
                , it.getTotSellamnt(), it.getFirstAccumamnt(), it.getFirstPrzwnerCo(), it.getFirstWinamnt())
        ).collect(Collectors.toList());
    }

}
