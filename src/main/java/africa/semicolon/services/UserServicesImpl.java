package africa.semicolon.services;

import africa.semicolon.data.model.Post;
import africa.semicolon.data.model.User;
import africa.semicolon.data.repository.UserRepository;
import africa.semicolon.dto.request.UserDeletePostRequest;
import africa.semicolon.dto.request.UserEditPostRequest;
import africa.semicolon.dto.request.UserPostRequest;
import africa.semicolon.dto.request.UserRegisterRequest;
import africa.semicolon.dto.response.UserRegisterResponse;
import africa.semicolon.exceptions.PostNotFoundException;
import africa.semicolon.exceptions.UserAlreadyExist;
import africa.semicolon.exceptions.UserDoesntExist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static africa.semicolon.utils.Mapper.map;

@Service
public class UserServicesImpl implements UserServices{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostServices postServices;

    @Override
    public UserRegisterResponse register(UserRegisterRequest userRegisterRequest) {
        userRegisterRequest.setUsername(userRegisterRequest.getUsername().toLowerCase());
        validate(userRegisterRequest.getUsername());

        User user = map(userRegisterRequest);
        userRepository.save(user);

        UserRegisterResponse result = map(user);

        return result;
    }

    @Override
    public void createPost(UserPostRequest userPostRequest) {
        userPostRequest.setUsername(userPostRequest.getUsername().toLowerCase());
        User foundUser = userRepository.findByUsername(userPostRequest.getUsername());
        validateUser(foundUser);

        Post post = postServices.createPost(userPostRequest);
        foundUser.getPosts().add(post);
        userRepository.save(foundUser);
    }

    @Override
    public User findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        validateUser(user);
        return user;
    }

    @Override
    public void updatePost(UserEditPostRequest userEditPostRequest) {
        userEditPostRequest.setUsername(userEditPostRequest.getUsername().toLowerCase());
        User foundUser = userRepository.findByUsername(userEditPostRequest.getUsername());
        validateUser(foundUser);

        Post oldPost = findUserPost(userEditPostRequest.getPostId(), foundUser);
        Post newPost = postServices.updatePost(userEditPostRequest);

        foundUser.getPosts().remove(oldPost);
        foundUser.getPosts().add(newPost);

        userRepository.save(foundUser);
    }

    @Override
    public void deletePost(UserDeletePostRequest userDeletePostRequest) {
        userDeletePostRequest.setUsername(userDeletePostRequest.getUsername().toLowerCase());
        User foundUser = userRepository.findByUsername(userDeletePostRequest.getUsername());
        Post post = findUserPost(userDeletePostRequest.getPostId(), foundUser);
        foundUser.getPosts().remove(post);
        userRepository.save(foundUser);
    }

    private Post findUserPost(String postId, User user) {
        for (Post post : user.getPosts()) if (post.getId().equals(postId)) return post;
        throw new PostNotFoundException("Post not found");
    }

    private static void validateUser(User user) {
        if (user == null) throw new UserDoesntExist("Username does not exist");
    }

    private void validate(String username){
        boolean userExists = userRepository.existsByUsername(username);
        if(userExists) throw new UserAlreadyExist(username + " already exist");
    }

 
}
