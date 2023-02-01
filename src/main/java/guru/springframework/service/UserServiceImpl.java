package guru.springframework.service;

import guru.springframework.config.JwtGenerator;
import guru.springframework.dto.TokenDto;
import guru.springframework.dto.UserDto;
import guru.springframework.dto.UserRequest;
import guru.springframework.exception.UserNotFoundException;
import guru.springframework.model.Role;
import guru.springframework.model.User;
import guru.springframework.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private JwtGenerator jwtGenerator;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtGenerator jwtGenerator){
        this.userRepository=userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtGenerator = jwtGenerator;
    }
    @Override
    public UserDto save(User user) {
        user.setCreatedAt(LocalDateTime.now());
        user.setRole(Role.USER);
        user.setActive(true);
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        user = userRepository.save(user);
        return UserDto.builder()
                .lastName(user.getLastName())
                .firstName(user.getFirstName())
                .id(user.getId())
                .email(user.getEmail())
                .build();
    }

    public Optional<TokenDto> validate(UserRequest userRequest) throws UserNotFoundException {
        final Optional<User> userOptional = userRepository.findByEmail(userRequest.getEmail());

        if(userOptional.isEmpty()){
            throw new UserNotFoundException("Invalid email user not found");
        }

       if(!userOptional.get().isActive()){
           throw new UserNotFoundException("User is not actived");
       }

        boolean isValid = userOptional.get().isActive() && passwordEncoder.matches(userRequest.getPassword(), userOptional.get().getPassword());

        if(isValid){
            User user = userOptional.get();
            UserDto userDto = UserDto.builder()
                    .id(user.getId())
                    .email(user.getEmail())
                    .firstName(user.getFirstName())
                    .lastName(user.getLastName())
                    .build();
            return Optional.ofNullable(this.jwtGenerator.generateToken(userDto));
        }else{
            return Optional.empty();
        }
    }

}
