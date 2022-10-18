package za.ac.cput.service.paymentService;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.MethodOrderer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.*;
import za.ac.cput.factory.OrderFactory;
import za.ac.cput.factory.PaymentFactory;
import za.ac.cput.service.paymentService.IPaymentService;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PaymentServiceImplTest {
    private final Order order = OrderFactory.build("18", "StoneHurst","001","001","001");
    private final Payment payment= PaymentFactory.build("219005303","15", order);

    @Autowired
    private IPaymentService paymentService;


    @Test
    @org.junit.jupiter.api.Order(1)
    void save(){
        Payment saved = this.paymentService.save(this.payment);
        assertEquals(this.payment, saved);
        System.out.println(saved);
    }

    @Test
    @org.junit.jupiter.api.Order(2)
    void update(){

        Payment updated=this.paymentService.save(this.payment);
        assertEquals(this.payment,updated);
        System.out.println(updated);
    }

    @Test
    @org.junit.jupiter.api.Order(3)
    void read(){
        Optional<Payment> read = this.paymentService.read(this.payment.getCardNumber());
        System.out.println(read);
        assertAll(
                ()-> assertTrue(read.isPresent()),
                ()-> assertEquals(this.payment,read.get())
        );
    }


    @Test
    @org.junit.jupiter.api.Order(4)
    void findAll(){
        List<Payment> paymentList = this.paymentService.findAll();
        System.out.println(paymentList);
        assertEquals(1,paymentList.size());
    }


    @Test
    @org.junit.jupiter.api.Order(5)
    void delete(){
        this.paymentService.deleteById(this.payment.getCardNumber());
        List<Payment>paymentList = this.paymentService.findAll();
        assertEquals(0,paymentList.size());
    }

}
