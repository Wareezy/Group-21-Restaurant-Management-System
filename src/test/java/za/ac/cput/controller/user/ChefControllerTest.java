package za.ac.cput.controller.user;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import za.ac.cput.domain.Chef;
import za.ac.cput.domain.Name;
import za.ac.cput.factory.ChefFactory;
import za.ac.cput.factory.NameFactory;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ChefControllerTest {
    @LocalServerPort
    private int port;
    @Autowired
    private ChefController controller;
    @Autowired private TestRestTemplate restTemplate;

    private Chef chef;
    private Name name;
    private String baseUrl;

    @BeforeEach
    void setUp()
    {
        assertNotNull(controller);
        this.name= NameFactory.build("Daniel","","Jaftha");
        this.chef= ChefFactory.build("219005303","warrenjaftha16@gmail.com","0731791829",name);
        this.baseUrl="http://localhost:" +this.port + "/restaurant-management/chef/";
    }
    @Order(1)
    @Test
    void save()
    {
        String url=baseUrl +"save";
        System.out.println(url);
        ResponseEntity<Chef> response=this.restTemplate

                .postForEntity(url,this.chef,Chef.class);
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
        String url=baseUrl+"update" +this.chef.getChefId();
        System.out.println(url);
        this.restTemplate.delete(url);
    }
    @Order(3)
    @Test
    void read()
    {
        String url=baseUrl +"read/" + this.chef.getChefId();
        System.out.println(url);
        ResponseEntity<Chef> response=this.restTemplate

                .getForEntity(url,Chef.class);
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
        String url = baseUrl + "read-by-email/" + this.chef.getEmail();
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
        String url=baseUrl+"delete/" +this.chef.getChefId();
        System.out.println(url);
        this.restTemplate.delete(url);
    }
    @Order(6)
    @Test
    void findAll()
    {
        String url=baseUrl +"find-all";
        System.out.println(url);
        ResponseEntity<Chef[]> response =
                this.restTemplate

                        .getForEntity(url, Chef[].class);
        System.out.println(Arrays.asList(response.getBody()));
        assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertEquals(0, response.getBody().length)
        );
    }

}

