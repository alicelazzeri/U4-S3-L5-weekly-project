package it.epicode.dao.implementations;

import it.epicode.dao.BaseEntityDao;
import it.epicode.entities.BaseEntity;

public class JpaBaseEntityDao extends JpaDao<BaseEntity> implements BaseEntityDao {
    public JpaBaseEntityDao() {
        super(BaseEntity.class);
    }
}
