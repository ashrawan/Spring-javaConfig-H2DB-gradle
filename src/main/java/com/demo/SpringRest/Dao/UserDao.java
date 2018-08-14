package com.demo.SpringRest.Dao;

import com.demo.SpringRest.Model.User;

import java.util.List;

public interface UserDao {

    List<User> getAllUsers();
    User addUser(User user);
    User getUser(int id);
    User updateUser(User user);
    void deleteUser(int id);
}
