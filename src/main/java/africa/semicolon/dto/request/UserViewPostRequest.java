package africa.semicolon.dto.request;

import africa.semicolon.data.model.User;
import lombok.Data;

@Data
public class UserViewPostRequest {
    private String postId;
    private User viewer;
}
