package com.mycard.bill.batch.dto;

import lombok.Data;

import java.util.List;

@Data
public class CardPageDTO {
    private List<CardDTO> content;
}
