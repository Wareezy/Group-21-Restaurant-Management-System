package za.ac.cput.service.customerService;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Customer;
import za.ac.cput.factory.CustomerFactory;
import za.ac.cput.factory.NameFactory;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CustomerServiceImplTest {
    private final Name name = NameFactory.build("Warren", "Middle","Jaftha");
    private final Customer customer = CustomerFactory.build("209023651","209023651@mycput.ac.za","0731791829", name);

    @Autowired
    private ICustomerService customerService;

    @Order(1)
    @Test
    void save(){
        Customer saved = this.customerService.save(this.customer);
        assertEquals(this.customer, saved);
        System.out.println(saved);
    }
    @Order(2)
    @Test
    void update(){

        Customer updated=this.customerService.save(this.customer);
        assertEquals(this.customer,updated);
        System.out.println(updated);
    }
    @Order(3)
    @Test
    void read(){
        Optional<Customer> read = this.customerService.read(this.customer.getCustomerId());
        System.out.println(read);
        assertAll(
                ()-> assertTrue(read.isPresent()),
                ()-> assertEquals(this.customer,read.get())
        );
    }

    @Order(4)
    @Test
    void findAll(){
        List<Customer> customerList = this.customerService.findAll();
        System.out.println(customerList);
        assertEquals(1,customerList.size());
    }
    @Order(5)
    @Test
    void findByEmail(){
        Optional<Customer> customersList = this.customerService.findByEmail(customer.getEmail());
        Name nameByEmail = customersList.get().getName();
        System.out.println(customersList);
        assertAll(
                () -> assertNotNull(nameByEmail),
                () -> assertEquals(name, nameByEmail)
        );

    }
    @Order(6)
    @Test
    void delete(){
        this.customerService.deleteById(this.customer.getCustomerId());
        List<Customer> customerList = this.customerService.findAll();
        assertEquals(0,customerList.size());
    }


}