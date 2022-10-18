package za.ac.cput.service.paymentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Payment;
import za.ac.cput.repository.paymentRepository.IPaymentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentServiceImpl {
    private final IPaymentRepository repository;


    @Autowired
    public PaymentServiceImpl(IPaymentRepository repository){
        this.repository = repository;
    }

    @Override
    public Payment save(Payment payment) {return this.repository.save(payment);}

    public Payment update(Payment payment){return this.repository.save(payment);}
    @Override
    public Optional<Payment> read(String id)
    {
        return this.repository.findById(id);
    }

    @Override
    public void delete(Payment payment) {
        this.repository.delete(payment);
    }

    @Override
    public List<Payment> findAll() {
        return this.repository.findAll();
    }

    public void deleteById(String id) {
        repository.deleteById(id);
        Optional<Payment> payment = read(id);
        if (payment.isPresent()) {
            delete(payment.get());
        }
    }
}

