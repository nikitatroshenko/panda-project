package by.bsu.group1.panda.dao;

import by.bsu.group1.panda.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
