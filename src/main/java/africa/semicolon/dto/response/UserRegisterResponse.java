package africa.semicolon.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserRegisterResponse {
    private String username;
    private String password;
    private LocalDateTime dateCreated;
}
