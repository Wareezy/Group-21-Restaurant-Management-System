package za.ac.cput.repository.orderTableRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.OrderTable;

@Repository
public interface IOrderTableRepository extends JpaRepository<OrderTable,String> {
}

