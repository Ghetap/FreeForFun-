package com.example.freeforfun.freeforfun.persistence.repo;

import com.example.freeforfun.freeforfun.persistence.exceptions.RepositoryException;
import com.example.freeforfun.freeforfun.persistence.model.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class UserDao {

    @PersistenceContext(unitName = "freeForFun-persistence")
    private EntityManager entityManager;

    public User findByUsernameAndPassword(String username, String password) throws RepositoryException {
        Query query = entityManager.createNamedQuery(User.QUERY_FIND_BY_USERNAME_AND_PASSWORD);
        query.setParameter("username", username);
        query.setParameter("password", password);
        try {
            User user = (User) query.getSingleResult();
            return user;
        } catch (NoResultException | NonUniqueResultException ex) {
            throw new RepositoryException("User with the given username and password was" +
                    "not found!", "freeForFun - 001");
        }
    }

    public User findUserByUsername(String username) throws RepositoryException {
        Query query = entityManager.createNamedQuery(User.QUERY_FIND_BY_USERNAME);
        query.setParameter("username", username);
        try {
            User user = (User) query.getSingleResult();
            return user;
        } catch (NoResultException | NonUniqueResultException ex) {
            throw new RepositoryException("User with the given username was not found!", "freeForFun - 002");
        }
    }

    public Integer deleteUserByUsername(String username) throws RepositoryException {
        Integer result = -1;
        User user = findUserByUsername(username);
        try{
            if (user != null) {
                Query query = entityManager.createNamedQuery(User.QUERY_DELETE_AFTER_USERNAME);
                query.setParameter("username", username);
                result = query.executeUpdate();
            }
        } catch(Exception ex) {
            throw new RepositoryException("User with the given username was not deleted!", "freeForFun - 003");
        }
        return result;
    }

    public User updateUser(User newDataUser) throws RepositoryException {
        try{
        User user = entityManager.merge(newDataUser);
        entityManager.flush();
        return user;
        } catch(Exception ex){
            throw new RepositoryException("User could not be updated!", "freeForFun - 004");
        }
    }
}
