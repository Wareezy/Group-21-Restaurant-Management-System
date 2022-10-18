package za.ac.cput.service.customerService;

import za.ac.cput.domain.Customer;
import za.ac.cput.service.IService;

import java.util.Optional;

public interface ICustomerService extends IService<Customer, String> {
    void deleteById(String id);
    Optional<Customer> findByEmail(String email);

}

