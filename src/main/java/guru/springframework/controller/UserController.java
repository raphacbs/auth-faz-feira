package guru.springframework.controller;

import guru.springframework.config.JwtGenerator;
import guru.springframework.dto.TokenDto;
import guru.springframework.dto.UserRequest;
import guru.springframework.exception.UserNotFoundException;
import guru.springframework.model.User;
import guru.springframework.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/user")
public class UserController {
    private UserService userService;



    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> postUser(@RequestBody User user) {
        try {
            userService.save(user);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserRequest user) {
        try {
            if (user.getEmail() == null || user.getPassword() == null) {
                throw new UserNotFoundException("UserName or Password is Empty");
            }
            final Optional<TokenDto> tokenDtoOptional = this.userService.validate(user);

           if(tokenDtoOptional.isPresent()){
               return new ResponseEntity<>(tokenDtoOptional.get(), HttpStatus.OK);
           }else{
               return new ResponseEntity<>(new UserNotFoundException("error"), HttpStatus.UNAUTHORIZED);
           }

        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }
}
