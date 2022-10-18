package za.ac.cput.service.waiterAddressService;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.*;
import za.ac.cput.factory.AddressFactory;
import za.ac.cput.factory.WaiterAddressFactory;
import za.ac.cput.service.waiterAddressService.IWaiterAddressService;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class WaiterAddressServiceImplTest {
    private final Address address = AddressFactory.build("18","StoneHurst", "8","7th Ave","8324","Joburg");
    private final WaiterAddress waiterAddress= WaiterAddressFactory.build("219005303",address);

    @Autowired
    private IWaiterAddressService waiterAddressService;


    @Order(1)
    @Test
    void save(){
        WaiterAddress saved=this.waiterAddressService.save(this.waiterAddress);
        assertEquals(this.waiterAddress,saved);
        System.out.println(saved);
    }

    @Test
    @org.junit.jupiter.api.Order(2)
    void update(){

        WaiterAddress updated=this.waiterAddressService.save(this.waiterAddress);
        assertEquals(this.waiterAddress,updated);
        System.out.println(updated);
    }

    @Order(3)
    @Test
    void read(){
        Optional<WaiterAddress> read=this.waiterAddressService.read(this.waiterAddress.getWaiterId());
        System.out.println(read);
        assertAll(
                ()->assertTrue(read.isPresent()),
                ()->assertEquals(this.waiterAddress,read.get())
        );
    }

    @Order(4)
    @Test
    void findAll(){
        List<WaiterAddress> waiterAddressList=this.waiterAddressService.findAll();
        System.out.println(waiterAddressList);
        assertEquals(1,waiterAddressList.size());
    }

    @Order(5)
    @Test
    void delete(){
        this.waiterAddressService.deleteById(this.waiterAddress.getWaiterId());
        List<WaiterAddress>waiterAddressList=this.waiterAddressService.findAll();
        assertEquals(0,waiterAddressList.size());
    }
}

