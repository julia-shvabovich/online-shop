package application.dao;

import java.util.List;
import java.util.Optional;

public interface GenericDao<T, K> {
    T create(T item);

    Optional<T> get(K id);

    List<T> getAll();

    T update(T item);

    boolean delete(K id);
}
