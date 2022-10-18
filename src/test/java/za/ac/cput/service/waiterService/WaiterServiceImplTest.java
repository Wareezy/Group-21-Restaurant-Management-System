package za.ac.cput.service.waiterService;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Waiter;
import za.ac.cput.factory.NameFactory;
import za.ac.cput.factory.WaiterFactory;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class WaiterServiceImplTest {
    private final Name name = NameFactory.build("Warren","Reagan","Jaftha");
    private final Waiter waiter= WaiterFactory.build("219005303","warrenjaftha16@gmail.com","0731791829",name);


    @Autowired
    private IWaiterService waiterService;


    @Order(1)
    @Test
    void save(){
        Waiter saved=this.waiterService.save(this.waiter);
        assertEquals(this.waiter,saved);
        System.out.println(saved);
    }
    @Order(2)
    @Test
    void update(){

        Waiter updated=this.waiterService.save(this.waiter);
        assertEquals(this.waiter,updated);
        System.out.println(updated);
    }

    @Order(3)
    @Test
    void read(){
        Optional<Waiter> read=this.waiterService.read(this.waiter.getWaiterId());
        System.out.println(read);
        assertAll(
                ()->assertTrue(read.isPresent()),
                ()->assertEquals(this.waiter,read.get())
        );
    }

    @Order(5)
    @Test
    void findAll(){
        List<Waiter>waiterList=this.waiterService.findAll();
        System.out.println(waiterList);
        assertEquals(1,waiterList.size());
    }

    @Order(6)
    @Test
    void delete(){
        this.waiterService.deleteById(this.waiter.getWaiterId());
        List<Waiter>waiterList=this.waiterService.findAll();
        assertEquals(0,waiterList.size());
    }
    @Order(4)
    @Test
    void findByEmail(){
        Optional<Waiter> waitersList=this.waiterService.findByEmail(waiter.getEmail());
        Name nameByEmail=waitersList.get().getName();
        System.out.println(waitersList);
        assertAll(
                ()->assertNotNull(nameByEmail),
                ()->assertEquals(name,nameByEmail)
        );
    }

}