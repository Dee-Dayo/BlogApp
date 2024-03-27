package africa.semicolon.dto.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserRegisterRequest {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
}
