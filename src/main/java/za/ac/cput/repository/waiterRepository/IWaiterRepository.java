package za.ac.cput.repository.waiterRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Waiter;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface IWaiterRepository extends JpaRepository<Waiter,String> {
    Optional<Waiter> findByEmail(String email);
}
