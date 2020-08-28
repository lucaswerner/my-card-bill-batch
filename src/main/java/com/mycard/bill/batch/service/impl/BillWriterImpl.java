package com.mycard.bill.batch.service.impl;

import com.mycard.bill.batch.dto.BillDTO;
import com.mycard.bill.batch.service.BillService;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillWriterImpl implements ItemWriter<BillDTO> {

    private final BillService billService;

    public BillWriterImpl(BillService billService) {
        this.billService = billService;
    }

    public void write(List<? extends BillDTO> list) {
        list.forEach(billService::postBill);
    }

}
