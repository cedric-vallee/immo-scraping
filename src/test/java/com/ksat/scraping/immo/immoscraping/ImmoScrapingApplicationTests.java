package com.ksat.scraping.immo.immoscraping;

import static org.junit.Assert.fail;

import java.util.ArrayList;

import com.ksat.scraping.immo.immoscraping.dto.Property;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.test.MetaDataInstanceFactory;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;

@RunWith(SpringRunner.class)
@SpringBatchTest
@ContextConfiguration(classes = { ImmoScrapingApplication.class })
public class ImmoScrapingApplicationTests {

    @Autowired
    private JdbcBatchItemWriter<Property> writer;

    public StepExecution getStepExecution() {
        StepExecution execution = MetaDataInstanceFactory.createStepExecution();
        execution.getExecutionContext();
        return execution;
    }

    @Test
    public void testWriter() {
        var list = new ArrayList<Property>();
        var property = Property.builder().address("address").price(3).description("description").nbRoom(4).nbBedroom(2)
                .size(50).detailUrl("url").provider("provider").providerId("providerId").build();
        list.add(property);
        try {
            writer.write(list);
        } catch (Exception e) {
            fail();
        }
    }
    
}
