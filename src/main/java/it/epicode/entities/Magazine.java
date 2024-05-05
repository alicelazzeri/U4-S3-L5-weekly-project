package it.epicode.entities;

import it.epicode.entities.constants.Tables;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

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

}
