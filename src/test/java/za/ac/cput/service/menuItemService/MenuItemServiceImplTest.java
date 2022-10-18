package za.ac.cput.service.menuItemService;

import org.junit.jupiter.api.MethodOrderer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.*;
import za.ac.cput.factory.MenuItemFactory;
import za.ac.cput.factory.OrderFactory;
import za.ac.cput.service.menuItemService.IMenuItemService;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MenuItemServiceImplTest {
    private final Order order = OrderFactory.build("18","StoneHurst","001","001","001");
    private final MenuItem menuItem= MenuItemFactory.build("219005303",order);

    @Autowired
    private IMenuItemService menuItemService;




    @Test
    @org.junit.jupiter.api.Order(1)
    void save(){
        MenuItem saved=this.menuItemService.save(this.menuItem);
        assertEquals(this.menuItem,saved);
        System.out.println(saved);
    }

    @Test
    @org.junit.jupiter.api.Order(2)
    void update(){

        MenuItem updated=this.menuItemService.save(this.menuItem);
        assertEquals(this.menuItem,updated);
        System.out.println(updated);
    }

    @Test
    @org.junit.jupiter.api.Order(3)
    void read(){
        Optional<MenuItem> read=this.menuItemService.read(this.menuItem.getTitle());
        System.out.println(read);
        assertAll(
                ()->assertTrue(read.isPresent()),
                ()->assertEquals(this.menuItem,read.get())
        );
    }


    @Test
    @org.junit.jupiter.api.Order(4)
    void findAll(){
        List<MenuItem> menuItemList=this.menuItemService.findAll();
        System.out.println(menuItemList);
        assertEquals(1,menuItemList.size());
    }


    @Test
    @org.junit.jupiter.api.Order(5)
    void delete(){
        this.menuItemService.deleteById(this.menuItem.getTitle());
        List<MenuItem>menuItemList=this.menuItemService.findAll();
        assertEquals(0,menuItemList.size());
    }
}
