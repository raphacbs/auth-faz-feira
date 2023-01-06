package guru.springframework.bootstrap;

import guru.springframework.model.Role;
import guru.springframework.model.User;
import guru.springframework.repository.UserRepository;
import guru.springframework.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class AppBootStrap implements CommandLineRunner {
    private UserService userService;

    @Autowired
    public AppBootStrap(UserService userService){
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        User user1 = new User();
        user1.setCreatedAt(LocalDateTime.now());
        user1.setEmail("raphaelcoel@gmail.com");
        user1.setRole(Role.ADMIN);
        user1.setPassword("123");
        user1.setFirstName("Raphael");
        user1.setLastName("Coelho");
        user1.setActive(true);
//        userService.save(user1);

    }
}
