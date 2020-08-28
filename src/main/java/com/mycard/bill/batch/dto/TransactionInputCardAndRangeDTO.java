package com.mycard.bill.batch.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class TransactionInputCardAndRangeDTO {
    private Long cardBin;
    private Long cardNumber;
    private LocalDate timeStampStart;
    private LocalDate timestampEnd;
}
