package za.ac.cput.service.menuItemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.MenuItem;
import za.ac.cput.repository.menuItemRepository.IMenuItemRepository;
import za.ac.cput.service.IService;

import java.util.List;
import java.util.Optional;

public interface IMenuItemService extends IService<MenuItem,String> {
    void deleteById(String id);


}

