package it.epicode.entities;

import it.epicode.entities.constants.Tables;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.StringJoiner;

@Entity
@Table (name = Tables.Names.BOOKS)
public class Book extends LibraryItem {
    @Column (length = 30, nullable = false)
    private String author;
    @Column (length = 30)
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
        return new StringJoiner(", ", Book.class.getSimpleName() + "[", "]")
                .add("author='" + author + "'")
                .add("genre='" + genre + "'")
                .toString();
    }
}
