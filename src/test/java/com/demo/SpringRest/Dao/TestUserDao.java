package com.demo.SpringRest.Dao;

import com.demo.SpringRest.Config.HibernateConfig;
import com.demo.SpringRest.Model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@ContextConfiguration(classes = {HibernateConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class TestUserDao {

    @Autowired
    private SessionFactory sessionFactory;
    Session session = null;

    private User user = new User();

    private static final int ID = 1;

    @Before
    public void setUp(){
        session = sessionFactory.openSession();
        user.setFirstName("Ftest");
        user.setLastName("Ltest");
    }

    @After
    public void  tearDown(){
        session.close();
    }

    @Test
    public void testSaveUser() {
        // saving new User
        int user1 = (int) session.save(user);
        System.out.println(user1);

        assertNotNull(user1);
        assertEquals(user, session.find(User.class, ID));
    }

    @Test
    public void testUpdateUser() {
        // getting users from db
        User user1 = session.get(User.class, ID);
        System.out.println("Existing User: " + user1.toString());

        // setting new values to user
        user1.setFirstName("new test");
        user1.setLastName("new last");
        session.update(user1);

        // checking updated user
        User updatedUser = session.get(User.class, ID);
        System.out.println("Updated User: " + updatedUser.toString());
        assertNotNull(user1);

    }

    @Test
    public void testGetUserById() {

        // getting users from db
        User user1 = session.get(User.class, ID);
        System.out.println("Get User By Id: " + user1.toString());
        assertNotNull(user1);

    }

    @Test
    public void testDeleteUser() {

        // getting users from db
        User user1 = session.get(User.class, ID);
        session.delete(user1);
        assertNotNull(user1);

    }
}