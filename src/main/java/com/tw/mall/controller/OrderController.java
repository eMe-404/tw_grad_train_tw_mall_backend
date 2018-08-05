package com.tw.mall.controller;

import com.tw.mall.controller.requests.AddOrderRequest;
import com.tw.mall.entity.Order;
import com.tw.mall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    private ResponseEntity add(@RequestBody AddOrderRequest addOrderRequest) {
        Order addedOrder = orderService.add(addOrderRequest);
        return ResponseEntity.created(URI.create("/orders" + addedOrder.getId())).build();
    }

    @GetMapping
    private ResponseEntity<Order> get() {
        return null;
    }
}
