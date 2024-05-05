package it.epicode.dao.implementations;

import it.epicode.dao.BookDao;
import it.epicode.entities.Book;

public class JpaBookDao extends JpaDao<Book> implements BookDao {
    public JpaBookDao() {
        super (Book.class);
    }
}
