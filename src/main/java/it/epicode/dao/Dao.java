package it.epicode.dao;

import java.util.Optional;

public interface Dao <T> extends AutoCloseable {
    T addItem(T item);
    Optional<T> deleteByISBN(long ISBN);
}
