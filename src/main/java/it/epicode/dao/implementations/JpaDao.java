package it.epicode.dao.implementations;

import it.epicode.dao.Dao;
import it.epicode.entities.LibraryItem;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
        var trans = em.getTransaction();
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
    public Optional<T> deleteByISBN(long ISBN) {
        var trans = em.getTransaction();
        try {
            trans.begin();
            var item = em.find(type, ISBN);
            if (item != null) {
                em.remove(item);
                trans.commit();
                return Optional.of(item);
            } else {
                trans.rollback();
                return Optional.empty();
            }
        } catch (Exception e) {
            trans.rollback();
            logger.error("Exception occurred while deleting the item, rolling back the transaction", e);
            return Optional.empty();
        }
    }

    @Override
    public void close() throws Exception {
        em.close();
        emf.close();
    }
}
