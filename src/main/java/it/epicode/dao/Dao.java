package it.epicode.dao;

import it.epicode.entities.LibraryItem;

import java.time.LocalDate;
import java.util.Optional;

public interface Dao <T> extends AutoCloseable {
    T addItem (T item);
    Optional <T> researchByISBN(long ISBN);
    Optional<T> removeByISBN(long ISBN);
    Optional<T> researchByYearOfPublication(int yearOfPublication);
    Optional<T> researchByAuthor(String author);
    Optional<T> researchByTitle (String title);
    Optional<T> researchLentItemsByCardNumber(int cardNumber);
    Optional<T> researchByExpiredAndUnreturnedLoans(LocalDate currentDate);
}

