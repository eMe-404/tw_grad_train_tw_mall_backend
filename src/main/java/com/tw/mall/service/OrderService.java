package com.tw.mall.service;

import com.tw.mall.controller.requests.AddOrderRequest;
import com.tw.mall.controller.response.GetOrderResponse;
import com.tw.mall.entity.Order;
import com.tw.mall.entity.OrderItem;
import com.tw.mall.entity.Product;
import com.tw.mall.exeption.OrderItemNotFoundException;
import com.tw.mall.exeption.OrderNotFoundException;
import com.tw.mall.exeption.ProductNotFoundException;
import com.tw.mall.repository.OrderItemRepository;
import com.tw.mall.repository.OrderRepository;
import com.tw.mall.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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

        for (OrderItem orderItem : addOrderRequest.getOrderItems()) {
            Product product = productRepository
                    .findById(orderItem.getProductId())
                    .orElseThrow(ProductNotFoundException::new);
            totalPrice += orderItem.getCount() * product.getPrice();

        }
        toAddedOrder.setTotalPrice(totalPrice);
        return orderRepository.save(toAddedOrder);
    }


    public GetOrderResponse get(int id) {
        Order orderBefore = orderRepository.findById(id).orElseThrow(OrderNotFoundException::new);
        GetOrderResponse getOrderResponse = new GetOrderResponse();
        getOrderResponse.setOrderId(orderBefore.getId());
        getOrderResponse.setCreatedDate(orderBefore.getCreateDate());
        getOrderResponse.setOrderItems(orderBefore.getOrderItems());
        getOrderResponse.setTotalPrice(orderBefore.getTotalPrice());
        return getOrderResponse;

    }
}
