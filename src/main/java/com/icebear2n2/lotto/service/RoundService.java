package com.icebear2n2.lotto.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.icebear2n2.lotto.http.HttpClient;
import com.icebear2n2.lotto.model.dto.DhlotteryRoundDto;
import com.icebear2n2.lotto.model.dto.RoundDto;
import com.icebear2n2.lotto.model.request.RoundCreateRequest;
import com.icebear2n2.lotto.repository.RoundRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoundService {
    private final RoundRepository roundRepository;

    public RoundDto create(RoundCreateRequest request) {
        return RoundDto.of(roundRepository.create(request));
    }
}
