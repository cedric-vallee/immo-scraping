package com.ksat.scraping.immo.immoscraping;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.task.configuration.EnableTask;

@EnableTask
@SpringBootApplication
@EnableBatchProcessing
public class ImmoScrapingApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImmoScrapingApplication.class, args);
	}

}