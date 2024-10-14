package com.ilr.user.api.service.impl;

import com.ilr.user.api.model.Login;
import com.ilr.user.api.model.User;
import com.ilr.user.api.repository.UserRepository;
import com.ilr.user.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User insertUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public boolean updateUser(Long id, User user) {
        Optional<User> userData = userRepository.findById(id);

        if(userData.isPresent()){
            User userUpdate = new User();
            userUpdate.setId(userData.get().getId());
            userUpdate.setName(user.getName());
            userUpdate.setLastname(user.getLastname());
            userUpdate.setSecondlastname(user.getSecondlastname());
            userUpdate.setEmail(user.getEmail());
            userUpdate.setPass(user.getPass());

            userRepository.save(userUpdate);
            return true;

        }else{
            return false;
        }
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(Long id) {
        Optional<User> userData = userRepository.findById(id);
        return userData.orElse(null);
    }

    @Override
    public User login(Login login) {
        Optional<User> userData = userRepository.findByEmailAndPassword(login.getEmail(), login.getPassword());
        return userData.orElse(null);
    }
}
