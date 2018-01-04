package by.bsu.group1.panda.web;

import by.bsu.group1.panda.dao.UserRepository;
import by.bsu.group1.panda.model.User;
import by.bsu.group1.panda.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.EntityNotFoundException;
import java.net.URI;
import java.util.Collection;

@RestController
@RequestMapping("/users")
public class UserEndpoint {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @GetMapping("")
    public Collection<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable long id) {
        return userRepository.getOne(id);
    }

    @PostMapping("")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        if (user == null) {
            user = new User();
        }
        user = userService.createUser(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable long id, @RequestBody User user) {
        if (!userRepository.exists(id)) {
            return ResponseEntity.notFound().build();
        }
        if (user.getId() != id) {
            return ResponseEntity.badRequest().build();
        }

        user = userRepository.save(user);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable long id) {
        userRepository.delete(id);
    }

    @ExceptionHandler({EntityNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void notFound() {
        // nothing to do
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
