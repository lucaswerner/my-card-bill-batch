package com.mycard.bill.batch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class BillBatchApplication {

    public static void main(String[] args) {
        System.exit(
                SpringApplication.exit(
                        new SpringApplicationBuilder(BillBatchApplication.class)
                                .web(WebApplicationType.NONE)
                                .run(args)
                )
        );
    }

}
