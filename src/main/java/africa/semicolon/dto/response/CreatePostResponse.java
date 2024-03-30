package africa.semicolon.dto.response;

import lombok.Data;

@Data
public class CreatePostResponse {
    private String postId;
    private String postTitle;
    private String postContent;
    private String dateCreated;
}
