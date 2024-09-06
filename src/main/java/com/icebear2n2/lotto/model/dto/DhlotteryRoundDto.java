package com.icebear2n2.lotto.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
public class DhlotteryRoundDto {
    @JsonProperty("totSellamnt")
    private Long totSellamnt;
    @JsonProperty("returnValue")
    private String returnValue;
    @JsonProperty("drwNoDate")
    private Date drwNoDate;
    @JsonProperty("firstWinamnt")
    private Long firstWinamnt;
    @JsonProperty("drwtNo6")
    private Integer drwtNo6;
    @JsonProperty("drwtNo4")
    private Integer drwtNo4;
    @JsonProperty("firstPrzwnerCo")
    private Integer firstPrzwnerCo;
    @JsonProperty("drwtNo5")
    private Integer drwtNo5;
    @JsonProperty("bnusNo")
    private Integer bnusNo;
    @JsonProperty("firstAccumamnt")
    private Long firstAccumamnt;
    @JsonProperty("drwNo")
    private Long drwNo;
    @JsonProperty("drwtNo2")
    private Integer drwtNo2;
    @JsonProperty("drwtNo3")
    private Integer drwtNo3;
    @JsonProperty("drwtNo1")
    private Integer drwtNo1;



}
