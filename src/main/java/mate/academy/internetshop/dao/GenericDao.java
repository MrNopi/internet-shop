package mate.academy.internetshop.dao;

import java.util.List;
import java.util.Optional;
import mate.academy.internetshop.exception.DataProcessingException;

public interface GenericDao<T> {
    T create(T item) throws DataProcessingException;

    Optional<T> get(Long itemId) throws DataProcessingException;

    T update(T item) throws DataProcessingException;

    boolean delete(Long itemId) throws DataProcessingException;
}
