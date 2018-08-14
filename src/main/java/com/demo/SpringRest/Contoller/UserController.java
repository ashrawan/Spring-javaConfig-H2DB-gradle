package com.demo.SpringRest.Contoller;

import com.demo.SpringRest.Model.User;
import com.demo.SpringRest.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

//    public UserController(final UserService userService) {
//        this.userService = userService;
//    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String hello(){
        System.out.println("hello");
        return "Its Working";
    }


    @GetMapping("/users")
    public ResponseEntity<List<User>> retrieveAllUsers() {

        List<User> userList = userService.getAllUsers();
        if(userList==null || userList.size()==0 ){
            return new ResponseEntity(userList,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<User>>(userList, HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {

        User userRe = userService.addUser(user);
        if(userRe == null ){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<User>(userRe, HttpStatus.OK);

    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable int id) {
        user.setId(id);
        User u = userService.updateUser(user);
        if(u == null){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<User>(u, HttpStatus.OK);

    }


    @DeleteMapping("/users/{id}")
    public ResponseEntity deleteUser(@PathVariable int id) {

        try {
            userService.deleteUser(id);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }

        return new ResponseEntity(HttpStatus.OK);
    }
}