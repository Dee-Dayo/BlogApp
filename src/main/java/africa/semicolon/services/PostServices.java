package africa.semicolon.services;

import africa.semicolon.data.model.Post;
import africa.semicolon.data.model.User;
import africa.semicolon.dto.request.*;

public interface PostServices {
    Post createPost(UserPostRequest userPostRequest);

    Post updatePost(UserEditPostRequest userEditPostRequest);

    void viewPost(UserViewPostRequest userViewPostRequest);

    Post findPostById(String postId);

    void commentPost(UserCommentPostRequest userCommentPostRequest);

    void deleteComment(UserDeleteCommentRequest userDeleteCommentRequest);

    void deletePost(UserDeletePostRequest userDeletePostRequest);
}
