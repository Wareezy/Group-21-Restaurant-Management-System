package za.ac.cput.controller.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import za.ac.cput.domain.Order;
import za.ac.cput.domain.OrderTable;
import za.ac.cput.factory.OrderFactory;
import za.ac.cput.factory.OrderTableFactory;
import za.ac.cput.service.orderTableService.IOrderTableService;

import java.util.List;

@RestController
@RequestMapping("restaurant-management/order-table/")
@Slf4j
public class OrderTableController {
    private final IOrderTableService orderTableService;
    @Autowired
    public OrderTableController(IOrderTableService orderTableService)
    {
        this.orderTableService=orderTableService;
    }

    @PostMapping("save")
    public ResponseEntity<OrderTable> save(@RequestBody OrderTable orderTable)
    {
        log.info("Save request: {}",orderTable);
        Order validatedOrder;
        OrderTable validatedOrderTable;
        try{
            validatedOrder= OrderFactory.build(orderTable.getOrder().getOrderId(),orderTable.getOrder().getOrderDetails(),orderTable.getOrder().getCustomerId(),orderTable.getOrder().getWaiterId(),orderTable.getOrder().getChefId());

            validatedOrderTable= OrderTableFactory.build(orderTable.getOrderTableId(),orderTable.getNumberOfGuest(),validatedOrder);
        }catch(IllegalArgumentException e)
        {
            log.info("Save request error: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        OrderTable save=orderTableService.save(validatedOrderTable);
        return ResponseEntity.ok(save);
    }

    @PutMapping("update")
    public OrderTable update(@RequestBody OrderTable orderTable){return orderTableService.save(orderTable);}


    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id)
    {
        log.info("Delete request{}",id);
        this.orderTableService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("read/{id}")
    public ResponseEntity<OrderTable>read(@PathVariable String id)
    {
        log.info("Read request: {}", id);
        OrderTable orderTable= this.orderTableService.read(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
        return ResponseEntity.ok(orderTable);
    }

    @GetMapping("find-all")
    public ResponseEntity<List<OrderTable>> findAll()
    {
        List<OrderTable> orderTableList=this.orderTableService.findAll();
        return ResponseEntity.ok(orderTableList);
    }
}

