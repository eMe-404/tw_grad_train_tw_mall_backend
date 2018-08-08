package com.tw.mall.controller.response;

import com.tw.mall.controller.VO.Product;
import lombok.Data;

@Data
public class ResponseOrderItem {
    private Product product;
    private int count;
}
