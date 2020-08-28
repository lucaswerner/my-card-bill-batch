package com.mycard.bill.batch.service;

import com.mycard.bill.batch.dto.TransactionDTO;
import com.mycard.bill.batch.dto.TransactionInputCardAndRangeDTO;

import java.util.List;

public interface TransactionService {

    List<TransactionDTO> getTransactionListByCardAndDtRange(TransactionInputCardAndRangeDTO transactionInputCardAndRangeDTO);

}
