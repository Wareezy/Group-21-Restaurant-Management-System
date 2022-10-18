package za.ac.cput.service;

import java.util.List;
import java.util.Optional;

public interface IService <T, I>{
    T save (T t);
    Optional<T> read(I id);
    void delete(T t);
    List<T> findAll();
}
