package com.tw.mall.controller;

import com.tw.mall.entity.OrderItem;
import com.tw.mall.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/orderItems")
public class OrderItemController {
    private final OrderItemService orderItemService;

    @Autowired
    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @PostMapping
    public ResponseEntity add(@RequestBody OrderItem orderItem) {

        OrderItem addOrderItem = orderItemService.add(orderItem);
        return ResponseEntity
                .created(URI.create("/orderItems/" + addOrderItem.getId())).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@RequestBody OrderItem orderItem,
                                 @PathVariable int id) {
        orderItemService.update(id, orderItem);
        return ResponseEntity
                .noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity remove(@PathVariable int id) {
        orderItemService.remove(id);
        return ResponseEntity.ok().build();
    }
}
