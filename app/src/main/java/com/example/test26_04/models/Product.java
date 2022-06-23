package com.example.test26_04.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Product implements Serializable {
    private String _id, name, description, categories, image;
    private float price;
    @SerializedName("import_price")
    private float importPrice;
    private int stock;

    public Product(String _id, String name, String description, String categories, String image, float importPrice, float price, int stock) {
        this.name = name;
        this._id = _id;
        this.description = description;
        this.categories = categories;
        this.image = image;
        this.importPrice = importPrice;
        this.price = price;
        this.stock = stock;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public float getImportPrice() {
        return importPrice;
    }

    public void setImportPrice(float importPrice) {
        this.importPrice = importPrice;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
