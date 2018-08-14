package com.demo.SpringRest.Dao;

import com.demo.SpringRest.Model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao{

    @Autowired
    private SessionFactory sessionFactory;
    Session session = null;


    @Override
    @Transactional
    public List<User> getAllUsers() {
        session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);
        criteria.from(User.class);
        List<User> userList = session.createQuery(criteria).getResultList();
        return userList;
    }


    @Override
    @Transactional
    public User addUser(User user) {
        session = sessionFactory.getCurrentSession();
        session.save(user);
        return user;
    }

    @Override
    @Transactional
    public User getUser(int id) {
        session = sessionFactory.getCurrentSession();
        User user = session.find(User.class, id);
        return user;
    }

    @Override
    @Transactional
    public User updateUser(User user) {
        session = sessionFactory.getCurrentSession();
        session.update(user);
        return user;
    }

    @Override
    @Transactional
    public void deleteUser(int id) {
        session = sessionFactory.getCurrentSession();
        User user = session.find(User.class, id);
        session.delete(user);
    }

}
