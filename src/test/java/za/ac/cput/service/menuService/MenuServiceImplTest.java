package za.ac.cput.service.menuService;

import org.junit.jupiter.api.MethodOrderer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Menu;
import za.ac.cput.domain.Order;
import za.ac.cput.factory.MenuFactory;
import za.ac.cput.factory.OrderFactory;
import za.ac.cput.service.menuService.IMenuService;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MenuServiceImplTest {
    private final Order order = OrderFactory.build("18","StoneHurst","001","001","001");
    private final Menu menu= MenuFactory.build("219005303","Burger","Spur",order);

    @Autowired
    private IMenuService menuService;



    @Test
    @org.junit.jupiter.api.Order(1)
    void save(){
        Menu saved=this.menuService.save(this.menu);
        assertEquals(this.menu,saved);
        System.out.println(saved);
    }

    @Test
    @org.junit.jupiter.api.Order(2)
    void update(){

        Menu updated=this.menuService.save(this.menu);
        assertEquals(this.menu,updated);
        System.out.println(updated);
    }

    @Test
    @org.junit.jupiter.api.Order(3)
    void read(){
        Optional<Menu> read=this.menuService.read(this.menu.getMenuId());
        System.out.println(read);
        assertAll(
                ()->assertTrue(read.isPresent()),
                ()->assertEquals(this.menu,read.get())
        );
    }


    @Test
    @org.junit.jupiter.api.Order(4)
    void findAll(){
        List<Menu> menuList=this.menuService.findAll();
        System.out.println(menuList);
        assertEquals(1,menuList.size());
    }


    @Test
    @org.junit.jupiter.api.Order(5)
    void delete(){
        this.menuService.deleteById(this.menu.getMenuId());
        List<Menu>menuList=this.menuService.findAll();
        assertEquals(0,menuList.size());
    }
}
