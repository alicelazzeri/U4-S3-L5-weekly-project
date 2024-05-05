package it.epicode.entities;

import it.epicode.entities.constants.Tables;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.StringJoiner;

@Entity
@Table(name = Tables.Names.BOOK_LOANS)

public class BookLoan {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn (name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "library_item_id")
    private LibraryItem lentElement;

    @Column  (nullable = false)
    private LocalDate beginningOfLoan;
    @Column (nullable = false)
    private LocalDate expectedEndingOfLoan;
    @Column  (nullable = false)
    private LocalDate actualEndingOfLoan;

    public BookLoan(long id, User user, LibraryItem lentElement, LocalDate beginningOfLoan, int daysToAdd, Date expectedEndingOfLoan, LocalDate actualEndingOfLoan) {
        this.id = id;
        this.user = user;
        this.lentElement = lentElement;
        this.beginningOfLoan = beginningOfLoan;
        this.expectedEndingOfLoan = beginningOfLoan.plusDays(30);
        this.actualEndingOfLoan = actualEndingOfLoan;
    }

    public BookLoan() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LibraryItem getLentElement() {
        return lentElement;
    }

    public void setLentElement(LibraryItem lentElement) {
        this.lentElement = lentElement;
    }

    public LocalDate getBeginningOfLoan() {
        return beginningOfLoan;
    }

    public void setBeginningOfLoan(LocalDate beginningOfLoan) {
        this.beginningOfLoan = beginningOfLoan;
    }

    public LocalDate getExpectedEndingOfLoan() {
        return expectedEndingOfLoan;
    }

    public void setExpectedEndingOfLoan(LocalDate expectedEndingOfLoan) {
        this.expectedEndingOfLoan = expectedEndingOfLoan;
    }

    public LocalDate getActualEndingOfLoan() {
        return actualEndingOfLoan;
    }

    public void setActualEndingOfLoan(LocalDate actualEndingOfLoan) {
        this.actualEndingOfLoan = actualEndingOfLoan;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", BookLoan.class.getSimpleName() + "[", "]")
                .add("ID = " + getId())
                .add("User = " + getUser())
                .add("Lent element = " + getLentElement())
                .add("Beginning of the loan = " + getBeginningOfLoan())
                .add("Expected ending of the loan = " + getExpectedEndingOfLoan())
                .add("Actual ending of the loan = " + getActualEndingOfLoan())
                .toString();
    }
}
