package za.ac.cput.service.menuService;

import za.ac.cput.domain.Menu;
import za.ac.cput.service.IService;

public interface IMenuService extends IService<Menu,String> {
    void deleteById(String id);
}

