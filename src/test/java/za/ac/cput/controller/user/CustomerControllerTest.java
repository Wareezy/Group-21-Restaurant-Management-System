package za.ac.cput.controller.user;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import za.ac.cput.domain.Customer;
import za.ac.cput.factory.CustomerFactory;
import za.ac.cput.factory.NameFactory;

import java.util.Arrays;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CustomerControllerTest {
    @LocalServerPort
    private int port;
    @Autowired private CustomerController controller;
    @Autowired private TestRestTemplate restTemplate;

    private Customer customer;
    private Name name;
    private String baseUrl;

    @BeforeEach
    void setUp(){
        assertNotNull(controller);
        this.name= NameFactory.build("Warren","","Jaftha");
        this.customer= CustomerFactory.build("219005303","warrenjaftha16@gmail.com","0731791829",name);
        this.baseUrl="http://localhost:" + this.port + "/restaurant-management/customer/";

    }

    @Order(1)
    @Test
    void save()
    {
        String url=baseUrl +"save";
        System.out.println(url);
        ResponseEntity<Customer> response=this.restTemplate

                .postForEntity(url,this.customer,Customer.class);
        System.out.println(response);
        assertAll(
                ()->assertEquals(HttpStatus.OK,response.getStatusCode()),
                ()->assertNotNull(response.getBody())
        );
    }
    @Order(2)
    @Test
    void update()
    {
        String url=baseUrl+"update" +this.customer.getCustomerId();
        System.out.println(url);
        this.restTemplate.delete(url);
    }
    @Order(3)
    @Test
    void read()
    {
        String url=baseUrl +"read/" + this.customer.getCustomerId();
        System.out.println(url);
        ResponseEntity<Customer> response=this.restTemplate

                .getForEntity(url,Customer.class);
        System.out.println(response);
        assertAll(
                ()->assertEquals(HttpStatus.OK,response.getStatusCode()),
                ()->assertNotNull(response.getBody())

        );
    }
    @Order(4)
    @Test
    void findByEmail()
    {
        String url = baseUrl + "read-by-email/" + this.customer.getEmail();
        System.out.println(url);
        ResponseEntity<Name> response =
                this.restTemplate

                        .getForEntity(url, Name.class);
        System.out.println(response.getBody());
        assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertNotNull(response.getBody())
        );
    }
    @Order(5)
    @Test
    void delete()
    {
        String url=baseUrl+"delete/" +this.customer.getCustomerId();
        System.out.println(url);
        this.restTemplate.delete(url);
    }
    @Order(6)
    @Test
    void findAll()
    {
        String url = baseUrl + "find-all";
        System.out.println(url);
        ResponseEntity<Customer[]> response =
                this.restTemplate

                        .getForEntity(url, Customer[].class);
        System.out.println(Arrays.asList(response.getBody()));
        assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertEquals(0, response.getBody().length)
        );
    }


}