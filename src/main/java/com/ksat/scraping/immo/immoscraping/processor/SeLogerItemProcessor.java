package com.ksat.scraping.immo.immoscraping.processor;

import com.ksat.scraping.immo.immoscraping.dto.Property;

import org.jsoup.nodes.Element;
import org.springframework.batch.item.ItemProcessor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SeLogerItemProcessor implements ItemProcessor<Element, Property> {

    private static final String EURO = "€";
    private static final String SIZE = "m²";
    private static final String BEDROOM = "ch";
    private static final String ROOM = "p";
    private static final String DATA_TEST = "data-test";
    private static final String SE_LOGER = "Se Loger";
    private static final String SL_TAGS = "sl.tags";

    @Override
    public Property process(Element propertie) throws Exception {
        
        return Property.builder()
        .detailUrl(propertie.getElementsByAttributeValue("name","classified-link").attr("href"))
        .price(extractedPrice(propertie))
        .address(propertie.getElementsByAttributeValue(DATA_TEST,"sl.address").text())
        .description(propertie.getElementsByAttributeValue(DATA_TEST, "sl.classified-description").text())
        .nbRoom(extractedRooms(propertie))
        .nbBedroom(extractedBedRooms(propertie))
        .size(extractedSize(propertie))
        .provider(SE_LOGER)
        .build();
    }

    // position are moving, so we need a field catcher
    private int extractedPrice(Element propertie) {
        String value = propertie.getElementsByAttributeValue(DATA_TEST,"sl.price-label").text();
        return Integer.parseInt(value.replace(EURO, "").replace(" ", ""));
    }

    private int extractedRooms(Element propertie) {
        return extractField(propertie, ROOM);
    }

    private int extractedBedRooms(Element propertie) {
        return extractField(propertie, BEDROOM);
    }

    private double extractedSize(Element propertie) {
        double extractField = 0;
        try{
            extractField = extractFieldDouble(propertie, SIZE);
        } catch( Exception e){
            log.warn("no field {} found for property {} \n generated following error : {}", SIZE, propertie.toString(), e.getMessage());
        }
        return extractField;
    }

    private int  extractField(Element propertie, String field) {
        String value;
        int i = 0;
        do{
            value = propertie.getElementsByAttributeValue(DATA_TEST, SL_TAGS).first().child(i++).text();
        }while(!value.contains(field));
        return Integer.parseInt(value.replace(field,"").replace(" ", ""));
    }

    private double  extractFieldDouble(Element propertie, String field) {
        String value;
        int i = 0;
        do{
            value = propertie.getElementsByAttributeValue(DATA_TEST, SL_TAGS).first().child(i++).text();
        }while(!value.contains(field));
        return Double.parseDouble(value.replace(field,"").replace(" ", "").replace(",", "."));
    }
}
