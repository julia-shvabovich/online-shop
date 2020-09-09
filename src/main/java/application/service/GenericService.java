package application.service;

import java.util.List;

public interface GenericService<T, K> {
    T create(T item);

    T get(K id);

    List<T> getAll();

    boolean delete(K id);

}
