package it.epicode.dao.implementations;

import it.epicode.dao.LibraryItemDao;
import it.epicode.entities.LibraryItem;

public class JpaLibraryItemDao extends JpaDao<LibraryItem> implements LibraryItemDao {
    public JpaLibraryItemDao() {
        super(LibraryItem.class);
    }
}
