package com.example.test26_04.models;

import java.io.Serializable;

public class ImportingProduct implements Serializable {
    private String productId;
    private int quantity;

    public ImportingProduct(String productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
