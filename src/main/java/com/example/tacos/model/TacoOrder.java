package com.example.tacos.model;

import lombok.Data;

@Data
public class TacoOrder {

    private String name;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String ccNumber;
    private String ccExpiration;
    private String ccCVV;

    public TacoOrder() {

    }

}