package africa.semicolon.utils;

import africa.semicolon.data.model.Post;
import africa.semicolon.data.model.User;
import africa.semicolon.dto.request.UserEditPostRequest;
import africa.semicolon.dto.request.UserPostRequest;
import africa.semicolon.dto.request.UserRegisterRequest;
import africa.semicolon.dto.response.UserRegisterResponse;

import java.time.format.DateTimeFormatter;

public class Mapper {
    public static User map(UserRegisterRequest userRegisterRequest){
        User user = new User();
        user.setUsername(userRegisterRequest.getUsername());
        user.setPassword(userRegisterRequest.getPassword());
        user.setFirstName(userRegisterRequest.getFirstName());
        user.setLastName(userRegisterRequest.getLastName());

        return user;
    }

    public static Post map(UserPostRequest userPostRequest){
        Post post = new Post();
        post.setTitle(userPostRequest.getTitle());
        post.setContent(userPostRequest.getContent());
        post.setId(userPostRequest.getUsername());
        return post;
    }

        public static Post map(UserEditPostRequest userEditPostRequest){
        Post post = new Post();
        post.setTitle(userEditPostRequest.getTitle());
        post.setContent(userEditPostRequest.getContent());
        post.setId(userEditPostRequest.getUsername());
        return post;
    }

    public static UserRegisterResponse map(User user){
        UserRegisterResponse userRegisterResponse = new UserRegisterResponse();
        userRegisterResponse.setUsername(user.getUsername());
        userRegisterResponse.setId(user.getId());
        userRegisterResponse.setDateCreated(DateTimeFormatter.ofPattern("dd-MM-yyyy, hh:mm:ss").format(user.getDateCreated()));
        return userRegisterResponse;
    }
}
