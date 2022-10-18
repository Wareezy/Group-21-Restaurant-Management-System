package za.ac.cput.service.customerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Customer;
import za.ac.cput.repository.customerRepository.ICustomerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements ICustomerService {

    private final ICustomerRepository repository;


    @Autowired
    public CustomerServiceImpl(ICustomerRepository repository){
        this.repository = repository;
    }

    @Override
    public Customer save(Customer customer) {return this.repository.save(customer);}

    public Customer update(Customer customer){return this.repository.save(customer);}
    @Override
    public Optional<Customer> read(String id)
    {
        return this.repository.findById(id);
    }

    @Override
    public void delete(Customer customer) {
        this.repository.delete(customer);
    }

    @Override
    public List<Customer> findAll() {
        return this.repository.findAll();
    }

    public void deleteById(String id) {
        repository.deleteById(id);
        Optional<Customer> customer = read(id);
        if (customer.isPresent()) {
            delete(customer.get());
        }
    }

    @Override
    public Optional<Customer> findByEmail(String email) {
        return this.repository.findByEmail(email);
    }

}
