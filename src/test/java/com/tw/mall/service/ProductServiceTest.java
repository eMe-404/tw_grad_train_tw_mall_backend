package com.tw.mall.service;

import com.tw.mall.entity.Product;
import com.tw.mall.repository.ProductRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    @Mock
    ProductRepository productRepository;

    private ProductService productService;

    @Before
    public void setUp() {
        productService = new ProductService(productRepository);
    }

    @Test
    public void should_return_all_product_when_call_getAll() {
        //given
        List<Product> products = new ArrayList<>(3);
        Product product = new Product();
        product.setName("test");
        products.add(product);
        products.add(product);
        products.add(product);
        given(productRepository.findAll()).willReturn(products);
        //when
        List<Product> allProduct = productService.getAll();
        //then
        assertThat(allProduct.size()).isEqualTo(3);
    }

    @Test
    public void should_correctly_add_product_when_call_add() {
        //given
        Product product = new Product();
        product.setName("test");
        given(productRepository.save(product)).willReturn(product);
        //when
        productService.add(product);
        //then

    }
}