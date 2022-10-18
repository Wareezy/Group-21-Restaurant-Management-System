package za.ac.cput.controller.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import za.ac.cput.domain.Menu;
import za.ac.cput.domain.Order;
import za.ac.cput.factory.MenuFactory;
import za.ac.cput.factory.OrderFactory;
import za.ac.cput.service.menuService.IMenuService;

import java.util.List;

@RestController
@RequestMapping("restaurant-management/menu/")
@Slf4j
public class MenuController {
    private final IMenuService menuService;
    @Autowired
    public MenuController(IMenuService menuService)
    {
        this.menuService=menuService;
    }

    @PostMapping("save")
    public ResponseEntity<Menu> save(@RequestBody Menu menu)
    {
        log.info("Save request: {}",menu);
        Order validatedOrder;
        Menu validatedMenu;
        try{
            validatedOrder= OrderFactory.build(menu.getOrder().getOrderId(),menu.getOrder().getOrderDetails(),menu.getOrder().getCustomerId(),menu.getOrder().getWaiterId(),menu.getOrder().getChefId());
            validatedMenu= MenuFactory.build(menu.getMenuId(),menu.getMenuDetails(),menu.getTitle(),validatedOrder);
        }catch(IllegalArgumentException e)
        {
            log.info("Save request error: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        Menu save=menuService.save(validatedMenu);
        return ResponseEntity.ok(save);
    }

    @PutMapping("update")
    public Menu update(@RequestBody Menu menu){return menuService.save(menu);}

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id)
    {
        log.info("Delete request{}",id);
        this.menuService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("read/{id}")
    public ResponseEntity<Menu>read(@PathVariable String id)
    {
        log.info("Read request: {}", id);
        Menu menu= this.menuService.read(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
        return ResponseEntity.ok(menu);
    }

    @GetMapping("find-all")
    public ResponseEntity<List<Menu>> findAll()
    {
        List<Menu> menuList=this.menuService.findAll();
        return ResponseEntity.ok(menuList);
    }
}

