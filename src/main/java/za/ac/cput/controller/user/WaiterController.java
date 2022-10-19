package za.ac.cput.controller.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import za.ac.cput.domain.Name;
import za.ac.cput.domain.Waiter;

import za.ac.cput.factory.NameFactory;
import za.ac.cput.factory.WaiterFactory;
import za.ac.cput.service.waiterService.IWaiterService;
import za.ac.cput.util.Helper;

import java.util.List;

@RestController
@RequestMapping("restaurant-management/waiter/")
@Slf4j
public class WaiterController {
    private final IWaiterService waiterService;

    @Autowired
    public WaiterController(IWaiterService waiterService)
    {
        this.waiterService=waiterService;
    }

    @PostMapping("save")
    public ResponseEntity<Waiter> save(@RequestBody Waiter waiter)
    {
        log.info("Save request: {}",waiter);
        Name validatedName;
        Waiter validatedWaiter;
        try{
            validatedName= NameFactory.build(waiter.getName().getFirstName(),waiter.getName().getMiddleName(),waiter.getName().getLastName());
            validatedWaiter= WaiterFactory.build(waiter.getWaiterId(),waiter.getEmail(),waiter.getCellPhoneNumber(),validatedName);
        }catch(IllegalArgumentException e)
        {
            log.info("Save request error: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        Waiter save=waiterService.save(validatedWaiter);
        return ResponseEntity.ok(save);
    }
    @PutMapping("update")
    public Waiter update(@RequestBody Waiter waiter){return waiterService.save(waiter);}


    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id)
    {
        log.info("Delete request{}",id);
        this.waiterService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("read/{id}")
    public ResponseEntity<Waiter>read(@PathVariable String id)
    {
        log.info("Read request: {}", id);
        Waiter waiter= this.waiterService.read(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
        return ResponseEntity.ok(waiter);
    }

    @GetMapping("find-all")
    public ResponseEntity<List<Waiter>> findAll()
    {
        List<Waiter> waiterList=this.waiterService.findAll();
        return ResponseEntity.ok(waiterList);
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
        Waiter waiter=this.waiterService.findByEmail(email)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
        return ResponseEntity.ok(waiter.getName());
    }
}
