package it.epicode.dao;

import it.epicode.entities.BookLoan;
import it.epicode.entities.LibraryItem;
import it.epicode.entities.User;
import it.epicode.entities.constants.Queries;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.List;

public class JpaBookLoanDao {
    private static final Logger logger = LoggerFactory.getLogger(JpaBookLoanDao.class);
    EntityManager em;

    public JpaBookLoanDao(EntityManager em) {
        this.em = em;
    }

    public void addItem(BookLoan bookLoan) {
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.persist(bookLoan);
            trans.commit();
            logger.debug("Book loan added {}", bookLoan);
        } catch (Exception e) {
            trans.rollback();
            logger.error("Error while adding book loans, rolling back transaction", e);
        }
    }

    public void deleteItem (long id) {
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            var item = em.find(BookLoan.class, id);
            if (item != null) {
                em.remove(item);
                trans.commit();
                logger.debug("Item removed by ID {}", id);
            } else {
                logger.debug("Item not found");
            }
        } catch(Exception e) {
            trans.rollback();
            logger.error("Error while removing item by ID, rolling back transaction", e);
        }
    }

    public List<BookLoan> researchLentItemsByCardNumber(int cardNumber) {
        try {
            Query query = em.createNamedQuery(Queries.BookLoans.RESEARCH_LENT_ITEMS_BY_CARD_NUMBER, BookLoan.class);
            query.setParameter("cardNumber", cardNumber);
            List<BookLoan> result = query.getResultList();
            logger.debug("Lent items found by card number {}", cardNumber);
            return result;
        } catch (Exception e) {
            logger.error("Error while researching lent items by card number", e);
            return null;
        }
    }

    public List<BookLoan> researchByExpiredAndUnreturnedLoans(LocalDate currentDate) {
        try {
            Query query = em.createNamedQuery(Queries.BookLoans.RESEARCH_BY_EXPIRED_AND_UNRETURNED_LOANS, BookLoan.class);
            query.setParameter("currentDate", currentDate);
            List<BookLoan> result = query.getResultList();
            logger.debug("Lent items found by expired and unreturned loans {}", currentDate);
            return result;
        } catch (Exception e) {
            logger.error("Error while researching lent items by expired and unreturned loans", e);
            return null;
        }
    }

}
