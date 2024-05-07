package it.epicode.dao;

import it.epicode.entities.Book;
import it.epicode.entities.LibraryItem;
import it.epicode.entities.constants.Queries;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class JpaLibraryItemDao {
    private static final Logger logger = LoggerFactory.getLogger(JpaLibraryItemDao.class);
    private EntityManager em;
    public JpaLibraryItemDao(EntityManager em) {
        this.em = em;
    }

    public void addItem(LibraryItem libraryItem) {
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.persist(libraryItem);
            trans.commit();
            logger.debug("Library item added {}", libraryItem);
        } catch (Exception e) {
            trans.rollback();
            logger.error("Error while adding items, rolling back transaction", e);
        }
    }

    public LibraryItem removeByISBN(long isbn) {
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            Query query = em.createNamedQuery(Queries.LibraryItems.REMOVE_BY_ISBN, LibraryItem.class);
            query.setParameter("isbn", isbn);
            var result = query.getSingleResult();
            query.executeUpdate();
            trans.commit();
            logger.debug("Item removed by ISBN {}", isbn);
            return (LibraryItem) result;
        } catch (NoResultException e) {
            logger.debug("No item found with ISBN: {}", isbn);
            return null;
        } catch (Exception e) {
            trans.rollback();
            logger.error("Error while removing item by ISBN, rolling back transaction", e);
            return null;
        }
    }

    public LibraryItem researchByISBN(long isbn) {
        try {
            Query query = em.createNamedQuery(Queries.LibraryItems.RESEARCH_BY_ISBN, LibraryItem.class);
             query.setParameter("isbn", isbn);
             var result = query.getSingleResult();
            logger.debug("Item found with ISBN {}", isbn);
            return (LibraryItem) result;
        } catch (Exception e) {
            logger.error("Error while researching items by ISBN code", e);
            return null;
        }
    }

    public List<LibraryItem> researchByYearOfPublication(int yearOfPublication) {
        try {
            Query query = em.createNamedQuery(Queries.LibraryItems.RESEARCH_BY_YEAR_OF_PUBLICATION, LibraryItem.class);
            query.setParameter("yearOfPublication", yearOfPublication);
            List<LibraryItem> result = query.getResultList();
            logger.debug("Item found by year of publication {}", yearOfPublication);
            return result;
        } catch (Exception e) {
            logger.error("Error while researching items by year of publication", e);
            return null;
        }
    }

    public List<Book> researchByAuthor(String author) {
        try {
            Query query = em.createNamedQuery(Queries.Books.RESEARCH_BY_AUTHOR, Book.class);
            query.setParameter("author", author);
            List<Book> result = query.getResultList();
            logger.debug("Item found by author {}", author);
            return result;
        } catch (Exception e) {
            logger.error("Error while researching items by author", e);
            return null;
        }
    }

    public List<LibraryItem> researchByTitle (String title){
        try {
            Query query = em.createNamedQuery(Queries.LibraryItems.RESEARCH_BY_TITLE, LibraryItem.class);
            query.setParameter("title",title);
            List<LibraryItem> result = query.getResultList();
            logger.debug("Item found by title {}", title);
            return result;
        } catch (Exception e) {
            logger.error("Error while researching items by title", e);
            return null;
        }
    }


    //    public void removeByISBN(long isbn) {
//        EntityTransaction trans = em.getTransaction();
//        try {
//            LibraryItem item = em.find(LibraryItem.class, isbn);
//            if(item != null) {
//                em.remove(item);
//                trans.commit();
//                System.out.println(item + " removed");
//            } else {
//                System.out.println(item + " not found");
//            }
//        } catch (Exception e) {
//            trans.rollback();
//            logger.error("Error while removing item by ISBN, rolling back transaction", e);
//        }
//    }
}
