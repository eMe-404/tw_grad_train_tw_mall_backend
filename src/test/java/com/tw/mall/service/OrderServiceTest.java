package com.tw.mall.service;

import com.tw.mall.controller.requests.AddOrderRequest;
import com.tw.mall.entity.Order;
import com.tw.mall.repository.OrderRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    private OrderService orderService;

    @Before
    public void setUp() {
        orderService = new OrderService(orderRepository);
    }

    @Test
    public void add_returnOrderInfo() {
        AddOrderRequest addOrderRequest = new AddOrderRequest();
        Order order = new Order();
        order.setCreateDate(new Date());
        order.setTotalPrice(12);
        given(orderRepository.save(any())).willReturn(order);

        Order addedOrder = orderService.add(addOrderRequest);
        assertThat(addedOrder.getTotalPrice()).isEqualTo(12);
    }


}