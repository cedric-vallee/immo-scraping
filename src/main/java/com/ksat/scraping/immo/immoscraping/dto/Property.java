package com.ksat.scraping.immo.immoscraping.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Property {
    private int id;
    private String address;
    private int price;
    private String description;
    private int nbRoom;
    private int nbBedroom; 
    private double size;
    private String detailUrl;
    private String provider;
    private String providerId;
}
