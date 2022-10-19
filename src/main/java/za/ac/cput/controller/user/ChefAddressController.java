package za.ac.cput.controller.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import za.ac.cput.domain.Address;
import za.ac.cput.domain.ChefAddress;
import za.ac.cput.factory.AddressFactory;
import za.ac.cput.factory.ChefAddressFactory;
import za.ac.cput.service.chefAddressService.IChefAddressService;
import lombok.extern.slf4j.Slf4j;
import java.util.List;

@RestController
@RequestMapping("restaurant-management/chef-address/")
@Slf4j
public class ChefAddressController {
    private final IChefAddressService chefAddressService;
    @Autowired
    public ChefAddressController(IChefAddressService chefAddressService)
    {
        this.chefAddressService=chefAddressService;
    }

    @PostMapping("save")
    public ResponseEntity<ChefAddress> save(@RequestBody ChefAddress chefAddress)
    {
        log.info("Save request: {}",chefAddress);
        Address validatedAddress;
        ChefAddress validatedChefAddress;
        try{
            validatedAddress= AddressFactory.build(chefAddress.getAddress().getUnitNumber(),chefAddress.getAddress().getComplexName(),chefAddress.getAddress().getStreetNumber(),chefAddress.getAddress().getStreetName(),chefAddress.getAddress().getPostalCode(),chefAddress.getAddress().getCity());

            validatedChefAddress= ChefAddressFactory.build(chefAddress.getChefId(),validatedAddress);
        }catch(IllegalArgumentException e)
        {
            log.info("Save request error: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        ChefAddress save=chefAddressService.save(validatedChefAddress);
        return ResponseEntity.ok(save);
    }

    @PutMapping("update")
    public ChefAddress update(@RequestBody ChefAddress chefAddress){return chefAddressService.save(chefAddress);}


    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id)
    {
        log.info("Delete request{}",id);
        this.chefAddressService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("read/{id}")
    public ResponseEntity<ChefAddress>read(@PathVariable String id)
    {
        log.info("Read request: {}", id);
        ChefAddress chefAddress= this.chefAddressService.read(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
        return ResponseEntity.ok(chefAddress);
    }

    @GetMapping("find-all")
    public ResponseEntity<List<ChefAddress>> findAll()
    {
        List<ChefAddress> chefList=this.chefAddressService.findAll();
        return ResponseEntity.ok(chefList);
    }
}
