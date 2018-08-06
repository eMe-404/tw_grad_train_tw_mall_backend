package com.tw.mall.controller.response;

import com.tw.mall.entity.OrderItem;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class GetOrderResponse {
    private int orderId;
    private Date createdDate;
    private double totalPrice;
    List<OrderItem> orderItems;
}
