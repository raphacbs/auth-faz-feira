package guru.springframework.service;

import guru.springframework.dto.TokenDto;
import guru.springframework.dto.UserDto;
import guru.springframework.dto.UserInfo;
import guru.springframework.dto.UserRequest;
import guru.springframework.exception.UserNotAuthException;
import guru.springframework.exception.UserNotFoundException;
import guru.springframework.model.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService{
    public UserDto save(User user);
    public Optional<TokenDto> validate(UserRequest userRequest) throws UserNotFoundException;
    UserInfo authGoogle(String token) throws UserNotAuthException;

    UserInfo authFacebook(String token)  throws UserNotAuthException;
}
