package it.epicode.dao.implementations;

import it.epicode.dao.UserDao;
import it.epicode.entities.User;

public class JpaUserDao extends JpaDao<User> implements UserDao {
    public JpaUserDao() {
        super(User.class);
    }
}
