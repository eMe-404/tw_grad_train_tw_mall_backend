package com.tw.mall.apitest;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseSetups;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.annotation.ExpectedDatabases;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.tw.mall.controller.requests.AddOrderRequest;
import com.tw.mall.entity.Order;
import com.tw.mall.entity.OrderItem;
import io.restassured.http.ContentType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@ContextConfiguration
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@ActiveProfiles("test")
public class OrderControllerTest {
    @LocalServerPort
    private int port;

    @Test
    @DatabaseSetup("classpath:/order_data/orderSampleData.xml")
    public void should_return_specified_order_when_call_get() {
        given()
                .port(port)
                .when()
                .get("/orders/1")
                .then()
                .statusCode(200)
                .body("totalPrice", equalTo(666f));
    }

    @Test
    @DatabaseSetups({
            @DatabaseSetup("classpath:/order_data/orderSampleData.xml"),
            @DatabaseSetup("classpath:/order_item_data/orderItemInitData.xml")})
    @ExpectedDatabases({
            @ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT, value = "classpath:/order_data/expectedExpectedData.xml"),
            @ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT, value = "classpath:/order_item_data/orderItemExpectedAdd.xml")})
    public void should_create_when_call_add() {
        AddOrderRequest addOrderRequest = new AddOrderRequest();
        OrderItem orderItem1 = new OrderItem();
        orderItem1.setProductId(1);
        orderItem1.setCount(5);
        OrderItem orderItem2 = new OrderItem();
        orderItem1.setProductId(2);
        orderItem1.setCount(11);
        List<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(orderItem1);
        orderItems.add(orderItem2);
        addOrderRequest.setOrderItems(orderItems);
        given()
                .port(port)
                .when()
                .request()
                .contentType(ContentType.JSON)
                .body(addOrderRequest)
                .post("/orders")
                .then()
                .statusCode(201);
    }
}
