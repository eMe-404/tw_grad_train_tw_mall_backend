package com.tw.mall.service;

import com.tw.mall.controller.requests.AddOrderRequest;
import com.tw.mall.controller.response.ResponseOrder;
import com.tw.mall.controller.response.ResponseOrderItem;
import com.tw.mall.entity.Order;
import com.tw.mall.entity.OrderItem;
import com.tw.mall.entity.Product;
import com.tw.mall.exeption.ProductNotFoundException;
import com.tw.mall.repository.OrderItemRepository;
import com.tw.mall.repository.OrderRepository;
import com.tw.mall.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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


    public Order add(AddOrderRequest addOrderRequest, Long userId) {

        Order toAddedOrder = new Order();
        double totalPrice = 0;
        toAddedOrder.setCreateDate(new Date());
        toAddedOrder.setUserId(userId);
        orderRepository.save(toAddedOrder);
        for (OrderItem orderItem : addOrderRequest.getOrderItems()) {
            totalPrice = getTotalPrice(totalPrice, orderItem);
            orderItem.setOrderId(toAddedOrder.getId());
            orderItemRepository.save(orderItem);
        }
        toAddedOrder.setTotalPrice(totalPrice);
        return orderRepository.save(toAddedOrder);
    }

    public List<ResponseOrder> getAll(Long userId) {
        return orderRepository
                .findByUserId(userId)
                .stream()
                .map(this::mapOrderToResponseOrder)
                .collect(Collectors.toList());
    }

    private ResponseOrder mapOrderToResponseOrder(Order order) {
        ResponseOrder responseOrder = new ResponseOrder();
        responseOrder.setCreateDate(order.getCreateDate());
        responseOrder.setId(order.getId());
        responseOrder.setTotalPrice(order.getTotalPrice());
        responseOrder.setUserId(order.getUserId());
        List<ResponseOrderItem> responseOrderItems = order
                .getOrderItems()
                .stream()
                .map(this::mapOrderItemToResponseOrderItem)
                .collect(Collectors.toList());
        responseOrder.setResponseOrderItems(responseOrderItems);
        return responseOrder;
    }

    private ResponseOrderItem mapOrderItemToResponseOrderItem(OrderItem orderItem) {
        ResponseOrderItem responseOrderItem = new ResponseOrderItem();
        responseOrderItem.setCount(orderItem.getCount());
        RestTemplate restTemplate = new RestTemplate();
        Product receivedProduct = restTemplate.getForObject("http://localhost:8888/products/" + orderItem.getProductId(), Product.class);
        responseOrderItem.setProduct(receivedProduct);
        return responseOrderItem;
    }

    private double getTotalPrice(double totalPrice, OrderItem orderItem) {
        RestTemplate restTemplate = new RestTemplate();


        Product receivedProduct = restTemplate.getForObject("http://localhost:8888/products/" + orderItem.getProductId(), Product.class);
        assert receivedProduct != null;
        totalPrice += orderItem.getCount() * receivedProduct.getPrice();
        return totalPrice;
    }


}
