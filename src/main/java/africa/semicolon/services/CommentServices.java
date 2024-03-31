package africa.semicolon.services;

import africa.semicolon.data.model.Comment;
import africa.semicolon.dto.request.UserCommentPostRequest;

public interface CommentServices {
    Comment saveComment(UserCommentPostRequest userCommentPostRequest);
}
