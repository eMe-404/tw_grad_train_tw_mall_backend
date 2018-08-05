package com.tw.mall.service;

import com.tw.mall.entity.OrderItem;
import com.tw.mall.repository.OrderItemRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class OrderItemServiceTest {
    @Mock
    private OrderItemRepository orderItemRepository;

    private OrderItemService orderItemService;

    @Before
    public void setUp() {
        orderItemService = new OrderItemService(orderItemRepository);
    }

    @Test
    public void should_correctly_add_orderItem_when_call_add() {
        //given
        OrderItem orderItem = new OrderItem();
        orderItem.setCount(5);
        given(orderItemRepository.save(orderItem)).willReturn(orderItem);
        //when
        OrderItem addedOrderItem = orderItemService.add(orderItem);
        //then
        assertThat(addedOrderItem).isEqualTo(orderItem);
    }

    @Test
    public void should_return_updated_orderItem_when_call_update() {
        //given
        Optional<OrderItem> orderItem = Optional.of(new OrderItem());
        orderItem.get().setCount(5);
        orderItem.get().setId(1);
        OrderItem orderItemNew = new OrderItem();
        orderItemNew.setCount(7);
        orderItemNew.setId(7);
        Integer id = 1;
        given(orderItemRepository.findById(id)).willReturn(orderItem);
        given(orderItemRepository.save(orderItemNew)).willReturn(orderItemNew);

        //when
        OrderItem updatedOrderItem = orderItemService.update(id, orderItemNew);

        //then
        assertThat(updatedOrderItem.getCount()).isEqualTo(orderItemNew.getCount());
        assertThat(updatedOrderItem.getId()).isEqualTo(orderItem.get().getId());
    }


    @Test
    public void should_correctly_delete_orderItem_when_call_delete() {
        //given

        //when

        //then
    }
}