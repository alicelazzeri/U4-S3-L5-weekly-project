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

public class JpaUserDao {
    Logger logger = LoggerFactory.getLogger(JpaUserDao.class);
    private EntityManager em;
    public JpaUserDao(EntityManager em) {
        this.em = em;
    }

    public void addItem(User user) {
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.persist(user);
            trans.commit();
            logger.debug("User added {}", user);
        } catch (Exception e) {
            trans.rollback();
            logger.error("Error while adding users, rolling back transaction", e);
        }
    }

    public void deleteItem (long id) {
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            var item = em.find(User.class, id);
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

    public User researchLentItemsByCardNumber(int cardNumber) {
        var item = em.find(User.class, cardNumber);
        return item;
    }
}
