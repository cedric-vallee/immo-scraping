package com.ksat.scraping.immo.immoscraping.reader;

import java.io.IOException;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SeLogerItemReader implements ItemStreamReader<Element> {

    private static final int PROPERTIES_BY_PAGE = 25;
    private static final String SELOGER_SEARCH_CRITERIA_URL = "https://www.seloger.com/list.htm?projects=2&types=1,2&natures=1,2,4"
            + "&places=%5b%7b%22inseeCodes%22:%5b750056%5d%7d%5d&proximities=0,10&price=NaN/510000&surface=55/NaN"
            + "&bedrooms=2,3,4,5&rooms=3,4,5&enterprise=0&qsVersion=1.0&m=search_refine";
    private static final String PAGE_SELECTOR = "&LISTING-LISTpg=";
    private static final String DATA_TEST = "data-test";

    private int nbPage;
    private Iterator<Element> propertiesIterator;

    @Override
    public void open(ExecutionContext executionContext) throws ItemStreamException {
        // Here we create a document object and use JSoup to fetch the website
        Document doc;
        try {
            doc = Jsoup.connect(SELOGER_SEARCH_CRITERIA_URL).get();
        } catch (IOException e) {
            throw new ItemStreamException(e.getMessage(), e);
        }
        nbPage = initNbPage(doc);

        // Get the list of properties
        Elements properties = doc.getElementsByAttributeValue(DATA_TEST, "sl.card-container");
        propertiesIterator = properties.iterator();

    }

    @Override
    public void update(ExecutionContext executionContext) throws ItemStreamException {
        log.debug("update se loger reader");
    }

    @Override
    public void close() throws ItemStreamException {
        log.debug("close se loger reader");
    }

    @Override
    public Element read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        // TODO implement browsing other pages
        return propertiesIterator.hasNext()? propertiesIterator.next() : null;
    }

    private static int initNbPage(Document doc) {
        Element element = doc.getElementsByAttributeValue(DATA_TEST, "sl.status-container").first();
        int nbProperties = Integer.parseInt(element.childNode(8).toString());
        int nbPage = nbProperties / PROPERTIES_BY_PAGE;
        int spartPage = nbProperties % PROPERTIES_BY_PAGE > 0 ? 1: 0;
        return nbPage + spartPage;
    }
    
}
