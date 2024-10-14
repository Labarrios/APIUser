package com.ilr.user.api.controller;

import com.ilr.user.api.model.Login;
import com.ilr.user.api.model.User;
import com.ilr.user.api.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/api/v1")
public class ControllerUser {

    @Autowired
    UserServiceImpl userServiceImpl;

    @GetMapping("/user")
    public ResponseEntity<List<User>> getUsers (){
        try {
            return new ResponseEntity<>(userServiceImpl.getUsers(), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUser (@PathVariable ("id") long id){
        try {
            return new ResponseEntity<>(userServiceImpl.getUser(id), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/user")
    public ResponseEntity<User> createUser(@RequestBody User user){
        try{
            return new ResponseEntity<>(userServiceImpl.insertUser(user), HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<HttpStatus> updateUser(@PathVariable("id") long id, @RequestBody User user) {
        try{
            if(userServiceImpl.updateUser(id,user)){
                return new ResponseEntity<>(HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") long id){
        try{
            userServiceImpl.deleteUser(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/user/login")
    public ResponseEntity<User> getUsers (@RequestBody Login login){
        try {
            User response = userServiceImpl.login(login);

            if(response != null){
                return new ResponseEntity<>(response, HttpStatus.OK);
            }else {
                return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
            }
        }catch(Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
