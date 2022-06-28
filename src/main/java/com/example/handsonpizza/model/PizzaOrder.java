package com.example.handsonpizza.model;

import java.util.List;

public class PizzaOrder {
    private int id;
    private List<OrderItem> orderItems;

    public PizzaOrder(int id, List<OrderItem> orderItems) {
        this.id = id;
        this.orderItems = orderItems;
    }

    public int getId() {
        return id;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }
}
