package africa.semicolon.services;

import africa.semicolon.dto.request.UserDeletePostRequest;
import africa.semicolon.dto.request.UserEditPostRequest;
import africa.semicolon.dto.request.UserPostRequest;
import africa.semicolon.dto.request.UserRegisterRequest;
import africa.semicolon.dto.response.CreatePostResponse;
import africa.semicolon.dto.response.EditPostResponse;
import africa.semicolon.dto.response.UserRegisterResponse;
import africa.semicolon.data.model.User;

public interface UserServices {
    UserRegisterResponse register(UserRegisterRequest userRegisterRequest);

    CreatePostResponse createPost(UserPostRequest userPostRequest);

    User findByUsername(String username);

    EditPostResponse updatePost(UserEditPostRequest userEditPostRequest);

    void deletePost(UserDeletePostRequest userDeletePostRequest);
}
