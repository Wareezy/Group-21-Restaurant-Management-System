package za.ac.cput.repository.waiterAddressRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.cput.domain.WaiterAddress;

public interface IWaiterAddressRepository extends JpaRepository<WaiterAddress,String> {
}

