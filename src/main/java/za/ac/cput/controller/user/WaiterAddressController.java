package za.ac.cput.controller.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import za.ac.cput.domain.Address;
import za.ac.cput.domain.WaiterAddress;
import za.ac.cput.factory.AddressFactory;
import za.ac.cput.factory.WaiterAddressFactory;
import za.ac.cput.service.waiterAddressService.IWaiterAddressService;

import java.util.List;
@RestController
@RequestMapping("restaurant-management/waiter-address/")
@Slf4j
public class WaiterAddressController {
    private final IWaiterAddressService waiterAddressService;
    @Autowired
    public WaiterAddressController(IWaiterAddressService waiterAddressService)
    {
        this.waiterAddressService=waiterAddressService;
    }

    @PostMapping("save")
    public ResponseEntity<WaiterAddress> save(@RequestBody WaiterAddress waiterAddress)
    {
        log.info("Save request: {}",waiterAddress);
        Address validatedAddress;
        WaiterAddress validatedWaiterAddress;
        try{
            validatedAddress= AddressFactory.build(waiterAddress.getAddress().getUnitNumber(),waiterAddress.getAddress().getComplexName(),waiterAddress.getAddress().getStreetNumber(),waiterAddress.getAddress().getStreetName(),waiterAddress.getAddress().getPostalCode(),waiterAddress.getAddress().getCity());

            validatedWaiterAddress= WaiterAddressFactory.build(waiterAddress.getWaiterId(),validatedAddress);
        }catch(IllegalArgumentException e)
        {
            log.info("Save request error: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        WaiterAddress save=waiterAddressService.save(validatedWaiterAddress);
        return ResponseEntity.ok(save);
    }

    @PutMapping("update")
    public WaiterAddress update(@RequestBody WaiterAddress waiterAddress){return waiterAddressService.save(waiterAddress);}

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id)
    {
        log.info("Delete request{}",id);
        this.waiterAddressService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("read/{id}")
    public ResponseEntity<WaiterAddress>read(@PathVariable String id)
    {
        log.info("Read request: {}", id);
        WaiterAddress waiterAddress= this.waiterAddressService.read(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
        return ResponseEntity.ok(waiterAddress);
    }

    @GetMapping("find-all")
    public ResponseEntity<List<WaiterAddress>> findAll()
    {
        List<WaiterAddress> waiterList=this.waiterAddressService.findAll();
        return ResponseEntity.ok(waiterList);
    }
}
