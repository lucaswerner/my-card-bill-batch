package com.mycard.bill.batch.service.impl;

import com.mycard.bill.batch.dto.BillDTO;
import com.mycard.bill.batch.service.BillService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.stereotype.Service;

@Service
public class BillServiceImpl implements BillService {

    private final OAuth2RestOperations oAuth2RestOperations;
    private HttpHeaders httpHeaders;

    public BillServiceImpl(OAuth2RestOperations oAuth2RestOperations, HttpHeaders httpHeaders) {
        this.oAuth2RestOperations = oAuth2RestOperations;
        this.httpHeaders = httpHeaders;
    }

    @Override
    public BillDTO postBill(BillDTO billDTO) {
        final ResponseEntity<BillDTO> postBillResponseEntity = oAuth2RestOperations.exchange(
                "http://localhost:8083/api/v1/bills",
                HttpMethod.POST,
                new HttpEntity<BillDTO>(billDTO, httpHeaders),
                BillDTO.class
        );

        return null;
    }
}
