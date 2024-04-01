package africa.semicolon.dto.request;

import lombok.Data;

@Data
public class UserDeleteCommentRequest {
    private String commentId;
    private String postId;
}
