package com.example.handsonpizza.controller;

import com.example.handsonpizza.model.OrderItem;
import com.example.handsonpizza.model.PizzaOrder;
import com.example.handsonpizza.service.PizzaOrderService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Collection;

import static org.mockito.Mockito.when;

@WebMvcTest
public class PizzaOrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PizzaOrderService pizzaOrderService;

    @Test
    @Disabled
    public void singleOrderReturnOK() throws Exception {
        when(pizzaOrderService.retrieveOrderById(1)).thenReturn(new PizzaOrder(1, new ArrayList<>((Collection) new OrderItem("Margherita", 2))));
        //mockMvc.perform(get("/orderId/1"));
    }
}
