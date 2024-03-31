package africa.semicolon.utils;

import africa.semicolon.data.model.Comment;
import africa.semicolon.data.model.Post;
import africa.semicolon.data.model.User;
import africa.semicolon.data.model.View;
import africa.semicolon.dto.request.*;
import africa.semicolon.dto.response.CreatePostResponse;
import africa.semicolon.dto.response.EditPostResponse;
import africa.semicolon.dto.response.UserRegisterResponse;

import java.time.format.DateTimeFormatter;

public class Mapper {
    public static User requestMap(UserRegisterRequest userRegisterRequest){
        User user = new User();
        user.setUsername(userRegisterRequest.getUsername());
        user.setPassword(userRegisterRequest.getPassword());
        user.setFirstName(userRegisterRequest.getFirstName());
        user.setLastName(userRegisterRequest.getLastName());
        return user;
    }

    public static Post requestMap(UserPostRequest userPostRequest){
        Post post = new Post();
        post.setTitle(userPostRequest.getTitle());
        post.setContent(userPostRequest.getContent());
        post.setId(userPostRequest.getUsername());
        return post;
    }

    public static Post requestMap(UserEditPostRequest userEditPostRequest){
        Post post = new Post();
        post.setTitle(userEditPostRequest.getTitle());
        post.setContent(userEditPostRequest.getContent());
        post.setId(userEditPostRequest.getUsername());
        return post;
    }

    public static View requestMap(UserViewPostRequest userViewPostRequest){
        View view = new View();
        view.setViewer(userViewPostRequest.getViewer());
        return view;
    }

    public static Comment requestMap(UserCommentPostRequest userCommentPostRequest){
        Comment comment = new Comment();
        comment.setCommenter(userCommentPostRequest.getCommenter());
        comment.setComment(userCommentPostRequest.getComment());
        return comment;
    }

    public static UserRegisterResponse responseMap(User user){
        UserRegisterResponse userRegisterResponse = new UserRegisterResponse();
        userRegisterResponse.setUsername(user.getUsername());
        userRegisterResponse.setId(user.getId());
        userRegisterResponse.setDateCreated(DateTimeFormatter.ofPattern("dd-MM-yyyy, hh:mm:ss").format(user.getDateCreated()));
        return userRegisterResponse;
    }

    public static CreatePostResponse createPostResponseMap(Post post){
        CreatePostResponse createPostResponse = new CreatePostResponse();
        createPostResponse.setPostId(post.getId());
        createPostResponse.setPostTitle(post.getTitle());
        createPostResponse.setPostContent(post.getContent());
        createPostResponse.setDateCreated(DateTimeFormatter.ofPattern("dd-MM-yyyy, hh:mm:ss").format(post.getDateCreated()));
        return createPostResponse;
    }

    public static EditPostResponse editPostResponseMap(Post post){
        EditPostResponse editPostResponse = new EditPostResponse();
        editPostResponse.setPostId(post.getId());
        editPostResponse.setPostTitle(post.getTitle());
        editPostResponse.setPostContent(post.getContent());
        editPostResponse.setDateCreated(DateTimeFormatter.ofPattern("dd-MM-yyyy, hh:mm:ss").format(post.getDateCreated()));
        return editPostResponse;
    }
}
