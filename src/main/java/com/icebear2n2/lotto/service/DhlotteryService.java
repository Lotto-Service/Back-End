package com.icebear2n2.lotto.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.icebear2n2.lotto.http.HttpClient;
import com.icebear2n2.lotto.model.dto.DhlotteryRoundDto;
import com.icebear2n2.lotto.model.dto.RoundDto;
import com.icebear2n2.lotto.model.entity.Prize;
import com.icebear2n2.lotto.model.entity.Round;
import com.icebear2n2.lotto.repository.PrizeRepository;
import com.icebear2n2.lotto.repository.RoundRepository;
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
public class DhlotteryService {
    private final HttpClient httpClient;
    private final RoundRepository roundRepository;
    private final PrizeRepository prizeRepository;

    public DhlotteryRoundDto saveByLottoNumberAndPrize(Long drwNo) throws JsonProcessingException {
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

        DhlotteryRoundDto dhlotteryRounds = objectMapper.readValue(result, new TypeReference<DhlotteryRoundDto>() {});



        Round round = new Round(dhlotteryRounds.getDrwNo(), dhlotteryRounds.getDrwNoDate(), dhlotteryRounds.getDrwtNo1(), dhlotteryRounds.getDrwtNo2(), dhlotteryRounds.getDrwtNo3(), dhlotteryRounds.getDrwtNo4(), dhlotteryRounds.getDrwtNo5(), dhlotteryRounds.getDrwtNo6(), dhlotteryRounds.getBnusNo());
        Prize prize = new Prize(round, dhlotteryRounds.getTotSellamnt(), dhlotteryRounds.getFirstAccumamnt(), dhlotteryRounds.getFirstPrzwnerCo(), dhlotteryRounds.getFirstWinamnt());
        roundRepository.create(round);
        prizeRepository.create(prize);

        return dhlotteryRounds;
    }
}
