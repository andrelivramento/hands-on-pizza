package com.example.handsonpizza.client;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty("pizzadelivery.env.local")
public class LocalPizzaInventoryClient implements PizzaInventoryClient {
    @Override
    public boolean isAvailable(String pizzaName) {
        return true;
    }
}