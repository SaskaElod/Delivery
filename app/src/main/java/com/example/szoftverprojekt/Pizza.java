package com.example.szoftverprojekt;

import android.media.Image;

public class Pizza {
    String name;
    String price;
    String description;

    public Pizza(){

    }

    public Pizza(String name,String price,String description){
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
