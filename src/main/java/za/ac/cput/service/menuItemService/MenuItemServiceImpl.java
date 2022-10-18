package za.ac.cput.service.menuItemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.MenuItem;
import za.ac.cput.repository.menuItemRepository.IMenuItemRepository;

import java.util.List;
import java.util.Optional;

@Service
class MenuItemServiceImpl implements IMenuItemService{
    private final IMenuItemRepository repository;

    @Autowired
    public MenuItemServiceImpl(IMenuItemRepository repository){this.repository=repository;}

    @Override
    public MenuItem save(MenuItem menuItem){return this.repository.save(menuItem);}

    public MenuItem update(MenuItem menuItem){return this.repository.save(menuItem);}
    @Override
    public Optional<MenuItem> read(String id){return this.repository.findById(id);}

    @Override
    public void delete(MenuItem menuItem){this.repository.delete(menuItem);}

    @Override
    public List<MenuItem> findAll(){return this.repository.findAll();}

    public void deleteById(String id){
        repository.deleteById(id);
        Optional<MenuItem> menuItem=read(id);
        if(menuItem.isPresent()){
            delete(menuItem.get());
        }
    }
}

