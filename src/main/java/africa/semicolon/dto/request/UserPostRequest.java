package africa.semicolon.dto.request;

import lombok.Data;

@Data
public class UserPostRequest {
    private String username;
    private String title;
    private String content;
}
