package com.mycard.bill.batch.dto;

import com.mycard.bill.batch.enumeration.TransactionType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TransactionDTO {
    private Long id;
    private TransactionType transactionType;
    private Long userId;
    private Long cardBin;
    private Long cardNumber;
    private double value;
    private LocalDateTime timestamp;
}
