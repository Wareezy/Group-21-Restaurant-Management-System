package za.ac.cput.service.waiterService;

import za.ac.cput.domain.Waiter;
import za.ac.cput.service.IService;

import java.util.Optional;

public interface IWaiterService extends IService<Waiter,String> {
    void deleteById(String id);
    Optional<Waiter> findByEmail(String email);
}
