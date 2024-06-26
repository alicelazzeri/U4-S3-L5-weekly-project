package it.epicode.entities;

import it.epicode.entities.constants.Queries;
import it.epicode.entities.constants.Tables;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.*;

@Entity
@Table (name= Tables.Names.USERS)

public class User extends BaseEntity {

    @Column
    private String name;
    @Column
    private String surname;
    @Column
    private LocalDate dateOfBirth;
    @Column
    private int cardNumber;

    @OneToMany (mappedBy ="user")
    private List<BookLoan> bookLoans = new ArrayList<>();

    public User(String name, String surname, LocalDate dateOfBirth, int cardNumber, List<BookLoan> bookLoans) {
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.cardNumber = cardNumber;
        this.bookLoans = bookLoans;
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public List<BookLoan> getBookLoans() {
        return bookLoans;
    }

    public void setBookLoans(List<BookLoan> bookLoans) {
        this.bookLoans = bookLoans;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
                .add("ID = " + getId())
                .add("Name = '" + getName() + "'")
                .add("Surname = '" + getSurname() + "'")
                .add("Date of birth = " + getDateOfBirth())
                .add("Card number = " + getCardNumber())
                .add("List of book loans = " + getBookLoans())
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return getId() == user.getId() && getCardNumber() == user.getCardNumber() && Objects.equals(getName(), user.getName()) && Objects.equals(getSurname(), user.getSurname()) && Objects.equals(getDateOfBirth(), user.getDateOfBirth()) && Objects.equals(getBookLoans(), user.getBookLoans());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getSurname(), getDateOfBirth(), getCardNumber(), getBookLoans());
    }
}
