package com.example.test26_04.models;

import java.io.Serializable;
import java.util.ArrayList;

public class Order implements Serializable {
    private ArrayList<ProductIdAndQuantity> buyingProductList;
    private Customer customerInfo;
    private String status;
    private float totalPrice;
    private String _id = "";

    public Order(ArrayList<ProductIdAndQuantity> buyingProductList, Customer customerInfo, String status, float totalPrice) {
        this.buyingProductList = buyingProductList;
        this.customerInfo = customerInfo;
        this.status = status;
        this.totalPrice = totalPrice;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public ArrayList<ProductIdAndQuantity> getBuyingProductList() {
        return buyingProductList;
    }

    public void setBuyingProductList(ArrayList<ProductIdAndQuantity> buyingProductList) {
        this.buyingProductList = buyingProductList;
    }

    public Customer getCustomerInfo() {
        return customerInfo;
    }

    public void setCustomerInfo(Customer customerInfo) {
        this.customerInfo = customerInfo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }
}
