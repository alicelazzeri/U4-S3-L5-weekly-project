package it.epicode.entities;

import it.epicode.entities.constants.Queries;
import it.epicode.entities.constants.Tables;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;
import java.util.StringJoiner;

@Entity
@Table(name = Tables.Names.BOOK_LOANS)

@NamedQuery(name = Queries.BookLoans.RESEARCH_BY_EXPIRED_AND_UNRETURNED_LOANS, query = "SELECT bl FROM BookLoan AS bl WHERE bl.actualEndingOfLoan IS NULL AND :currentDate > bl.expectedEndingOfLoan")
@NamedQuery(name = Queries.BookLoans.RESEARCH_LENT_ITEMS_BY_CARD_NUMBER, query = "SELECT bl.libraryItem FROM BookLoan AS bl WHERE bl.user.cardNumber = :cardNumber AND bl.actualEndingOfLoan IS NULL")

public class BookLoan extends BaseEntity {
    @Column
    private LocalDate beginningOfLoan;
    @Column
    private LocalDate expectedEndingOfLoan;
    @Column
    private LocalDate actualEndingOfLoan;

    @ManyToOne
    @JoinColumn (name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "library_item_id")
    private LibraryItem libraryItem;

    public BookLoan(User user, LibraryItem libraryItem, LocalDate beginningOfLoan, LocalDate expectedEndingOfLoan, LocalDate actualEndingOfLoan) {
        this.user = user;
        this.libraryItem = libraryItem;
        this.beginningOfLoan = beginningOfLoan;
        this.expectedEndingOfLoan = beginningOfLoan.plusDays(30);
        this.actualEndingOfLoan = actualEndingOfLoan;
    }

    public BookLoan() {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LibraryItem getLibraryItem() {
        return libraryItem;
    }

    public void setLibraryItem(LibraryItem libraryItem) {
        this.libraryItem = libraryItem;
    }


    @Override
    public String toString() {
        return new StringJoiner(", ", BookLoan.class.getSimpleName() + "[", "]")
                .add("ID = " + getId())
                .add("User = " + getUser())
                .add("Lent element = " + getLibraryItem())
                .add("Beginning of the loan = " + getBeginningOfLoan())
                .add("Expected ending of the loan = " + getExpectedEndingOfLoan())
                .add("Actual ending of the loan = " + getActualEndingOfLoan())
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookLoan bookLoan)) return false;
        return getId() == bookLoan.getId() && Objects.equals(getUser(), bookLoan.getUser()) && Objects.equals(getLibraryItem(), bookLoan.getLibraryItem()) && Objects.equals(getBeginningOfLoan(), bookLoan.getBeginningOfLoan()) && Objects.equals(getExpectedEndingOfLoan(), bookLoan.getExpectedEndingOfLoan()) && Objects.equals(getActualEndingOfLoan(), bookLoan.getActualEndingOfLoan());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUser(), getLibraryItem(), getBeginningOfLoan(), getExpectedEndingOfLoan(), getActualEndingOfLoan());
    }
}
