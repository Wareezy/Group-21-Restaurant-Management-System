package za.ac.cput.service.orderTableService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.OrderTable;
import za.ac.cput.repository.orderTableRepository.IOrderTableRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OrderTableServiceImpl implements IOrderTableService {
    private final IOrderTableRepository repository;


    @Autowired
    public OrderTableServiceImpl(IOrderTableRepository repository){
        this.repository = repository;
    }

    @Override
    public OrderTable save(OrderTable orderTable) {return this.repository.save(orderTable);}

    public OrderTable update(OrderTable orderTable){return this.repository.save(orderTable);}
    @Override
    public Optional<OrderTable> read(String id)
    {
        return this.repository.findById(id);
    }

    @Override
    public void delete(OrderTable orderTable) {
        this.repository.delete(orderTable);
    }

    @Override
    public List<OrderTable> findAll() {
        return this.repository.findAll();
    }

    public void deleteById(String id) {
        repository.deleteById(id);
        Optional<OrderTable> orderTable = read(id);
        if (orderTable.isPresent()) {
            delete(orderTable.get());
        }
    }
}

