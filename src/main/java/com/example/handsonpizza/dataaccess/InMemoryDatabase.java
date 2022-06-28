package com.example.handsonpizza.dataaccess;

import com.example.handsonpizza.model.OrderItem;
import com.example.handsonpizza.model.PizzaOrder;

import java.util.*;

public class InMemoryDatabase {
    public static final List<PizzaOrder> DATABASE = new ArrayList<>() {{
        OrderItem margherita2 = new OrderItem("Margherita", 2);
        OrderItem napoli2 = new OrderItem("Margherita", 2);
        OrderItem margherita1 = new OrderItem("Margherita", 1);
        OrderItem calzone3 = new OrderItem("Calzone", 3);

        PizzaOrder order1 = new PizzaOrder(1, Arrays.asList(margherita1, napoli2));
        PizzaOrder order2 = new PizzaOrder(2, Arrays.asList(margherita2));
        PizzaOrder order3 = new PizzaOrder(3, Arrays.asList(calzone3));

        add(order1);
        add(order2);
        add(order3);
    }};
}
