package it.epicode.dao.implementations;

import it.epicode.dao.Dao;
import it.epicode.entities.constants.Queries;
import jakarta.persistence.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public abstract class JpaDao<T> implements Dao<T> {
    private static final Logger logger = LoggerFactory.getLogger(JpaDao.class);
    private static final String PERSISTENCE_UNIT = "gestione_catalogo_bibliotecario";
    protected final EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
    protected final EntityManager em = emf.createEntityManager();
    protected Class<T> type;

    public JpaDao(Class<T> type) {
        this.type = type;
        logger.debug("This class is type {}", type);
    }

    @Override
    public T addItem(T item) {
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.persist(item);
            trans.commit();
            return item;
        } catch (Exception e) {
            trans.rollback();
            logger.error("Exception occurred while adding the item, rolling back transaction", e);
            return null;
        }
    }

    @Override
    public Optional<T> researchByISBN(long ISBN) {
        try {
            Query query = em.createNamedQuery(Queries.LibraryItems.RESEARCH_BY_ISBN);
            query.setParameter("isbn", ISBN);
            List<T> resultList = query.getResultList();
            return resultList.isEmpty() ? Optional.empty() : Optional.of(resultList.get(0));
        } catch (Exception e) {
            logger.error("An error occurred during the research by ISBN", e);
            return Optional.empty();
        }
    }

    @Override
    public Optional<T> removeByISBN(long ISBN) {
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            Query queryDelete = em.createNamedQuery(Queries.LibraryItems.REMOVE_BY_ISBN);
            queryDelete.setParameter( "isbn", ISBN);
            T removedItem = (T) queryDelete.getSingleResult();
            queryDelete.executeUpdate();
            trans.commit();
            return Optional.ofNullable(removedItem);
        } catch (Exception e) {
            trans.rollback();
            logger.error("An error occurred while removing the element, rolling back the transaction", e);
            return Optional.empty();
        }
    }

    @Override
    public Optional<T> researchByYearOfPublication(int yearOfPublication) {
        try {
            Query query = em.createNamedQuery(Queries.LibraryItems.RESEARCH_BY_YEAR_OF_PUBLICATION);
            query.setParameter("yearOfPublication", yearOfPublication);
            List <T> resultList = query.getResultList();
            return resultList.isEmpty() ? Optional.empty() : Optional.of(resultList.get(0));
        } catch (Exception e) {
            logger.error("An error occurred during the research by year of publication", e);
            return Optional.empty();
        }
    }

    @Override
    public Optional<T> researchByAuthor(String author) {
        try {
            Query query = em.createNamedQuery(Queries.Books.RESEARCH_BY_AUTHOR);
            query.setParameter("author", author);
            List <T> resultList = query.getResultList();
            return resultList.isEmpty() ? Optional.empty() : Optional.of(resultList.get(0));
        } catch (Exception e) {
            logger.error("An error occurred while searching for items by author", e);
            return Optional.empty();
        }
    }

    @Override
    public Optional<T> researchByTitle(String title) {
        try {
            Query query = em.createNamedQuery(Queries.LibraryItems.RESEARCH_BY_TITLE);
            query.setParameter("title", title);
            List<T> resultList = query.getResultList();
            return resultList.isEmpty() ? Optional.empty() : Optional.of(resultList.get(0));
        } catch (Exception e) {
            logger.error("An error occurred while searching for items by title", e);
            return Optional.empty();
        }
    }

    @Override
    public Optional<T> researchLentItemsByCardNumber(int cardNumber) {
        try {
            Query query = em.createNamedQuery(Queries.BookLoans.RESEARCH_LENT_ITEMS_BY_CARD_NUMBER);
            query.setParameter("cardNumber", cardNumber);
            List<T> resultList = query.getResultList();
            return resultList.isEmpty() ? Optional.empty() : Optional.of(resultList.get(0));
        } catch (Exception e) {
            logger.error("An error occurred while searching for lent items by card number", e);
            return Optional.empty();
        }
    }

    @Override
    public Optional<T> researchByExpiredAndUnreturnedLoans(LocalDate currentDate) {
        try {
            Query query = em.createNamedQuery(Queries.BookLoans.RESEARCH_BY_EXPIRED_AND_UNRETURNED_LOANS);
            query.setParameter("currentDate", currentDate);
            List<T> resultList = query.getResultList();
            return resultList.isEmpty() ? Optional.empty() : Optional.of(resultList.get(0));
        } catch (Exception e) {
            logger.error("An error occurred while searching for expired and unreturned loans", e);
            return Optional.empty();
        }
    }

    @Override
    public void close() throws Exception {
        em.close();
        emf.close();
    }
}
