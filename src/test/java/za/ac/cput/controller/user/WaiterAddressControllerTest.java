package za.ac.cput.controller.user;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import za.ac.cput.controller.user.WaiterAddressController;
import za.ac.cput.domain.Address;
import za.ac.cput.domain.WaiterAddress;
import za.ac.cput.factory.AddressFactory;
import za.ac.cput.factory.WaiterAddressFactory;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class WaiterAddressControllerTest {
    @LocalServerPort
    private int port;
    @Autowired
    private WaiterAddressController controller;
    @Autowired private TestRestTemplate restTemplate;

    private WaiterAddress waiterAddress;
    private Address address;
    private String baseUrl;

    @BeforeEach
    void setUp()
    {
        assertNotNull(controller);
        this.address= AddressFactory.build("18","StoneHurst", "8","7th Ave","8324","Joburg");
        this.waiterAddress= WaiterAddressFactory.build("219005303",address);
        this.baseUrl="http://localhost:" +this.port + "/restaurant-management/waiter-address/";
    }
    @Order(1)
    @Test
    void save()
    {
        String url=baseUrl +"save";
        System.out.println(url);
        ResponseEntity<WaiterAddress> response=this.restTemplate

                .postForEntity(url,this.waiterAddress,WaiterAddress.class);
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
        String url=baseUrl+"update" +this.waiterAddress.getWaiterId();
        System.out.println(url);
        this.restTemplate.delete(url);
    }

    @Order(3)
    @Test
    void read()
    {
        String url=baseUrl +"read/" + this.waiterAddress.getWaiterId();
        System.out.println(url);
        ResponseEntity<WaiterAddress> response=this.restTemplate

                .getForEntity(url,WaiterAddress.class);
        System.out.println(response);
        assertAll(
                ()->assertEquals(HttpStatus.OK,response.getStatusCode()),
                ()->assertNotNull(response.getBody())
        );
    }
    @Order(4)
    @Test
    void delete()
    {
        String url=baseUrl+"delete/" +this.waiterAddress.getWaiterId();
        System.out.println(url);
        this.restTemplate.delete(url);
    }
    @Order(5)
    @Test
    void findAll()
    {
        String url=baseUrl +"find-all";
        System.out.println(url);
        ResponseEntity<WaiterAddress[]> response =
                this.restTemplate

                        .getForEntity(url, WaiterAddress[].class);
        System.out.println(Arrays.asList(response.getBody()));
        assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertEquals(0, response.getBody().length)
        );
    }
}

