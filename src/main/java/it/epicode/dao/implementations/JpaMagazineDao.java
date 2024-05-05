package it.epicode.dao.implementations;

import it.epicode.dao.MagazineDao;
import it.epicode.entities.Magazine;

public class JpaMagazineDao extends JpaDao<Magazine> implements MagazineDao {
    public JpaMagazineDao() { super(Magazine.class); }
}
