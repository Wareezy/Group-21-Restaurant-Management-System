package za.ac.cput.controller.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import za.ac.cput.domain.*;
import za.ac.cput.factory.ChefFactory;
import za.ac.cput.factory.NameFactory;
import za.ac.cput.service.chefService.IChefService;
import za.ac.cput.util.Helper;

import java.util.List;

@RestController
@RequestMapping("restaurant-management/chef/")
@Slf4j
public class ChefController {
    private final IChefService chefService;
    @Autowired
    public ChefController(IChefService chefService)
    {
        this.chefService=chefService;
    }

    @PostMapping("save")
    public ResponseEntity<Chef> save(@RequestBody Chef chef)
    {
        log.info("Save request: {}",chef);
        Name validatedName;
        Chef validatedChef;
        try{
            validatedName= NameFactory.build(chef.getName().getFirstName(),chef.getName().getMiddleName(),chef.getName().getLastName());
            validatedChef= ChefFactory.build(chef.getChefId(),chef.getEmail(),chef.getCellPhoneNumber(),validatedName);
        }catch(IllegalArgumentException e)
        {
            log.info("Save request error: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        Chef save=chefService.save(validatedChef);
        return ResponseEntity.ok(save);
    }
    @PutMapping("update")
    public Chef update(@RequestBody Chef chef){return chefService.save(chef);}


    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id)
    {
        log.info("Delete request{}",id);
        this.chefService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("read/{id}")
    public ResponseEntity<Chef>read(@PathVariable String id)
    {
        log.info("Read request: {}", id);
        Chef chef= this.chefService.read(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
        return ResponseEntity.ok(chef);
    }

    @GetMapping("find-all")
    public ResponseEntity<List<Chef>> findAll()
    {
        List<Chef> chefList=this.chefService.findAll();
        return ResponseEntity.ok(chefList);
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
        Chef chef=this.chefService.findByEmail(email)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
        return ResponseEntity.ok(chef.getName());
    }
}
