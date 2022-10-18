package za.ac.cput.service.waiterService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Waiter;
import za.ac.cput.repository.waiterRepository.IWaiterRepository;

import java.util.List;
import java.util.Optional;

@Service
public class WaiterServiceImpl implements IWaiterService {
    private final IWaiterRepository repository;

    @Autowired
    public WaiterServiceImpl(IWaiterRepository repository){this.repository=repository;}

    @Override
    public Waiter save(Waiter waiter){return this.repository.save(waiter);}

    public Waiter update(Waiter waiter){return this.repository.save(waiter);}
    @Override
    public Optional<Waiter> read(String id){return this.repository.findById(id);}

    @Override
    public void delete(Waiter waiter){this.repository.delete(waiter);}
    @Override
    public List<Waiter> findAll(){return this.repository.findAll();}


    public void deleteById(String id){
        repository.deleteById(id);
        Optional<Waiter> waiter=read(id);
        if(waiter.isPresent()){
            delete(waiter.get());
        }
    }
    @Override
    public Optional<Waiter>findByEmail(String email){return this.repository.findByEmail(email);}
}
