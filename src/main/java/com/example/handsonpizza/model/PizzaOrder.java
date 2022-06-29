package com.example.handsonpizza.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "pizza_order")
public class PizzaOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    @Column(name = "order_id")
    private List<OrderItem> orderItems;

    public PizzaOrder() {
    }

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
