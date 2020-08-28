package com.mycard.bill.batch.service.impl;

import com.mycard.bill.batch.dto.CardDTO;
import com.mycard.bill.batch.service.CardService;
import com.mycard.bill.batch.service.ParameterService;
import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CardReaderImpl implements ItemReader<CardDTO> {

    private final int pageSize;
    private final CardService cardService;

    private int pageNumber;
    private int cardIndex;
    private List<CardDTO> cardDTOList;

    public CardReaderImpl(CardService cardService, ParameterService parameterService) {
        this.cardService = cardService;
        this.pageSize = parameterService.getChunkSize();
        this.cardDTOList = new ArrayList<>();
    }

    @Override
    public CardDTO read() {
        if (cardIndex == cardDTOList.size()) {
            cardDTOList = cardService.findAll(pageSize, pageNumber).getContent();
            pageNumber++;
            cardIndex = 0;

            if (cardDTOList.isEmpty()) {
                return null;
            }
        }

        final CardDTO cardDTO = cardDTOList.get(cardIndex);
        cardIndex++;
        return cardDTO;
    }
}
