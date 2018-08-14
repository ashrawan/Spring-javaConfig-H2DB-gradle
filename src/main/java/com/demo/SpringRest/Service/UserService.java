package com.demo.SpringRest.Service;

import com.demo.SpringRest.Model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();
    User addUser(User user);
    User getUser(int id);
    User updateUser(User user);
    void deleteUser(int id) throws Exception;
}
