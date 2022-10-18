package za.ac.cput.service.waiterAddressService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.WaiterAddress;
import za.ac.cput.repository.waiterAddressRepository.IWaiterAddressRepository;

import java.util.List;
import java.util.Optional;
@Service
public class WaiterAddressServiceImpl implements IWaiterAddressService {
    private final IWaiterAddressRepository repository;

    @Autowired
    public WaiterAddressServiceImpl(IWaiterAddressRepository repository){this.repository=repository;}

    @Override
    public WaiterAddress save(WaiterAddress waiterAddress){return this.repository.save(waiterAddress);}

    public WaiterAddress update(WaiterAddress waiterAddress){return this.repository.save(waiterAddress);}

    @Override
    public Optional<WaiterAddress> read(String id){return this.repository.findById(id);}

    @Override
    public void delete(WaiterAddress waiterAddress){this.repository.delete(waiterAddress);}

    @Override
    public List<WaiterAddress> findAll(){return this.repository.findAll();}

    public void deleteById(String id){
        repository.deleteById(id);
        Optional<WaiterAddress> waiterAddress=read(id);
        if(waiterAddress.isPresent()){
            delete(waiterAddress.get());
        }
    }
}


