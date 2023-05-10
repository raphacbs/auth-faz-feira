package guru.springframework.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    private String email;
    @JsonProperty("family_name")
    private String familyName;
    @JsonProperty("given_name")
    private String givenName;
    private String id;
    private String locale;
    private String name;
    private String picture;
    @JsonProperty("verified_email")
    private boolean verifiedEmail;

    private TokenDto  tokenDto;

}
