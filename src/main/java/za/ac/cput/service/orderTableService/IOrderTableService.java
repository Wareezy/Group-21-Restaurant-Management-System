package za.ac.cput.service.orderTableService;

import za.ac.cput.domain.OrderTable;
import za.ac.cput.service.IService;

public interface IOrderTableService extends IService<OrderTable,String> {
    void deleteById(String id);
}

