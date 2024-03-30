package africa.semicolon.dto.response;

import lombok.Data;

@Data
public class EditPostResponse {
    private String postId;
    private String postTitle;
    private String postContent;
    private String dateCreated;
}
