package za.ac.cput.service.chefAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.ChefAddress;
import za.ac.cput.repository.chefAddressRepository.IChefAddressRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ChefAddressServiceImpl implements IChefAddressService {
    private final IChefAddressRepository repository;

    @Autowired
    public ChefAddressServiceImpl(IChefAddressRepository repository){this.repository=repository;}

    @Override
    public ChefAddress save(ChefAddress chefAddress){return this.repository.save(chefAddress);}


    public ChefAddress update(ChefAddress chefAddress){return this.repository.save(chefAddress);}

    @Override
    public Optional<ChefAddress>read(String id){return this.repository.findById(id);}

    @Override
    public void delete(ChefAddress chefAddress){this.repository.delete(chefAddress);}

    @Override
    public List<ChefAddress> findAll(){return this.repository.findAll();}

    public void deleteById(String id){
        repository.deleteById(id);
        Optional<ChefAddress> chefAddress=read(id);
        if(chefAddress.isPresent()){
            delete(chefAddress.get());
        }
    }
}
