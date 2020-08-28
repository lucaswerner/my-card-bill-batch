package com.mycard.bill.batch.service;

import com.mycard.bill.batch.dto.CardPageDTO;

public interface CardService {

    CardPageDTO findAll(int pageSize, int pageNumber);

}
