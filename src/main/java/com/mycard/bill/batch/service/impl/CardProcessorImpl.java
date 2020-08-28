package com.mycard.bill.batch.service.impl;

import com.mycard.bill.batch.dto.BillDTO;
import com.mycard.bill.batch.dto.CardDTO;
import com.mycard.bill.batch.dto.TransactionDTO;
import com.mycard.bill.batch.dto.TransactionInputCardAndRangeDTO;
import com.mycard.bill.batch.enumeration.TransactionType;
import com.mycard.bill.batch.service.ParameterService;
import com.mycard.bill.batch.service.TransactionService;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class CardProcessorImpl implements ItemProcessor<CardDTO, BillDTO> {

    private final TransactionService transactionService;
    private final ParameterService parameterService;

    public CardProcessorImpl(TransactionService transactionService, ParameterService parameterService) {
        this.transactionService = transactionService;
        this.parameterService = parameterService;
    }

    @Override
    public BillDTO process(CardDTO cardDTO) {
        final Long cardBin = cardDTO.getBin();
        final Long cardNumber = cardDTO.getNumber();
        final LocalDate processingDt = parameterService.getProcessingDt();

        final List<TransactionDTO> transactionList = transactionService.getTransactionListByCardAndDtRange(
                TransactionInputCardAndRangeDTO.builder()
                        .cardBin(cardBin)
                        .cardNumber(cardNumber)
                        .timeStampStart(parameterService.getTransactionDtRangeStart())
                        .timestampEnd(parameterService.getTransactionDtRangeEnd())
                        .build()
        );

        final BigDecimal creditAmount = findTransactionByTypeAndSum(transactionList, TransactionType.CREDIT);
        final BigDecimal refundAmount = findTransactionByTypeAndSum(transactionList, TransactionType.REFUND);

        return BillDTO
                .builder()
                .cardBin(cardBin)
                .cardNumber(cardNumber)
                .month(processingDt.getMonth())
                .year(Integer.valueOf(processingDt.getYear()).shortValue())
                .value(creditAmount.subtract(refundAmount).doubleValue())
                .build();
    }

    private BigDecimal findTransactionByTypeAndSum(List<TransactionDTO> transactionList, TransactionType type) {
        return transactionList
                .stream()
                .filter(transaction -> transactionsHasDefinedType(transaction, type))
                .map(transactionDTO -> BigDecimal.valueOf(transactionDTO.getValue()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private boolean transactionsHasDefinedType(TransactionDTO transactionDTO, TransactionType type) {
        return type.equals(transactionDTO.getTransactionType());
    }
}
