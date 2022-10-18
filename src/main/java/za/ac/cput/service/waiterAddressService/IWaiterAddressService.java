package za.ac.cput.service.waiterAddressService;

import za.ac.cput.domain.WaiterAddress;
import za.ac.cput.service.IService;

public interface IWaiterAddressService extends IService<WaiterAddress,String> {
    void deleteById(String id);
}

