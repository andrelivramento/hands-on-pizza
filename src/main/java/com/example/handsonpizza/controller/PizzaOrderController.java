package com.example.handsonpizza.controller;

import com.example.handsonpizza.model.OrderItem;
import com.example.handsonpizza.model.PizzaOrder;
import com.example.handsonpizza.service.PizzaOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("pizza-orders")
public class PizzaOrderController {

    private final PizzaOrderService pizzaOrderService;

    @Autowired
    public PizzaOrderController(PizzaOrderService pizzaOrderService) {
        this.pizzaOrderService = pizzaOrderService;
    }

    @GetMapping("")
    public ResponseEntity<List<PizzaOrder>> getAllOrders() {
        return new ResponseEntity<>(pizzaOrderService.retrieveAllOrders(), HttpStatus.OK);
    }

    @GetMapping("/orderId/{id}")
    public ResponseEntity<PizzaOrder> getPizzaOrderById(@PathVariable int id) {
        return new ResponseEntity<>(pizzaOrderService.retrieveOrderById(id), HttpStatus.OK);
    }

    @GetMapping("/pizzaName/{pizzaName}")
    public ResponseEntity<List<PizzaOrder>> getOrdersByPizzaName(@PathVariable String pizzaName) {
        return new ResponseEntity<>(pizzaOrderService.retrieveOrdersByPizzaName(pizzaName), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<PizzaOrder> createNewPizzaOrder(@RequestBody List<OrderItem> orderItems) {
        return new ResponseEntity<>(pizzaOrderService.createNewOrder(orderItems), HttpStatus.OK);
    }
}