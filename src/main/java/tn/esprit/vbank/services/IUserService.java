package tn.esprit.vbank.services;

import tn.esprit.vbank.entities.User;

import java.util.List;

public interface IUserService {

    User getUser(Long id);

    List<User> getUsers();

    public User addUser(User user);

    User updateUser(User user);

    void deleteUser(Long id);
}
