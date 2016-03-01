package com.cayman.repository;


import com.cayman.entity.Message;
import com.cayman.entity.User;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
public class MessageRepositoryImpl implements MessageRepository{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Message get(int userId, int messageId) {
        List<Message> resultList = entityManager.createNamedQuery(Message.GET, Message.class)
                .setParameter("messageId", messageId)
                .setParameter("userId", userId)
                .getResultList();
        return DataAccessUtils.singleResult(resultList);
    }

    @Override
    public List<Message> getAll(int userId) {
        return entityManager.createNamedQuery(Message.GET_ALL, Message.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    @Override
    public Message save(Message message, int userId) {
        User user = entityManager.getReference(User.class, userId);
        message.setUser(user);
        if (message.isNew()) {
            entityManager.persist(message);
            return message;
        } else {
            return entityManager.merge(message);
        }
    }

    @Override
    public boolean delete(int userId, int messageId) {
        return entityManager.createNamedQuery(Message.DELETE)
                .setParameter("messageId", messageId)
                .setParameter("userId", userId)
                .executeUpdate() != 0;
    }
}
