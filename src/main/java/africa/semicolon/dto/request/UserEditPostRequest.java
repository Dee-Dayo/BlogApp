package africa.semicolon.dto.request;

import lombok.Data;

@Data
public class UserEditPostRequest {
    private String username;
    private String title;
    private String content;
    private String postId;
}
