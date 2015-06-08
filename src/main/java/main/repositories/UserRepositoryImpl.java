package main.repositories;

import main.entites.Role;
import main.entites.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Michal
 * 2015-06-08.
 */
public class UserRepositoryImpl implements UserRepositoryCustom {
    @PersistenceContext
    private EntityManager em;

    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Role> rolesForUser(User user) {
        em.refresh(user);
        return user.getRoles();
    }
}
