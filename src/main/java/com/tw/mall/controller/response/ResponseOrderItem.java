package com.tw.mall.controller.response;

import com.tw.mall.entity.Product;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
public class ResponseOrderItem {
    private Product product;
    private int count;
}
