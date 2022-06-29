package com.example.handsonpizza.service;

import com.example.handsonpizza.client.PizzaInventoryClient;
import com.example.handsonpizza.dataaccess.PizzaOrderRepository;
import com.example.handsonpizza.model.OrderItem;
import com.example.handsonpizza.model.PizzaOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PizzaOrderService {
    private final PizzaInventoryClient pizzaInventoryClient;

    private final PizzaOrderRepository pizzaOrderRepository;

    @Autowired
    public PizzaOrderService(PizzaInventoryClient pizzaInventoryClient, PizzaOrderRepository pizzaOrderRepository) {
        this.pizzaInventoryClient = pizzaInventoryClient;
        this.pizzaOrderRepository = pizzaOrderRepository;
    }

    public PizzaOrder retrieveOrderById(int id) throws IllegalArgumentException {

        Optional<PizzaOrder> pizzaOrder = pizzaOrderRepository.findById(id);

        if(pizzaOrder.isEmpty()) {
            throw new IllegalArgumentException();
        } else {
            return pizzaOrder.get();
        }

    }

    public List<PizzaOrder> retrieveOrdersByPizzaName(String pizzaName) {
        return pizzaOrderRepository.findAll()
                .stream()
                .filter(order -> order
                        .getOrderItems()
                        .stream()
                        .allMatch(orderItem -> orderItem.getName().equals(pizzaName)))
                .collect(Collectors.toList());
    }

    public List<PizzaOrder> retrieveAllOrders() {
        return pizzaOrderRepository.findAll();
    }

    public PizzaOrder createNewOrder(List<OrderItem> orderItems) throws IllegalStateException {
        for(OrderItem orderItem : orderItems) {
            if(!pizzaInventoryClient.isAvailable(orderItem.getName())) {
                throw new IllegalStateException();
            }
        }

        int lastPizzaOrderId = Math.toIntExact(pizzaOrderRepository.count());
        PizzaOrder pizzaOrder = new PizzaOrder(++lastPizzaOrderId,orderItems);
        pizzaOrderRepository.save(pizzaOrder);

        return pizzaOrder;
    }
}
