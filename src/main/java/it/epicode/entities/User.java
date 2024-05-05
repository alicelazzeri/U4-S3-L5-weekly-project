package it.epicode.entities;

import it.epicode.entities.constants.Tables;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

@Entity
@Table (name= Tables.Names.USERS)

public class User {
    @Id
    @GeneratedValue (strategy =GenerationType.AUTO)
    private long id;
    @Column (length = 30, nullable = false)
    private String name;
    @Column (length = 30, nullable = false)
    private String surname;
    @Column
    private LocalDate dateOfBirth;
    @Column (length = 10, nullable = false)
    private int cardNumber;

    @OneToMany (mappedBy ="user")
    private List<BookLoan> bookLoans = new ArrayList<>();

    public User(long id, String name, String surname, LocalDate dateOfBirth, int cardNumber, List<BookLoan> bookLoans) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.cardNumber = cardNumber;
        this.bookLoans = bookLoans;
    }

    public User() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
                .add("ID = " + id)
                .add("Name = '" + name + "'")
                .add("Surname = '" + surname + "'")
                .add("Date of birth = " + dateOfBirth)
                .add("Card number = " + cardNumber)
                .add("List of book loans = " + bookLoans)
                .toString();
    }
}
