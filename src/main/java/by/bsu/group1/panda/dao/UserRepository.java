package by.bsu.group1.panda.dao;

import by.bsu.group1.panda.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByUsername(String username);
}
