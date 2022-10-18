package za.ac.cput.service.chefAddressService;

import za.ac.cput.domain.ChefAddress;
import za.ac.cput.service.IService;

public interface IChefAddressService extends IService<ChefAddress,String> {

    void deleteById(String id);
}

