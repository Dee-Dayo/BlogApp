package africa.semicolon.services;

import africa.semicolon.dto.request.UserDeletePostRequest;
import africa.semicolon.dto.request.UserEditPostRequest;
import africa.semicolon.dto.request.UserPostRequest;
import africa.semicolon.dto.request.UserRegisterRequest;
import africa.semicolon.dto.response.UserRegisterResponse;
import africa.semicolon.data.model.User;

public interface UserServices {
    UserRegisterResponse register(UserRegisterRequest userRegisterRequest);

    void createPost(UserPostRequest userPostRequest);

    User findByUsername(String username);

    void updatePost(UserEditPostRequest userEditPostRequest);

    void deletePost(UserDeletePostRequest userDeletePostRequest);
}
