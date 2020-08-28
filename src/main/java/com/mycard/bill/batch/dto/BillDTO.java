package com.mycard.bill.batch.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Month;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BillDTO {
    private Long cardBin;
    private Long cardNumber;
    private Month month;
    private Short year;
    private Double value;
}
