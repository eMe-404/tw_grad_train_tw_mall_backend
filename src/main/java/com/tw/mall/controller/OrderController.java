package com.tw.mall.controller;

import com.tw.mall.controller.requests.AddOrderRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @PostMapping
    private ResponseEntity add(@RequestBody AddOrderRequest addOrderRequest) {
        return null;
    }
}
