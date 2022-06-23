package com.example.test26_04.models;

import java.io.Serializable;

public class Item implements Serializable {
    private int id;
    private String name, image;
    private int quantity;
    private float price;

    public Item(int id, String name, String image, int quantity, float price) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.quantity = quantity;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString(){
        return this.name;
    }
}
