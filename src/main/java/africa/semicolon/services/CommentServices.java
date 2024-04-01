package africa.semicolon.services;

import africa.semicolon.data.model.Comment;
import africa.semicolon.dto.request.UserCommentPostRequest;
import africa.semicolon.dto.request.UserDeleteCommentRequest;

public interface CommentServices {
    Comment saveComment(UserCommentPostRequest userCommentPostRequest);

    Comment findById(String postId);

    Comment deleteComment(UserDeleteCommentRequest userDeleteCommentRequest);
}
