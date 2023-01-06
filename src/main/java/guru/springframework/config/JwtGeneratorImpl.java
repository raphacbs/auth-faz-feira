package guru.springframework.config;

import guru.springframework.dto.TokenDto;
import guru.springframework.dto.UserRequest;
import guru.springframework.model.User;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import io.jsonwebtoken.Jwts;

@Service
public class JwtGeneratorImpl implements JwtGenerator {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${app.jwttoken.message}")
    private String message;

    @Override
    public TokenDto generateToken(User user) {
        final LocalDateTime localDateTime = LocalDateTime.now().plusHours(3);
        Date expireDate = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        String jwtToken="";
        jwtToken = Jwts.builder()
                .setSubject(user.getId().toString())
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "secret")
                .setExpiration(expireDate)
                .compact();

        return TokenDto.builder()
                .token(jwtToken)
                .message(message)
                .expiredAt(localDateTime.format(DateTimeFormatter
                        .ofPattern("yyyy-MM-dd HH:mm:ss")))
                .build();
    }
}
