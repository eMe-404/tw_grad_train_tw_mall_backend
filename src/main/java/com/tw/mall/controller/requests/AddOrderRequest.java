package com.tw.mall.controller.requests;

import com.tw.mall.entity.OrderItem;
import lombok.Data;

import java.util.List;

@Data
public class AddOrderRequest {
    List<OrderItem> orderItems;
}
