package com.demo.SpringRest.Service;

import com.demo.SpringRest.Dao.UserDao;
import com.demo.SpringRest.Model.User;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

//    @Autowired
//    public UserServiceImpl(final UserDao userDao) {
//        this.userDao = userDao;
//    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public User addUser(User user) {
        return userDao.addUser(user);
    }

    @Override
    public User getUser(int id) {
        return userDao.getUser(id);
    }

    @Override
    public User updateUser(User user) {
        return userDao.updateUser(user);
    }

    @Override
    public void deleteUser(int id) throws Exception {
        if(userDao.getUser(id) == null){
            throw new Exception("user not found");
        }
        userDao.deleteUser(id);
    }
}
