package com.mycard.bill.batch.service;

import java.time.LocalDate;

public interface ParameterService {

    LocalDate getProcessingDt();

    LocalDate getTransactionDtRangeStart();

    LocalDate getTransactionDtRangeEnd();

    Byte getBillDay();

    Integer getChunkSize();

}
