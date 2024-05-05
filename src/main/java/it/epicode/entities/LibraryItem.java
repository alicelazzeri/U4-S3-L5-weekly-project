package it.epicode.entities;

import it.epicode.entities.constants.Tables;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

@Entity
@Inheritance (strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = Tables.Names.LIBRARY_ITEM)

public abstract class LibraryItem extends BaseEntity {
    @Column (length = 13, nullable = false)
    private long ISBN;
    @Column(length = 50, nullable = false)
    private String title;
    @Column
    private int yearOfPublication;
    @Column
    private int numberOfPages;

    @OneToMany (mappedBy = "libraryItem")
    private List<BookLoan> bookLoans = new ArrayList<>();

    public LibraryItem(long ISBN, String title, int yearOfPublication, int numberOfPages) {
        this.ISBN = ISBN;
        this.title = title;
        this.yearOfPublication = yearOfPublication;
        this.numberOfPages = numberOfPages;
    }

    public LibraryItem() {
    }

    public long getISBN() {
        return ISBN;
    }

    public void setISBN(long ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYearOfPublication() {
        return yearOfPublication;
    }

    public void setYearOfPublication(int yearOfPublication) {
        this.yearOfPublication = yearOfPublication;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public List<BookLoan> getBookLoans() {
        return bookLoans;
    }

    public void setBookLoans(List<BookLoan> bookLoans) {
        this.bookLoans = bookLoans;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", LibraryItem.class.getSimpleName() + "[", "]")
                .add("ISBN=" + ISBN)
                .add("title='" + title + "'")
                .add("yearOfPublication=" + yearOfPublication)
                .add("numberOfPages=" + numberOfPages)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LibraryItem that)) return false;
        if (!super.equals(o)) return false;
        return getISBN() == that.getISBN() && getYearOfPublication() == that.getYearOfPublication() && getNumberOfPages() == that.getNumberOfPages() && Objects.equals(getTitle(), that.getTitle()) && Objects.equals(getBookLoans(), that.getBookLoans());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getISBN(), getTitle(), getYearOfPublication(), getNumberOfPages(), getBookLoans());
    }
}
