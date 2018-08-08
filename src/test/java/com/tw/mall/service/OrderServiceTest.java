package com.tw.mall.service;

import com.tw.mall.controller.requests.AddOrderRequest;
import com.tw.mall.controller.response.GetOrderResponse;
import com.tw.mall.controller.response.ResponseOrder;
import com.tw.mall.entity.Order;
import com.tw.mall.repository.OrderItemRepository;
import com.tw.mall.repository.OrderRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;


    @Mock
    private OrderItemRepository orderItemRepository;

    private OrderService orderService;

    @Before
    public void setUp() {
        orderService = new OrderService(orderRepository, orderItemRepository);
    }

    @Test
    public void add_returnOrderInfo() {
        AddOrderRequest addOrderRequest = new AddOrderRequest();
        Order order = new Order();
        order.setCreateDate(new Date());
        order.setTotalPrice(12);
        Long userId = 1L;
        given(orderRepository.save(any())).willReturn(order);

        Order addedOrder = orderService.add(addOrderRequest, userId);
        assertThat(addedOrder.getTotalPrice()).isEqualTo(12);
    }

    @Test
    public void should_return_correct_order_when_call_get() {
        //given
        GetOrderResponse getOrderResponse = new GetOrderResponse();
        getOrderResponse.setTotalPrice(100);
        Long id = 1L;

        Optional<Order> order = Optional.of(new Order());
        order.get().setTotalPrice(199);
        given(orderRepository.findById(1)).willReturn(order);
        //when
        List<ResponseOrder> getOrderResponse2 = orderService.getAll(id);
        //then
        assertThat(getOrderResponse2.get(0).getTotalPrice()).isEqualTo(199);
    }

}