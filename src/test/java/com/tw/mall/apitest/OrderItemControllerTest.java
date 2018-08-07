package com.tw.mall.apitest;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
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

import static io.restassured.RestAssured.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@ContextConfiguration
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@ActiveProfiles("test")
public class OrderItemControllerTest {
    @LocalServerPort
    private int port;

    @Test
    @DatabaseSetup("classpath:/order_item_data/orderItemInitData.xml")
    @ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT, value = "classpath:/order_item_data/orderItemExpectedAdd.xml")
    public void should_add_orderItem_when_call_add() {
        OrderItem orderItem = new OrderItem();
        orderItem.setCount(66);
        orderItem.setOrderId(1);
        orderItem.setProductId(2);
        given()
                .port(port)
                .when()
                .request()
                .contentType(ContentType.JSON)
                .body(orderItem)
                .post("/orderItems")
                .then()
                .statusCode(201);
    }

    @Test
    @DatabaseSetup("classpath:/order_item_data/orderItemInitData.xml")
    @ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT, value = "classpath:/order_item_data/orderItemExpectedUpdate.xml")
    public void should_update_order_item_when_call_update() {
        OrderItem orderItem = new OrderItem();
        orderItem.setCount(888);
        orderItem.setOrderId(1);
        orderItem.setProductId(3);
        given()
                .port(port)
                .when()
                .request()
                .contentType(ContentType.JSON)
                .body(orderItem)
                .put("/orderItems/2")
                .then()
                .statusCode(204);

    }

    @Test
    @DatabaseSetup("classpath:/order_item_data/orderItemInitData.xml")
    @ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT, value = "classpath:/order_item_data/orderItemExpectedDelete.xml")
    public void should_delete_order_item_when_call_delete() {
        given()
                .port(port)
                .when()
                .delete("/orderItems/2")
                .then()
                .statusCode(200);
    }

}
