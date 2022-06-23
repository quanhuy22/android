package com.example.test26_04.utils;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.test26_04.models.Order;

import java.util.ArrayList;

public class OrderItemViewModel extends ViewModel {
    private final MutableLiveData<Order> confirmedOrder =  new MutableLiveData<Order>();
    private final MutableLiveData<Order> cancelledOrder =  new MutableLiveData<Order>();
    private final MutableLiveData<Order> createdOrder = new MutableLiveData<Order>();

    public void setConfirmedOrder(Order order){
        confirmedOrder.setValue(order);
    }

    public void setCancelledOrder(Order order){
        cancelledOrder.setValue(order);
    }

    public LiveData<Order> getConfirmedOrder(){
        return confirmedOrder;
    }

    public LiveData<Order> getCancelledOrder(){
        return cancelledOrder;
    }

    public void setCreatedOrder(Order order) {
        createdOrder.setValue(order);
    }

    public LiveData<Order> getCreatedOrder(){
        return createdOrder;
    }

}
