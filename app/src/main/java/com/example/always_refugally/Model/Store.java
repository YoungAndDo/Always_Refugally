package com.example.always_refugally.Model;

import java.io.Serializable;

/**
 * Created by yd199 on 2016-11-18.
 */

public class Store implements Serializable{
    int id;
    int logo;
    String name;
    String address;
    String distance;
    int price;

    public int getLogo() {
        return logo;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getAddress() {
        return address;
    }

    public String getDistance() {
        return distance;
    }
        public Store(int logo, String name, String address, String distance, int price){
        this.logo = logo;
        this.name = name;
        this.address = address;
        this.distance = distance;
        this.price = price;
    }
}
