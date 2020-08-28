package com.mycard.bill.batch.config;

import com.mycard.bill.batch.dto.BillDTO;
import com.mycard.bill.batch.dto.CardDTO;
import com.mycard.bill.batch.service.ParameterService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final ParameterService parameterService;
    private final ItemReader<CardDTO> cardReader;
    private final ItemProcessor<CardDTO, BillDTO> cardProcessor;
    private final ItemWriter<BillDTO> billWriter;

    public BatchConfig(JobBuilderFactory jobBuilderFactory,
                       StepBuilderFactory stepBuilderFactory,
                       ParameterService parameterService,
                       ItemReader<CardDTO> cardReader,
                       ItemProcessor<CardDTO, BillDTO> cardProcessor,
                       ItemWriter<BillDTO> billWriter) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.parameterService = parameterService;
        this.cardReader = cardReader;
        this.cardProcessor = cardProcessor;
        this.billWriter = billWriter;
    }

    @Bean
    public Job billJob() {
        return jobBuilderFactory.get("billJobT")
                .flow(billStep())
                .end()
                .build();
    }

    @Bean
    public Step billStep() {
        return stepBuilderFactory.get("billStepT")
                .<CardDTO, BillDTO>chunk(parameterService.getChunkSize())
                .reader(cardReader)
                .processor(cardProcessor)
                .writer(billWriter)
                .build();
    }
}
