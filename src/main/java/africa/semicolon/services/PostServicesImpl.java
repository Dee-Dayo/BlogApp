package africa.semicolon.services;

import africa.semicolon.data.model.Post;
import africa.semicolon.data.repository.PostRepository;
import africa.semicolon.dto.request.UserEditPostRequest;
import africa.semicolon.dto.request.UserPostRequest;
import africa.semicolon.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static africa.semicolon.utils.Mapper.requestMap;

@Service
public class PostServicesImpl implements PostServices {

    @Autowired
    PostRepository postRepository;


    @Override
    public Post createPost(UserPostRequest userPostRequest) {
        Post post = Mapper.requestMap(userPostRequest);
        postRepository.save(post);
        return post;
    }

    @Override
    public Post updatePost(UserEditPostRequest userEditPostRequest) {
        Post updatedPost = Mapper.requestMap(userEditPostRequest);
        postRepository.save(updatedPost);
        return updatedPost;
    }


}
