package africa.semicolon.services;

import africa.semicolon.data.model.Post;
import africa.semicolon.data.model.User;
import africa.semicolon.data.model.View;
import africa.semicolon.data.repository.PostRepository;
import africa.semicolon.dto.request.UserEditPostRequest;
import africa.semicolon.dto.request.UserPostRequest;
import africa.semicolon.dto.request.UserViewPostRequest;
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
        System.out.println(post.getViews());
        post.getViews().add(view);
        System.out.println(post.getViews());
        postRepository.save(post);
    }

    public Post findPostById(String postId) {
        Optional<Post> post = postRepository.findById(postId);
        if (post.isEmpty()) throw new PostNotFoundException("Post not found");
        return post.get();
    }


}
