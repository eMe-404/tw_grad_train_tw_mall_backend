package com.tw.mall.vo;

import lombok.Data;

@Data
class OrderItemVO {
    private int id;
    private int orderId;
    private int productId;

    private int count;
}
