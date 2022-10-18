package za.ac.cput.service.menuService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Menu;
import za.ac.cput.repository.menuRepository.IMenuRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MenuServiceImpl implements IMenuService {
    private final IMenuRepository repository;

    @Autowired
    public MenuServiceImpl(IMenuRepository repository){this.repository=repository;}

    @Override
    public Menu save(Menu menu){return this.repository.save(menu);}

    public Menu update(Menu menu){return this.repository.save(menu);}
    @Override
    public Optional<Menu> read(String id){return this.repository.findById(id);}

    @Override
    public void delete(Menu menu){this.repository.delete(menu);}
    @Override
    public List<Menu> findAll(){return this.repository.findAll();}


    public void deleteById(String id){
        repository.deleteById(id);
        Optional<Menu> menu=read(id);
        if(menu.isPresent()){
            delete(menu.get());
        }
    }

}

