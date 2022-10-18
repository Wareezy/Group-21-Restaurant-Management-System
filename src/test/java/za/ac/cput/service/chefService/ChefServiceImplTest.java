package za.ac.cput.service.chefService;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Chef;
import za.ac.cput.domain.Name;
import za.ac.cput.domain.Waiter;
import za.ac.cput.domain.WaiterAddress;
import za.ac.cput.factory.ChefFactory;
import za.ac.cput.factory.NameFactory;
import za.ac.cput.factory.WaiterFactory;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ChefServiceImplTest {
    private final Name name = NameFactory.build("Warren","Reagan","Jaftha");
    private final Chef chef= ChefFactory.build("219005303","warrenjaftha16@gmail.com","0731791829",name);

    @Autowired
    private  IChefService chefService;


    @Order(1)
    @Test
    void save(){
        Chef saved=this.chefService.save(this.chef);
        assertEquals(this.chef,saved);
        System.out.println(saved);
    }
    @Order(2)
    @Test
    void update(){

        Chef updated=this.chefService.save(this.chef);
        assertEquals(this.chef,updated);
        System.out.println(updated);
    }

    @Order(3)
    @Test
    void read(){
        Optional<Chef> read=this.chefService.read(this.chef.getChefId());
        System.out.println(read);
        assertAll(
                ()->assertTrue(read.isPresent()),
                ()->assertEquals(this.chef,read.get())
        );
    }
    @Order(4)
    @Test
    void findByEmail(){
        Optional<Chef> chefsList=this.chefService.findByEmail(chef.getEmail());
        Name nameByEmail=chefsList.get().getName();
        System.out.println(chefsList);
        assertAll(
                ()->assertNotNull(nameByEmail),
                ()->assertEquals(name,nameByEmail)
        );
    }
    @Order(5)
    @Test
    void findAll(){
        List<Chef> chefList=this.chefService.findAll();
        System.out.println(chefList);
        assertEquals(1,chefList.size());
    }

    @Order(6)
    @Test
    void delete(){
        this.chefService.deleteById(this.chef.getChefId());
        List<Chef>chefList=this.chefService.findAll();
        assertEquals(0,chefList.size());
    }

}

