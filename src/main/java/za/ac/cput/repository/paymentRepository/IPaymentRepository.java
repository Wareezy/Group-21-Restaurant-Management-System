package za.ac.cput.repository.paymentRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Payment;
@Repository
public interface IPaymentRepository extends JpaRepository<Payment,String> {
}

