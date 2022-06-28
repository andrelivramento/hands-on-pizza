package com.example.handsonpizza.service;

import com.example.handsonpizza.client.PizzaInventoryClient;
import com.example.handsonpizza.dataaccess.InMemoryDatabase;
import com.example.handsonpizza.model.OrderItem;
import com.example.handsonpizza.model.PizzaOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PizzaOrderService {
    private List<PizzaOrder> orders = InMemoryDatabase.DATABASE;

    private final PizzaInventoryClient pizzaInventoryClient;

    @Autowired
    public PizzaOrderService(PizzaInventoryClient pizzaInventoryClient) {
        this.pizzaInventoryClient = pizzaInventoryClient;
    }

    public PizzaOrder retrieveOrderById(int id) throws IllegalArgumentException {

        Optional<PizzaOrder> pizzaOrder = orders.stream().filter(order -> order.getId() == id).findFirst();

        if(pizzaOrder.isEmpty()) {
            throw new IllegalArgumentException();
        } else {
            return pizzaOrder.get();
        }

    }

    public List<PizzaOrder> retrieveOrdersByPizzaName(String pizzaName) {
        return orders
                .stream()
                .filter(order -> order
                        .getOrderItems()
                        .stream()
                        .allMatch(orderItem -> orderItem.getName().equals(pizzaName)))
                .collect(Collectors.toList());
    }

    public List<PizzaOrder> retrieveAllOrders() {
        return orders;
    }

    public PizzaOrder createNewOrder(List<OrderItem> orderItems) throws IllegalStateException {
        for(OrderItem orderItem : orderItems) {
            if(!pizzaInventoryClient.isAvailable(orderItem.getName())) {
                throw new IllegalStateException();
            }
        }
        int lastPizzaOrderId = Math.toIntExact(InMemoryDatabase.DATABASE.size());
        PizzaOrder pizzaOrder = new PizzaOrder(++lastPizzaOrderId,orderItems);
        InMemoryDatabase.DATABASE.add(pizzaOrder);

        return pizzaOrder;
    }
}
