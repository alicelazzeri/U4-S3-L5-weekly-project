package it.epicode.dao.implementations;

import it.epicode.dao.BookLoanDao;
import it.epicode.entities.BookLoan;

public class JpaBookLoanDao extends JpaDao<BookLoan> implements BookLoanDao {
    public JpaBookLoanDao () {
        super(BookLoan.class);
    }
}
