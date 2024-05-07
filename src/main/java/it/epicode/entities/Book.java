package it.epicode.entities;

import it.epicode.entities.constants.Queries;
import it.epicode.entities.constants.Tables;
import jakarta.persistence.*;

import java.util.Objects;
import java.util.StringJoiner;

@Entity
@Table (name = Tables.Names.BOOKS)
@DiscriminatorValue("Books")

@NamedQuery(name = Queries.Books.RESEARCH_BY_AUTHOR, query = "SELECT b FROM Book AS b WHERE b.author LIKE :author")

public class Book extends LibraryItem {
    @Column
    private String author;
    @Column
    private String genre;

    public Book(long ISBN, String title, int yearOfPublication, int numberOfPages, String author, String genre) {
        super(ISBN, title, yearOfPublication, numberOfPages);
        this.author = author;
        this.genre = genre;
    }

    public Book() {
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Book.class.getSimpleName() + " [", "]")
                .add("ISBN = '" + getISBN() + "'")
                .add("Title = '" + getTitle() + "'")
                .add("Author = '" + getAuthor() + "'")
                .add("Genre = '" + getGenre() + "'")
                .add("Year of publication = '" + getYearOfPublication() + "'")
                .add("Number of pages = '" + getNumberOfPages() + "'")
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book book)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(getAuthor(), book.getAuthor()) && Objects.equals(getGenre(), book.getGenre());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getAuthor(), getGenre());
    }
}
