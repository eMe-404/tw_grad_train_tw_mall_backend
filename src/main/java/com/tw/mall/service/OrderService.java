package com.tw.mall.service;

import com.tw.mall.controller.requests.AddOrderRequest;
import com.tw.mall.entity.Order;
import com.tw.mall.entity.OrderItem;
import com.tw.mall.entity.Product;
import com.tw.mall.exeption.OrderNotFoundException;
import com.tw.mall.exeption.ProductNotFoundException;
import com.tw.mall.repository.OrderItemRepository;
import com.tw.mall.repository.OrderRepository;
import com.tw.mall.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OrderService {
    private OrderRepository orderRepository;
    private ProductRepository productRepository;
    private OrderItemRepository orderItemRepository;


    @Autowired
    public OrderService(OrderRepository orderRepository, ProductRepository productRepository, OrderItemRepository orderItemRepository) {

        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.orderItemRepository = orderItemRepository;
    }

    public Order add(AddOrderRequest addOrderRequest) {
        Order toAddedOrder = new Order();
        double totalPrice = 0;
        toAddedOrder.setCreateDate(new Date());
        orderRepository.save(toAddedOrder);
        for (OrderItem orderItem : addOrderRequest.getOrderItems()) {
            OrderItem orderItemNew = new OrderItem();
            orderItemNew.setOrderId(toAddedOrder.getId());
            orderItemNew.setCount(orderItem.getCount());
            orderItemNew.setProductId(orderItem.getProductId());
            orderItemRepository.save(orderItemNew);

        }
        toAddedOrder.setTotalPrice(totalPrice);
        return orderRepository.save(toAddedOrder);
    }


    public Order get(int id) {
        return orderRepository.findById(id).orElseThrow(OrderNotFoundException::new);

    }
}
