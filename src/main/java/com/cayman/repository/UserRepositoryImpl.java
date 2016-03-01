package com.cayman.repository;

import com.cayman.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User getUser(int userId) {
        return entityManager.find(User.class, userId);
    }

    @Override
    public User saveUser(User user) {
        if (user.isNew()) {
            entityManager.persist(user);
            //entityManager.refresh(user);
            return user;
        } else {
            return entityManager.merge(user);
        }
    }

    @Override
    public boolean deleteUser(int userId) {
        return entityManager.createNamedQuery(User.DELETE).setParameter("id", userId).executeUpdate() != 0;
    }

    @Override
    public List<User> getAll() {
        return entityManager.createNamedQuery(User.GET_ALL, User.class).getResultList();
    }

    @Override
    public User getByLogin(String login) {
        return (User) entityManager.createNamedQuery(User.GET_BY_LOGIN).setParameter("login", login).getSingleResult();
    }
}
