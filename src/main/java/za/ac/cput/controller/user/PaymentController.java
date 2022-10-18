package za.ac.cput.controller.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import za.ac.cput.domain.*;
import za.ac.cput.factory.OrderFactory;
import za.ac.cput.factory.PaymentFactory;
import za.ac.cput.service.paymentService.IPaymentService;

import java.util.List;

@RestController
@RequestMapping("restaurant-management/payment/")
@Slf4j
public class PaymentController {
    private final IPaymentService paymentService;
    @Autowired
    public PaymentController(IPaymentService paymentService)
    {
        this.paymentService=paymentService;
    }

    @PostMapping("save")
    public ResponseEntity<Payment> save(@RequestBody Payment payment)
    {
        log.info("Save request: {}",payment);
        Order validatedOrder;
        Payment validatedPayment;
        try{
            validatedOrder= OrderFactory.build(payment.getOrder().getOrderId(),payment.getOrder().getOrderDetails(),payment.getOrder().getCustomerId(),payment.getOrder().getWaiterId(),payment.getOrder().getChefId());

            validatedPayment= PaymentFactory.build(payment.getCardNumber(),payment.getPaymentAmount(),validatedOrder);
        }catch(IllegalArgumentException e)
        {
            log.info("Save request error: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        Payment save=paymentService.save(validatedPayment);
        return ResponseEntity.ok(save);
    }

    @PutMapping("update")
    public Payment update(@RequestBody Payment payment){return paymentService.save(payment);}

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id)
    {
        log.info("Delete request{}",id);
        this.paymentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("read/{id}")
    public ResponseEntity<Payment>read(@PathVariable String id)
    {
        log.info("Read request: {}", id);
        Payment payment= this.paymentService.read(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
        return ResponseEntity.ok(payment);
    }

    @GetMapping("find-all")
    public ResponseEntity<List<Payment>> findAll()
    {
        List<Payment> paymentList=this.paymentService.findAll();
        return ResponseEntity.ok(paymentList);
    }
}

