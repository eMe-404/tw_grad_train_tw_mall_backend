package com.tw.mall.controller.response;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class ResponseOrder {
    private int id;
    private Date createDate;
    private double totalPrice;
    List<ResponseOrderItem> responseOrderItems = new ArrayList<>();
    private Long userId;

}
