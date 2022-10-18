package za.ac.cput.repository.chefRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Chef;

import java.util.Optional;

@Repository
public interface IChefRepository extends JpaRepository<Chef,String> {
    Optional<Chef> findByEmail(String email);
}
