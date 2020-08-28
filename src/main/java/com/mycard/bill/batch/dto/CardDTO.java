package com.mycard.bill.batch.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class CardDTO {
    private Long bin;
    private Long number;
    private LocalDate expiration;
    private LocalDateTime validFrom;
    private LocalDate billDt;
    private Long userId;
}
