package za.ac.cput.service.paymentService;

import za.ac.cput.domain.Payment;
import za.ac.cput.service.IService;

public interface IPaymentService extends IService<Payment,String> {
    void deleteById(String id);
}

