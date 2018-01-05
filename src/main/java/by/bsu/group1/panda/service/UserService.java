package by.bsu.group1.panda.service;

import by.bsu.group1.panda.model.User;

public interface UserService {

    User getUserByUsername(String username);

    User createUser(User user);

    User updateUser(long id, User user);

    User getUserById(long id);
}
