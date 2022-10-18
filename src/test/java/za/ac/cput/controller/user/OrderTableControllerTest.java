package za.ac.cput.controller.user;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import za.ac.cput.controller.user.OrderTableController;
import za.ac.cput.domain.Order;
import za.ac.cput.domain.OrderTable;
import za.ac.cput.factory.OrderFactory;
import za.ac.cput.factory.OrderTableFactory;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OrderTableControllerTest {
    @LocalServerPort
    private int port;
    @Autowired
    private OrderTableController controller;
    @Autowired private TestRestTemplate restTemplate;

    private OrderTable orderTable;
    private Order order;
    private String baseUrl;

    @BeforeEach
    void setUp()
    {
        assertNotNull(controller);
        this.order= OrderFactory.build("18","StoneHurst","001","001","001");
        this.orderTable= OrderTableFactory.build("219005303","15",order);
        this.baseUrl="http://localhost:" +this.port + "/restaurant-management/order-table/";
    }

    @Test
    @org.junit.jupiter.api.Order(1)
    void save()
    {
        String url=baseUrl +"save";
        System.out.println(url);
        ResponseEntity<OrderTable> response=this.restTemplate

                .postForEntity(url,this.orderTable,OrderTable.class);
        System.out.println(response);
        assertAll(
                ()->assertEquals(HttpStatus.OK,response.getStatusCode()),
                ()->assertNotNull(response.getBody())
        );
    }

    @org.junit.jupiter.api.Order(2)
    @Test
    void update()
    {
        String url=baseUrl+"update" +this.orderTable.getOrderTableId();
        System.out.println(url);
        this.restTemplate.delete(url);
    }

    @Test
    @org.junit.jupiter.api.Order(3)
    void read()
    {
        String url=baseUrl +"read/" + this.orderTable.getOrderTableId();
        System.out.println(url);
        ResponseEntity<OrderTable> response=this.restTemplate

                .getForEntity(url,OrderTable.class);
        System.out.println(response);
        assertAll(
                ()->assertEquals(HttpStatus.OK,response.getStatusCode()),
                ()->assertNotNull(response.getBody())
        );
    }

    @Test
    @org.junit.jupiter.api.Order(4)
    void delete()
    {
        String url=baseUrl+"delete/" +this.orderTable.getOrderTableId();
        System.out.println(url);
        this.restTemplate.delete(url);
    }

    @Test
    @org.junit.jupiter.api.Order(5)
    void findAll()
    {
        String url=baseUrl +"find-all";
        System.out.println(url);
        ResponseEntity<OrderTable[]> response =
                this.restTemplate

                        .getForEntity(url, OrderTable[].class);
        System.out.println(Arrays.asList(response.getBody()));
        assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertEquals(0, response.getBody().length)
        );
    }

}
