package by.bsu.group1.panda.service.impl;

import by.bsu.group1.panda.dao.UserRepository;
import by.bsu.group1.panda.model.Role;
import by.bsu.group1.panda.model.User;
import by.bsu.group1.panda.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public User createUser(User user) {
        validateUserParams(user);
        return userRepository.save(user);
    }

    @Override
    public User updateUser(long id, User user) {
        User repositoryOne = userRepository.getOne(id);

        validateUserParams(user);

        if (user.getRole() != null) {
            repositoryOne.setRole(user.getRole());
        }
        if (user.getPassword() != null) {
            repositoryOne.setPassword(user.getPassword());
        }
        return userRepository.save(repositoryOne);
    }

    private void validateUserParams(User user) {
        if (Role.UNKNOWN.equals(user.getRole())) {
            throw new IllegalArgumentException("'role' is invalid. Supported values are: "
                    + Arrays.stream(Role.values()).map(Role::getName).collect(Collectors.joining()));
        }
    }

    @Override
    public User getUserById(long id) {
        return userRepository.getOne(id);
    }
}
