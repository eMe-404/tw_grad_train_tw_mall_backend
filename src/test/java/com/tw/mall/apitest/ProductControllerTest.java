//package com.tw.mall.apitest;
//
//import com.github.springtestdbunit.DbUnitTestExecutionListener;
//import com.github.springtestdbunit.annotation.DatabaseSetup;
//import com.github.springtestdbunit.annotation.DatabaseTearDown;
//import com.github.springtestdbunit.annotation.DatabaseTearDowns;
//import com.github.springtestdbunit.annotation.ExpectedDatabase;
//import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
//import com.tw.mall.entity.Product;
//import io.restassured.http.ContentType;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.web.server.LocalServerPort;
//import org.springframework.test.annotation.DirtiesContext;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.TestExecutionListeners;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
//
//import static io.restassured.RestAssured.given;
//import static org.hamcrest.Matchers.contains;
//import static org.hamcrest.Matchers.notNullValue;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@RunWith(SpringRunner.class)
//@ContextConfiguration
//@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
//        DbUnitTestExecutionListener.class})
//@ActiveProfiles("test")
//public class ProductControllerTest {
//
//    @LocalServerPort
//    private int port;
//
//    @Before
//    @DatabaseSetup("classpath:/productSampleData.xml")
//    public void setUp() {
//    }
//
//    @Test
//    public void should_return_all_products_when_call_addAll() {
//        given()
//                .port(port)
//                .when()
//                .get("/products")
//                .then()
//                .body("id", contains(1, 2));
//    }
//
//    @Test
//    @DatabaseSetup("classpath:/productSampleData.xml")
//    @ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT, value = "classpath:/expectedSampleData.xml")
//    public void should_add_a_product() {
//        Product product = new Product();
//        product.setName("apple3");
//        product.setPrice(25);
//        product.setUnit("元");
//        given()
//                .port(port)
//                .when()
//                .request()
//                .contentType(ContentType.JSON)
//                .body(product)
//                .post("/products")
//                .then()
//                .statusCode(201)
//                .header("location", notNullValue());
//
//    }
//
//    @Test
//    @ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT, value = "classpath:/expectedSampleData.xml")
//    public void should_update_when_call_update() {
//        Product product = new Product();
//        product.setName("restTest");
//        product.setUnit("元");
//        product.setPrice(111);
//        given()
//                .port(port)
//                .when()
//                .request()
//                .contentType(ContentType.JSON)
//                .body(product)
//                .put("/products/1")
//                .then()
//                .statusCode(204);
//
//    }
//}