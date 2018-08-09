package com.tw.mall.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class OrderVO {
    private int id;
    private Date createDate;
    private double totalPrice;
    List<OrderItemVO> orderItems = new ArrayList<>();
    private Long userId;
}
