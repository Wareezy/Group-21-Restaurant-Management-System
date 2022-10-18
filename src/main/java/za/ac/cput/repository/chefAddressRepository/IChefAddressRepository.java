package za.ac.cput.repository.chefAddressRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.ChefAddress;

@Repository
public interface IChefAddressRepository extends JpaRepository <ChefAddress,String>{

}

