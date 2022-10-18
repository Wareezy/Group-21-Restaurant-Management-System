package za.ac.cput.repository.customerRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Customer;

import java.util.Optional;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer, String> {

    Optional<Customer> findByEmail(String email);

}