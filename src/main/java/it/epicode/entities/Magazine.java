package it.epicode.entities;

import it.epicode.entities.constants.Tables;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Objects;
import java.util.StringJoiner;

@Entity
@Table (name = Tables.Names.MAGAZINES)

public class Magazine extends LibraryItem {
    @Column (length = 20, nullable = false)
    private Periodicity periodicity;

    public Magazine(long ISBN, String title, int yearOfPublication, int numberOfPages, Periodicity periodicity) {
        super(ISBN, title, yearOfPublication, numberOfPages);
        this.periodicity = periodicity;
    }

    public Magazine() {
    }

    public Periodicity getPeriodicity() {
        return periodicity;
    }

    public void setPeriodicity(Periodicity periodicity) {
        this.periodicity = periodicity;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Magazine.class.getSimpleName() + " [", "]")
                .add("ISBN=" + getISBN())
                .add("Title = '" + getTitle() + "'")
                .add("Year of publication = " + getYearOfPublication())
                .add("Number of pages = " + getNumberOfPages())
                .add("Periodicity = " + getPeriodicity())
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Magazine magazine)) return false;
        if (!super.equals(o)) return false;
        return getPeriodicity() == magazine.getPeriodicity();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getPeriodicity());
    }
}
