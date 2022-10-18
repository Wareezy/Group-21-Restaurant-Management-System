package za.ac.cput.controller.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import za.ac.cput.domain.*;
import za.ac.cput.factory.MenuItemFactory;
import za.ac.cput.factory.OrderFactory;
import za.ac.cput.service.menuItemService.IMenuItemService;

import java.util.List;

@RestController
@RequestMapping("restaurant-management/menu-item/")
@Slf4j
public class MenuItemController {
    private final IMenuItemService menuItemService;
    @Autowired
    public MenuItemController(IMenuItemService menuItemService)
    {
        this.menuItemService=menuItemService;
    }

    @PostMapping("save")
    public ResponseEntity<MenuItem> save(@RequestBody MenuItem menuItem  )
    {
        log.info("Save request: {}",menuItem);
        Order validatedOrder;
        MenuItem validatedMenuItem;
        try{
            validatedOrder= OrderFactory.build(menuItem.getOrder().getOrderId(),menuItem.getOrder().getOrderDetails(),menuItem.getOrder().getCustomerId(),menuItem.getOrder().getWaiterId(),menuItem.getOrder().getChefId());

            validatedMenuItem= MenuItemFactory.build(menuItem.getTitle(),validatedOrder);
        }catch(IllegalArgumentException e)
        {
            log.info("Save request error: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        MenuItem save=menuItemService.save(validatedMenuItem);
        return ResponseEntity.ok(save);
    }
    @PutMapping("update")
    public MenuItem update(@RequestBody MenuItem menuItem){return menuItemService.save(menuItem);}
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id)
    {
        log.info("Delete request{}",id);
        this.menuItemService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("read/{id}")
    public ResponseEntity<MenuItem>read(@PathVariable String id)
    {
        log.info("Read request: {}", id);
        MenuItem menuItem= this.menuItemService.read(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
        return ResponseEntity.ok(menuItem);
    }

    @GetMapping("find-all")
    public ResponseEntity<List<MenuItem>> findAll()
    {
        List<MenuItem> menuItemList=this.menuItemService.findAll();
        return ResponseEntity.ok(menuItemList);
    }
}

