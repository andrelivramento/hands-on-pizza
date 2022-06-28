package com.example.handsonpizza.client;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Component;
import java.util.Random;

@Component
@ConditionalOnExpression("not(${pizzadelivery.env.local})")
public class RemotePizzaInventoryClient implements PizzaInventoryClient{
    @Override
    public boolean isAvailable(String pizzaName) {
        Random random = new Random();
        return random.nextBoolean();
    }
}