package com.mycard.bill.batch.service.impl;

import com.mycard.bill.batch.dto.CardPageDTO;
import com.mycard.bill.batch.service.CardService;
import com.mycard.bill.batch.service.ParameterService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class CardServiceImpl implements CardService {

    private final OAuth2RestOperations oAuth2RestOperations;
    private final ParameterService parameterService;
    private HttpHeaders httpHeaders;

    public CardServiceImpl(OAuth2RestOperations oAuth2RestOperations, ParameterService parameterService, HttpHeaders httpHeaders) {
        this.oAuth2RestOperations = oAuth2RestOperations;
        this.parameterService = parameterService;
        this.httpHeaders = httpHeaders;
    }

    public CardPageDTO findAll(int pageSize, int pageNumber) {
        final ResponseEntity<CardPageDTO> cardPageDTOResponseEntity = oAuth2RestOperations.exchange(
                UriComponentsBuilder
                        .fromHttpUrl("http://localhost:8080/api/v1/cards/bill-page")
                        .queryParam("pageSize", pageSize)
                        .queryParam("pageNumber", pageNumber)
                        .queryParam("billDay", parameterService.getBillDay())
                        .toUriString(),
                HttpMethod.GET,
                new HttpEntity<>(httpHeaders),
                CardPageDTO.class
        );

        return cardPageDTOResponseEntity.getBody();
    }
}
