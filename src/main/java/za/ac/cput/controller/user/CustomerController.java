package za.ac.cput.controller.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import za.ac.cput.domain.Customer;
import za.ac.cput.domain.Name;
import za.ac.cput.factory.CustomerFactory;
import za.ac.cput.factory.NameFactory;
import za.ac.cput.service.customerService.ICustomerService;
import za.ac.cput.util.Helper;

import java.util.List;

@RestController
@RequestMapping("restaurant-management/customer/")
@Slf4j
public class CustomerController {

    private final ICustomerService customerService;

    @Autowired
    public CustomerController(ICustomerService customerService)
    {
        this.customerService=customerService;
    }

    @PostMapping("save")
    public ResponseEntity<Customer>save(@RequestBody Customer customer)
    {
        log.info("Save request:{}",customer);
        Name validatedName;
        Customer validatedCustomer;
        try{
            validatedName= NameFactory.build(customer.getName().getFirstName(),customer.getName().getMiddleName(),customer.getName().getLastName());
            validatedCustomer= CustomerFactory.build(customer.getCustomerId(),customer.getEmail(),customer.getCellPhoneNumber(),validatedName );
        }catch(IllegalArgumentException e)
        {
            log.info("Save request error:{}",e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        Customer save=customerService.save(validatedCustomer);
        return ResponseEntity.ok(save);
    }
    @PutMapping("update")
    public Customer update(@RequestBody Customer customer){return customerService.save(customer);}

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id)
    {
        log.info("Delete request{}",id);
        this.customerService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("read/{id}")
    public ResponseEntity<Customer> read(@PathVariable String id) {
        log.info("Read request: {}", id);
        Customer customer = this.customerService.read(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return ResponseEntity.ok(customer);
    }
    @GetMapping("find-all")
    public ResponseEntity<List<Customer>> findAll()
    {
        List<Customer>customerList=this.customerService.findAll();
        return ResponseEntity.ok(customerList);
    }
    @GetMapping("read-by-email/{email}")
    public ResponseEntity<Name> findByEmail(@PathVariable String email)
    {
        log.info("Read name by email request: {}",email);
        try{
            Helper.checkEmail(email);
        }catch(IllegalArgumentException e)
        {
            log.info("Find name by email request error: {}",e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        Customer customer=this.customerService.findByEmail(email)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
        return ResponseEntity.ok(customer.getName());
    }
}

