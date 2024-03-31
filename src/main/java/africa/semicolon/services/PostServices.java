package africa.semicolon.services;

import africa.semicolon.data.model.Post;
import africa.semicolon.data.model.User;
import africa.semicolon.dto.request.UserCommentPostRequest;
import africa.semicolon.dto.request.UserEditPostRequest;
import africa.semicolon.dto.request.UserPostRequest;
import africa.semicolon.dto.request.UserViewPostRequest;

public interface PostServices {
    Post createPost(UserPostRequest userPostRequest);

    Post updatePost(UserEditPostRequest userEditPostRequest);

    void viewPost(UserViewPostRequest userViewPostRequest);

    Post findPostById(String postId);

    void commentPost(UserCommentPostRequest userCommentPostRequest);
}
