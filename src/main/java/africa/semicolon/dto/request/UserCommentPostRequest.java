package africa.semicolon.dto.request;

import africa.semicolon.data.model.User;
import lombok.Data;

@Data
public class UserCommentPostRequest {
    private String postId;
    private User commenter;
    private String comment;
}
