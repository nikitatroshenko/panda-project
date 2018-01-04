package by.bsu.group1.panda.service.impl;

import by.bsu.group1.panda.dao.UserRepository;
import by.bsu.group1.panda.model.User;
import by.bsu.group1.panda.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        user.getRole(); // todo: add something better to validate user role
        return userRepository.save(user);
    }

    @Override
    public User getUserById(long id) {
        return userRepository.getOne(id);
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
