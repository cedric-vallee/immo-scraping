package com.ksat.scraping.immo.immoscraping.config;

import javax.sql.DataSource;

import com.ksat.scraping.immo.immoscraping.dto.Property;
import com.ksat.scraping.immo.immoscraping.processor.SeLogerItemProcessor;
import com.ksat.scraping.immo.immoscraping.reader.SeLogerItemReader;

import org.jsoup.nodes.Element;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BatchConfiguration {

    @Bean
    public JdbcBatchItemWriter<Property> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Property>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO property (address, price, description, nb_room, nb_bedroom, size, provider, provider_id, detail_url) " 
                    + " VALUES (:address, :price, :description, :nbRoom, :nbBedroom, :size, :provider, :providerId, :detailUrl)")
                .dataSource(dataSource)
                .build();
    }

    @Bean
    public ItemStreamReader<Element> seLogerReader(){
        return new SeLogerItemReader();
    }

    @Bean ItemProcessor<Element, Property> seLogerProcessor()
    {
        return new SeLogerItemProcessor();
    }

    @Bean
    public Job importUserJob(JobBuilderFactory jobBuilderFactory, Step seLogerStep) {
        return jobBuilderFactory.get("immoScrapingJob")
            .incrementer(new RunIdIncrementer())
            .flow(seLogerStep)
            .end()
            .build();
    }

    @Bean
    public Step seLogerStep(StepBuilderFactory stepBuilderFactory, ItemStreamReader<Element> reader,
            ItemProcessor<Element, Property> processor, JdbcBatchItemWriter<Property> writer) {
        return stepBuilderFactory.get("seLogerStep")
            .<Element, Property>chunk(10)
            .reader(reader)
            .processor(processor)
            .writer(writer)
            .build();
    }

}
