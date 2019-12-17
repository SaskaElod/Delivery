package com.example.szoftverprojekt;

import android.media.Image;

public class Product {
    String name;
    String price;
    String description;

    public Product(){

    }
    public  Product (String name,String price)
    {
        this.name=name;
        this.price=price;
    }

    public Product(String name, String price, String description){
        this.name=name;
        this.price=price;
        this.description=description;
    }


    public String getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }
}
