package com.ilr.user.api.service;

import com.ilr.user.api.model.Login;
import com.ilr.user.api.model.User;

import java.util.List;

public interface UserService {

    public User insertUser (User user);
    public boolean updateUser (Long id, User user);
    public void deleteUser (Long id);
    public List<User> getUsers ();
    public User getUser(Long id);
    public User login (Login login);

}
