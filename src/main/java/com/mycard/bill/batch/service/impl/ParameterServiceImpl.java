package com.mycard.bill.batch.service.impl;

import com.mycard.bill.batch.service.ParameterService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class ParameterServiceImpl implements ParameterService {

    private final LocalDate processingDt;
    private final LocalDate transactionDtRangeStart;
    private final LocalDate transactionDtRangeEnd;
    private final Byte billDay;
    private final Integer chunkSize;

    public ParameterServiceImpl() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.processingDt = LocalDate.parse(System.getProperty("processingDt"), formatter);
        this.transactionDtRangeEnd = this.processingDt.minusDays(10);
        this.transactionDtRangeStart = this.processingDt.minusMonths(1);
        this.billDay = Integer.valueOf(processingDt.getDayOfMonth()).byteValue();
        this.chunkSize = Integer.valueOf(System.getProperty("chunkSize"));
    }

    public LocalDate getProcessingDt() {
        return this.processingDt;
    }

    @Override
    public LocalDate getTransactionDtRangeStart() {
        return this.transactionDtRangeStart;
    }

    @Override
    public LocalDate getTransactionDtRangeEnd() {
        return this.transactionDtRangeEnd;
    }

    @Override
    public Byte getBillDay() {
        return this.billDay;
    }

    @Override
    public Integer getChunkSize() {
        return this.chunkSize;
    }
}
