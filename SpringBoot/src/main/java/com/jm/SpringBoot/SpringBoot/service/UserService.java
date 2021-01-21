package com.jm.SpringBoot.SpringBoot.service;

import com.jm.SpringBoot.SpringBoot.model.Role;
import com.jm.SpringBoot.SpringBoot.model.User;

import java.util.List;

public interface UserService  {
    List<User> getAllUsers();
    void save(User user);
    User show(long id);
    void update(long id, User updatedUser);
    void delete(long id);
    List<Role> getRoles();
}
