package com.mycard.bill.batch.service.impl;

import com.mycard.bill.batch.dto.TransactionDTO;
import com.mycard.bill.batch.dto.TransactionInputCardAndRangeDTO;
import com.mycard.bill.batch.service.TransactionService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final OAuth2RestOperations oAuth2RestOperations;
    private final HttpHeaders httpHeaders;

    public TransactionServiceImpl(OAuth2RestOperations oAuth2RestOperations, HttpHeaders httpHeaders) {
        this.oAuth2RestOperations = oAuth2RestOperations;
        this.httpHeaders = httpHeaders;
    }

    public List<TransactionDTO> getTransactionListByCardAndDtRange(TransactionInputCardAndRangeDTO transactionInputCardAndRangeDTO) {
        final ResponseEntity<TransactionDTO[]> transactionListResponseEntity = oAuth2RestOperations.exchange(
                UriComponentsBuilder.
                        fromHttpUrl("http://localhost:8082/api/v1/transactions/card-and-date")
                        .queryParam("cardBin", transactionInputCardAndRangeDTO.getCardBin())
                        .queryParam("cardNumber", transactionInputCardAndRangeDTO.getCardNumber())
                        .queryParam("timeStampStart", transactionInputCardAndRangeDTO.getTimeStampStart())
                        .queryParam("timeStampEnd", transactionInputCardAndRangeDTO.getTimestampEnd())
                        .toUriString(),
                HttpMethod.GET,
                new HttpEntity<>(httpHeaders),
                TransactionDTO[].class
        );

        return Arrays.asList(transactionListResponseEntity.getBody());
    }
}
