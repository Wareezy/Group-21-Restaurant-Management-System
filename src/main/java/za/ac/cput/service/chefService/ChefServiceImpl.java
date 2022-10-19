package za.ac.cput.service.chefService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Chef;
import za.ac.cput.repository.chefRepository.IChefRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ChefServiceImpl implements IChefService {
    private final IChefRepository repository;

    @Autowired
    public ChefServiceImpl(IChefRepository repository){this.repository=repository;}

    @Override
    public Chef save(Chef chef){return this.repository.save(chef);}

    public Chef update(Chef chef){return this.repository.save(chef);}

    @Override
    public Optional<Chef> read(String id){return this.repository.findById(id);}

    @Override
    public void delete(Chef chef){this.repository.delete(chef);}
    @Override
    public List<Chef> findAll(){return this.repository.findAll();}


    public void deleteById(String id){
        repository.deleteById(id);
        Optional<Chef> chef=read(id);
        if(chef.isPresent()){
            delete(chef.get());
        }
    }
    @Override
    public Optional<Chef>findByEmail(String email){return this.repository.findByEmail(email);}
}
