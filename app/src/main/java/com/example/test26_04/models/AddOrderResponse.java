package com.example.test26_04.models;

import java.io.Serializable;

public class AddOrderResponse implements Serializable {
    private String message;
    private Order order;

    public AddOrderResponse(String message, Order order) {
        this.message = message;
        this.order = order;
    }

    public String getMessage() {
        return message;
    }

    public Order getOrder() {
        return order;
    }
}
