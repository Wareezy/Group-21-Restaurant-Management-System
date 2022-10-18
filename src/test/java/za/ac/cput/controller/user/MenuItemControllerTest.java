package za.ac.cput.controller.user;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import za.ac.cput.controller.user.MenuItemController;
import za.ac.cput.domain.MenuItem;
import za.ac.cput.domain.Order;
import za.ac.cput.factory.MenuItemFactory;
import za.ac.cput.factory.OrderFactory;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MenuItemControllerTest{
    @LocalServerPort
    private int port;
    @Autowired
    private MenuItemController controller;
    @Autowired private TestRestTemplate restTemplate;

    private MenuItem menuItem;
    private Order order;
    private String baseUrl;


    @BeforeEach
    void setUp()
    {
        assertNotNull(controller);
        this.order= OrderFactory.build("18","StoneHurst","001","001","001");
        this.menuItem= MenuItemFactory.build("001",order);
        this.baseUrl="http://localhost:" +this.port + "/restaurant-management/menu-item/";

    }

    @Test
    @org.junit.jupiter.api.Order(1)
    void save()
    {
        String url=baseUrl +"save";
        System.out.println(url);
        ResponseEntity<MenuItem> response=this.restTemplate

                .postForEntity(url,this.menuItem,MenuItem.class);
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
        String url=baseUrl+"update" +this.menuItem.getTitle();
        System.out.println(url);
        this.restTemplate.delete(url);
    }

    @Test
    @org.junit.jupiter.api.Order(3)
    void read()
    {
        String url=baseUrl +"read/" + this.menuItem.getTitle();
        System.out.println(url);
        ResponseEntity<MenuItem> response=this.restTemplate

                .getForEntity(url,MenuItem.class);
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
        String url=baseUrl+"delete/" +this.menuItem.getTitle();
        System.out.println(url);
        this.restTemplate.delete(url);
    }

    @Test
    @org.junit.jupiter.api.Order(5)
    void findAll()
    {
        String url=baseUrl +"find-all";
        System.out.println(url);
        ResponseEntity<MenuItem[]> response =this.restTemplate

                .getForEntity(url, MenuItem[].class);
        System.out.println(Arrays.asList(response.getBody()));
        assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertEquals(0, response.getBody().length)
        );
    }
}
