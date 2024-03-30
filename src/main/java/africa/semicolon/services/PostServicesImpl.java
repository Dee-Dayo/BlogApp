package africa.semicolon.services;

import africa.semicolon.data.model.Post;
import africa.semicolon.data.model.User;
import africa.semicolon.data.repository.PostRepository;
import africa.semicolon.dto.request.UserEditPostRequest;
import africa.semicolon.dto.request.UserPostRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static africa.semicolon.utils.Mapper.map;

@Service
public class PostServicesImpl implements PostServices {

    @Autowired
    PostRepository postRepository;


    @Override
    public Post createPost(UserPostRequest userPostRequest) {
        Post post = map(userPostRequest);
        postRepository.save(post);
        return post;
    }

    @Override
    public Post updatePost(UserEditPostRequest userEditPostRequest) {
        Post updatedPost = map(userEditPostRequest);
        postRepository.save(updatedPost);
        return updatedPost;
    }


}
