package guru.springframework.config;

import guru.springframework.dto.TokenDto;
import guru.springframework.dto.UserRequest;
import guru.springframework.model.User;

public interface JwtGenerator {

    TokenDto generateToken(User user);
}
