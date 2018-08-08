package com.tw.mall.controller;

import com.tw.mall.controller.requests.AddOrderRequest;
import com.tw.mall.controller.response.ResponseOrder;
import com.tw.mall.entity.Order;
import com.tw.mall.login.JwtUser;
import com.tw.mall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

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
        Order addedOrder = orderService.add(addOrderRequest, getLoggedUser().getId());
        String URIstr = "/orders/" + addedOrder.getId();
        return ResponseEntity.created(URI.create(URIstr)).build();
    }

    @GetMapping
    private ResponseEntity<List<ResponseOrder>> getAll() {
        List<ResponseOrder> getOrderResponse = orderService.getAll(getLoggedUser().getId());
        return ResponseEntity.ok(getOrderResponse);
    }

    private JwtUser getLoggedUser() {
        return (JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
