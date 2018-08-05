package com.tw.mall.service;

import com.tw.mall.controller.requests.AddOrderRequest;
import com.tw.mall.entity.Order;
import com.tw.mall.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OrderService {
    private OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {

        this.orderRepository = orderRepository;
    }

    public Order add(AddOrderRequest addOrderRequest) {
        Order toAddedOrder = new Order();
        toAddedOrder.setOrderItems(addOrderRequest.getOrderItems());
        toAddedOrder.setCreateDate(new Date());
        toAddedOrder.setTotalPrice(12);
        return orderRepository.save(toAddedOrder);
    }
}
