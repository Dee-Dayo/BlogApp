package africa.semicolon.services;

import africa.semicolon.data.model.Comment;
import africa.semicolon.data.model.Post;
import africa.semicolon.data.model.User;
import africa.semicolon.data.model.View;
import africa.semicolon.data.repository.PostRepository;
import africa.semicolon.dto.request.*;
import africa.semicolon.exceptions.PostNotFoundException;
import africa.semicolon.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static africa.semicolon.utils.Mapper.requestMap;

@Service
public class PostServicesImpl implements PostServices {

    @Autowired
    PostRepository postRepository;

    @Autowired
    ViewServices viewServices;

    @Autowired
    CommentServices commentServices;


    @Override
    public Post createPost(UserPostRequest userPostRequest) {
        Post post = requestMap(userPostRequest);
        postRepository.save(post);
        return post;
    }

    @Override
    public Post updatePost(UserEditPostRequest userEditPostRequest) {
        Post updatedPost = requestMap(userEditPostRequest);
        postRepository.save(updatedPost);
        return updatedPost;
    }

    @Override
    public void viewPost(UserViewPostRequest userViewPostRequest) {
        Post post = findPostById(userViewPostRequest.getPostId());
        View view = viewServices.saveView(userViewPostRequest);
        post.getViews().add(view);
        postRepository.save(post);
    }

    public Post findPostById(String postId) {
        Optional<Post> post = postRepository.findById(postId);
        if (post.isEmpty()) throw new PostNotFoundException("Post not found");
        return post.get();
    }

    @Override
    public void commentPost(UserCommentPostRequest userCommentPostRequest) {
        Post post = findPostById(userCommentPostRequest.getPostId());
        Comment comment = commentServices.saveComment(userCommentPostRequest);
        post.getComments().add(comment);
        postRepository.save(post);
    }

    @Override
    public void deleteComment(UserDeleteCommentRequest userDeleteCommentRequest) {
        Post post = findPostById(userDeleteCommentRequest.getPostId());
        Comment comment = commentServices.deleteComment(userDeleteCommentRequest);
        post.getComments().remove(comment);
        postRepository.save(post);
    }

    @Override
    public void deletePost(UserDeletePostRequest userDeletePostRequest) {
        Post post = findPostById(userDeletePostRequest.getPostId());
        postRepository.delete(post);
    }


}
