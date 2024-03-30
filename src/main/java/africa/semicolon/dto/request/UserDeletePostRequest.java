package africa.semicolon.dto.request;

import lombok.Data;

@Data
public class UserDeletePostRequest {
    private String postId;
    private String username;
}
