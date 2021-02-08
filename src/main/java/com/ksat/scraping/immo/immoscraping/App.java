package com.ksat.scraping.immo.immoscraping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.ksat.scraping.immo.immoscraping.dto.Property;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class App 
{
    private static final String SE_LOGER = "se Loger";
    private static final int PROPERTIES_BY_PAGE = 25;
    private static final String SELOGER_SEARCH_CRITERIA_URL = "https://www.seloger.com/list.htm?projects=2&types=1,2&natures=1,2,4"
                                +"&places=%5b%7b%22inseeCodes%22:%5b750056%5d%7d%5d&proximities=0,10&price=NaN/550000&surface=55/NaN"
                                +"&bedrooms=2,3,4,5&rooms=3,4,5&enterprise=0&qsVersion=1.0&m=search_refine";
    private static final String SL_TAGS = "sl.tags";
    private static final String DATA_TEST = "data-test";
    private static final String PAGE_SELECTOR= "&LISTING-LISTpg=";

    // public static void main( String[] args )
    // {
    //     try {

    //         var propertiesList = new ArrayList<Property>();

    //         // Here we create a document object and use JSoup to fetch the website
    //         Document doc = Jsoup.connect(SELOGER_SEARCH_CRITERIA_URL).get();

    //         int nbPage = initNbPage(doc);

    //         //TODO loop on all pages
                 
    //         processor(propertiesList, doc);

    //         propertiesList.forEach(System.out::println);
     
    //       // In case of any IO errors, we want the messages written to the console
    //       } catch (IOException e) {
    //         e.printStackTrace();
    //       }
    // }

    // private static int initNbPage(Document doc) {
    //     Element element = doc.getElementsByAttributeValue(DATA_TEST, "sl.status-container").first();
    //     int nbProperties = Integer.parseInt(element.childNode(8).toString());
    //     int nbPage = nbProperties / PROPERTIES_BY_PAGE;
    //     int spartPage = nbProperties % PROPERTIES_BY_PAGE > 0 ? 1: 0;
    //     return nbPage + spartPage;
    // }

    // private static void processor(List<Property> propertiesList, Document doc) {
    //     // Get the list of properties
    //     Elements properties = doc.getElementsByAttributeValue(DATA_TEST, "sl.card-container");
    //     if(properties != null){
    //         for (Element propertie : properties){
    //             propertiesList.add(
    //                 Property.builder()
    //                 .detailUrl(propertie.getElementsByAttributeValue("name","classified-link").attr("href"))
    //                 .price(propertie.getElementsByAttributeValue(DATA_TEST,"sl.price-label").text())
    //                 .address(propertie.getElementsByAttributeValue(DATA_TEST,"sl.address").text())
    //                 .description(propertie.getElementsByAttributeValue(DATA_TEST, "sl.classified-description").text())
    //                 .nbRoom(propertie.getElementsByAttributeValue(DATA_TEST, SL_TAGS).first().child(0).text())
    //                 .nbBedroom(propertie.getElementsByAttributeValue(DATA_TEST, SL_TAGS).first().child(1).text())
    //                 .size(propertie.getElementsByAttributeValue(DATA_TEST, SL_TAGS).first().child(2).text())
    //                 .provider(SE_LOGER)
    //                 .build()
    //             );
    //         }
    //     } else {
    //         System.err.println("no property");
    //     }
    // }
}
