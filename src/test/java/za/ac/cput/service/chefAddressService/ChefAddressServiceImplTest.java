package za.ac.cput.service.chefAddressService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Address;
import za.ac.cput.domain.Chef;
import za.ac.cput.domain.ChefAddress;
import za.ac.cput.domain.WaiterAddress;
import za.ac.cput.factory.AddressFactory;
import za.ac.cput.factory.ChefAddressFactory;
import za.ac.cput.factory.WaiterAddressFactory;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ChefAddressServiceImplTest {
    private final Address address = AddressFactory.build("18","StoneHurst", "8","7th Ave","8324","Joburg");
    private final ChefAddress chefAddress= ChefAddressFactory.build("219005303",address);

    @Autowired
    private  IChefAddressService chefAddressService;


    @Order(1)
    @Test
    void save(){
        ChefAddress saved=this.chefAddressService.save(this.chefAddress);
        assertEquals(this.chefAddress,saved);
        System.out.println(saved);
    }

    @Order(2)
    @Test
    void update(){
        ChefAddress updated=this.chefAddressService.save(this.chefAddress);
        assertEquals(this.chefAddress,updated);
        System.out.println(updated);
    }


    @Order(3)
    @Test
    void read(){
        Optional<ChefAddress> read=this.chefAddressService.read(this.chefAddress.getChefId());
        System.out.println(read);
        assertAll(
                ()->assertTrue(read.isPresent()),
                ()->assertEquals(this.chefAddress,read.get())
        );
    }

    @Order(4)
    @Test
    void findAll(){
        List<ChefAddress> chefAddressList=this.chefAddressService.findAll();
        System.out.println(chefAddressList);
        assertEquals(1,chefAddressList.size());
    }

    @Order(5)
    @Test
    void delete(){
        this.chefAddressService.deleteById(this.chefAddress.getChefId());
        List<ChefAddress>chefAddressList=this.chefAddressService.findAll();
        assertEquals(0,chefAddressList.size());
    }
}

