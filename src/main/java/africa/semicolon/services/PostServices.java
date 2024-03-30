package africa.semicolon.services;

import africa.semicolon.data.model.Post;
import africa.semicolon.data.model.User;
import africa.semicolon.dto.request.UserEditPostRequest;
import africa.semicolon.dto.request.UserPostRequest;

public interface PostServices {
    Post createPost(UserPostRequest userPostRequest);

    Post updatePost(UserEditPostRequest userEditPostRequest);
}
