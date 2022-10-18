package za.ac.cput.service.chefService;
import za.ac.cput.domain.Chef;
import za.ac.cput.service.IService;

import java.util.Optional;

public interface IChefService extends IService<Chef,String> {
    void deleteById(String id);
    Optional<Chef> findByEmail(String email);
}


