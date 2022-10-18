package za.ac.cput.service.orderTableService;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Order;
import za.ac.cput.domain.OrderTable;
import za.ac.cput.factory.OrderFactory;
import za.ac.cput.factory.OrderTableFactory;
import za.ac.cput.service.orderTableService.IOrderTableService;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OrderTableServiceImplTest {
    private final Order order = OrderFactory.build("18", "StoneHurst","001","001","001");
    private final OrderTable orderTable= OrderTableFactory.build("219005303","15", order);

    @Autowired
    private IOrderTableService orderTableService;


    @Test
    @org.junit.jupiter.api.Order(1)
    void save(){
        OrderTable saved = this.orderTableService.save(this.orderTable);
        assertEquals(this.orderTable, saved);
        System.out.println(saved);
    }

    @Test
    @org.junit.jupiter.api.Order(2)
    void update(){

        OrderTable updated=this.orderTableService.save(this.orderTable);
        assertEquals(this.orderTable,updated);
        System.out.println(updated);
    }
    @Test
    @org.junit.jupiter.api.Order(3)
    void read(){
        Optional<OrderTable> read = this.orderTableService.read(this.orderTable.getOrderTableId());
        System.out.println(read);
        assertAll(
                ()-> assertTrue(read.isPresent()),
                ()-> assertEquals(this.orderTable,read.get())
        );
    }


    @Test
    @org.junit.jupiter.api.Order(4)
    void findAll(){
        List<OrderTable> orderTableList = this.orderTableService.findAll();
        System.out.println(orderTableList);
        assertEquals(1,orderTableList.size());
    }


    @Test
    @org.junit.jupiter.api.Order(5)
    void delete(){
        this.orderTableService.deleteById(this.orderTable.getOrderTableId());
        List<OrderTable>orderTableList = this.orderTableService.findAll();
        assertEquals(0,orderTableList.size());
    }

}
